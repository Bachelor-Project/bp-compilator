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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Dato
 */
public class PascalCompilation extends Compilation {

    public PascalCompilation(){
        compilatorPath = "fpc ";
        fileExtention = ".pas";
    }
    
    @Override
    protected void writeUserCodeInto(File file, String code) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(code);
            writer.flush();
        }
    }
    
    protected List<CompilationError> processStream(OutputStream stream) {
        System.out.println("stream---------------------: \n" + stream.toString());
        return null;
    }
   
    // 1. error iwereba Error: ganyopilebashi
    // 2. aklia ; an aris sintaqsuri shecdoma, iwereba Fatal: ...

    @Override
    protected List<CompilationError> processStream(InputStream stream) throws IOException{
        
        System.out.println("stream: " + stream.available());
        
        List<CompilationError> errors = new ArrayList<>();
        System.out.println("???????????????????????????");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String colonSymbol = ":";
            String errorKeyWord = "Error" + colonSymbol;
            String fatalKeyWord = "Fatal" + colonSymbol;
            String line = "";
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            while((line = reader.readLine()) != null) {
                System.out.println("before if----------");
                if (line.contains(fileExtention)){
                    System.out.println("aqanaaa--------------");
                    if(line.contains(errorKeyWord)){
                        CompilationError error = processLineFor(errorKeyWord, line);
                        errors.add(error);
                    }
                    else if (line.contains(fatalKeyWord)){
                        CompilationError syntaxFatal = processLineFor(fatalKeyWord, line);
                        errors.add(syntaxFatal);
                    }
                }
            }
        }
        System.out.println("---------------");
        for (CompilationError error : errors) {
            System.out.println(error);
        }
        System.out.println("---------------");
        return errors;
    }

    private CompilationError processLineFor(String errorType, String line){
        CompilationError error = new CompilationError();
        String beforeLineNumberStr = fileExtention + "(";
        int lineNumberStart = line.indexOf(beforeLineNumberStr) + beforeLineNumberStr.length();
        int lineNumberEnd = line.indexOf(",", lineNumberStart);
        String lineNumberAsStr = line.substring(lineNumberStart, lineNumberEnd);
        error.setLine(Integer.parseInt(lineNumberAsStr));
        error.setErrorText(StringUtils.substringAfter(line, errorType));
        return error;
    }
    
}
