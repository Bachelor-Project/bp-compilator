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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dato
 */
public class CPPCompilation extends Compilation {

    public CPPCompilation(){
        compilatorPath = "g++";
        fileExtention = ".cpp";
    }

    @Override
    protected void writeUserCodeInto(File file, String code) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(code);
            writer.flush();
        }
    }

    @Override
    protected List<CompilationError> processStream(InputStream stream) throws IOException {
        List<CompilationError> errors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF8"))) {
            String line;
            int parts = 2;
            int partsCount = 0;
            String colonSymbol = ":";
            String fileExtKeyWord = fileExtention + colonSymbol;
            String errorKeyword = "error" + colonSymbol;
            String warningKeyword = "warning" + colonSymbol;
            while((line = reader.readLine()) != null){
                if (partsCount > 0){
                    CompilationError lastError = errors.get(errors.size() - 1);
                    lastError.setCode(lastError.getCode() + line + "\n");
                    partsCount--;
                }
                else if (line.contains(fileExtention) && (line.contains(errorKeyword) || line.contains(warningKeyword))){
                    CompilationError error = new CompilationError();
                    int firstColon = line.indexOf(fileExtKeyWord) + fileExtKeyWord.length();
                    int secondColon = line.indexOf(colonSymbol, firstColon);
                    String lineNumber = line.substring(firstColon, secondColon);
                    error.setLine(Integer.parseInt(lineNumber));

                    String errWarnKey = (line.contains(errorKeyword)) ? errorKeyword : warningKeyword;
                    int startingErrorText = line.indexOf(errWarnKey) + errWarnKey.length();
                    error.setErrorText(line.substring(startingErrorText));

                    errors.add(error);
                    partsCount = parts;
                }

            }
        }
        return errors;
    }
    
    
}
