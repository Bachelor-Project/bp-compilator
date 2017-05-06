/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.compilations;

import helpers.CompileError;
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
//        compilatorPath = "..\\..\\extra_files\\compilators\\java\\bin\\javac.exe";
        compilatorPath = "javac.exe ";
        tempFilesDirectory = "..\\..\\extra_files\\compilators\\java\\";
    }
    
    @Override
    protected File createFileFor(String fileName, StringBuffer codeBuff) throws IOException {
        String path = tempFilesDirectory + fileName + ".java";
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("public class " + fileName + " {\n");
            writer.write("\t" + codeBuff.toString() + "\n");
            writer.write("}");
            writer.flush();
        }
        return file;
    }

    @Override
    protected List<CompileError> processStream(InputStream stream) throws IOException {
        List<CompileError> errors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            int count = 1;
            String line;
            while((line = reader.readLine()) != null){
                if (Character.isDigit(line.charAt(0))) break; // end of parsing.
                if (count % 3 == 1){
                    CompileError error = new CompileError();
                    
                    String lineAsStr = StringUtils.substringBetween(line, ".java:", ": error:");
                    int lineNum = Integer.parseInt(lineAsStr);
                    error.setLine(lineNum);
                    
                    String errorText = StringUtils.substringAfter(line, ": error:");
                    error.setErrorText(errorText);
                    
                    errors.add(error);
                }
                if (count % 3 == 2) {
                    CompileError error = errors.get(errors.size() - 1);
                    error.setCode(line);
                }
                else {
                    CompileError error = errors.get(errors.size() - 1);
                    String code = error.getCode() + "\n";
                    code += line;
                    error.setCode(code);
                }
                count ++;
            }
        }
        return errors;
    }
    
}
