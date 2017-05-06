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
public class PythonCompilation extends Compilation {

    public PythonCompilation(){
        compilatorPath = "python -m py_compile ";
        tempFilesDirectory = "..\\..\\extra_files\\compilators\\python\\";
    }
    
    @Override
    protected File createFileFor(String fileName, StringBuffer codeBuff) throws IOException {
        String path = tempFilesDirectory + fileName + ".py";
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(codeBuff.toString());
            writer.flush();
        }
        return file;
    }

    @Override
    protected List<CompileError> processStream(InputStream stream) throws IOException {
        List<CompileError> errors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line = reader.readLine();
            String errorText = StringUtils.substringBetween(line, "(", ", (");
            String content = StringUtils.substringBetween(line, ", (", ")");
            String lineNum = StringUtils.substringBetween(content, ".py',", ",").trim();
            String errorInCode = StringUtils.substringBetween(content, "\"", "\"");
            
            CompileError error = new CompileError();
            error.setLine(Integer.parseInt(lineNum));
            error.setErrorText(errorText);
            error.setCode(errorInCode);
            
            errors.add(error);
        }
        return errors;
    }

    
}
