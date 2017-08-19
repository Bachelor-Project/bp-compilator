/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.compilations;

import helpers.CompilationError;
import interfaces.Compilation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Dato
 */
public class PythonCompilation extends Compilation {

    public PythonCompilation(){
        compilatorPath = "python3";
        fileExtention = ".py";
    }
    
    @Override
    protected void writeUserCodeInto(File file, String code) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(code);
            writer.flush();
        }
    }

//    @Override
//    protected List<CompilationError> processStream(InputStream stream) throws IOException {
//        List<CompilationError> errors = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
//            String line = reader.readLine();
//            String errorText = StringUtils.substringBetween(line, "(", ", (");
//            String content = StringUtils.substringBetween(line, ", (", ")");
//            String lineNum = StringUtils.substringBetween(content, ".py',", ",").trim();
//            String errorInCode = StringUtils.substringBetween(content, "\"", "\"");
//            
//            CompilationError error = new CompilationError();
//            error.setLine(Integer.parseInt(lineNum));
//            error.setErrorText(errorText);
//            error.setCode(errorInCode);
//            
//            errors.add(error);
//        }
//        return errors;
//    }

    @Override
    protected List<CompilationError> processStream(InputStream stream) throws IOException {
        List<CompilationError> errors = new ArrayList<>();
        int flag = 0;
        int lineNum = 0;
        String errorText, inCode = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF8"))) {
            String entry;
            while((entry = reader.readLine()) != null){
                String line = entry.trim();
                if (!line.equals("^")){
                    System.out.println("line: " + line);
                    if (isErrorStarting(line)){
                        lineNum = cutLineNumberFrom(line);
                        flag = 1;
                    }
                    else if (flag == 1){
                        inCode = line;
                        flag = 2;
                    }
                    else if (flag == 2){
                        errorText = line;
                        flag = 0;
                        
                        CompilationError error = new CompilationError();
                        error.setLine(lineNum);
                        error.setErrorText(errorText);
                        error.setCode(inCode);
                        errors.add(error);
                    }
                }
            }
        }
        return errors;
    }
    
    private boolean isErrorStarting(String line){
        Pattern pattern = Pattern.compile("^(File\\s\")");
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    private int cutLineNumberFrom(String line) {
        int result = -1;
        String sentinel = "line ";
        int index = line.indexOf(sentinel);
        String includeLineNumber = line.substring(index + sentinel.length());
        
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(includeLineNumber);
        if (matcher.find()){
            result = Integer.parseInt(matcher.group());
        }
        return result;
    }
    
}
