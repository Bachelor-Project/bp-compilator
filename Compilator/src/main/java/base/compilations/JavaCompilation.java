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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Dato
 */
public class JavaCompilation extends Compilation {

    public JavaCompilation(){
        compilatorPath = "javac.exe ";
        fileExtention = ".java";
    }
    
    @Override
    protected void writeUserCodeInto(File file, String code) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String classNameFromClient = StringUtils.substringBetween(code, " class ", "{");
            String onlyFileName = StringUtils.substringAfterLast(file.getAbsolutePath(), File.separator);
            String classNameFromFile = StringUtils.substringBefore(onlyFileName, fileExtention);
            String newCodeContent = code.replace(classNameFromClient, classNameFromFile);
            
            writer.write(newCodeContent);
            writer.flush();
        }
    }

    @Override
    protected List<CompilationError> processStream(InputStream stream) throws IOException {
        System.out.println("stream: " + stream.available());
        
        List<CompilationError> errors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            int partsCount = 2;
            int count = 0;
            String colonSymbol = ":";
            String fileExtKeyWord = fileExtention + colonSymbol;
            String errorKeyWord = "error" + colonSymbol;
            String line;
            while((line = reader.readLine()) != null){
                if (count > 0){
                    CompilationError error = errors.get(errors.size() - 1);
                    error.setCode(error.getCode() + line + "\n");
                    count--;
                }
                else if (line.contains(fileExtention) && line.contains(errorKeyWord)){
                    CompilationError error = new CompilationError();
                    
                    int firstColon = line.indexOf(fileExtKeyWord) + fileExtKeyWord.length();
                    int secondColon = line.indexOf(colonSymbol, firstColon);
                    String lineAsStr = line.substring(firstColon, secondColon);
                    int lineNum = Integer.parseInt(lineAsStr);
                    error.setLine(lineNum);
                    
                    int startingErrorText = line.indexOf(errorKeyWord) + errorKeyWord.length();
                    String errorText = line.substring(startingErrorText);
                    error.setErrorText(errorText);
                    
                    errors.add(error);
                    count = partsCount;
                }
//                if (count % 3 == 2) {
//                    CompilationError error = errors.get(errors.size() - 1);
//                    error.setCode(line);
//                }
//                else {
//                    CompilationError error = errors.get(errors.size() - 1);
//                    String code = error.getCode() + "\n";
//                    code += line;
//                    error.setCode(code);
//                }
//                count ++;
            }
        }
        System.out.println("---------------");
        for (CompilationError error : errors) {
            System.out.println(error);
        }
        System.out.println("---------------");
        return errors;
    }
    
}
