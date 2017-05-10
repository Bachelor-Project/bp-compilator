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

/**
 *
 * @author Dato
 */
public class PascalCompilation extends Compilation {

    public PascalCompilation(){
        compilatorPath = "..\\..\\extra_files\\compilators\\pascal\\ppc386.exe";
        tempFilesDirectory = "..\\..\\extra_files\\compilators\\pascal\\userDir\\";
    }
    
    @Override
    protected File createFileFor(String fileName, StringBuffer codeBuff) throws IOException {
        String path = tempFilesDirectory + fileName + ".pas";
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Program " + fileName + ";\n");
            writer.write(codeBuff.toString());
            writer.flush();
        }
        return file;
    }

    @Override
    protected List<CompileError> processStream(InputStream stream) throws IOException{
        List<CompileError> errors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            
            System.out.println("aq?");
            
            while((line = reader.readLine()) != null) {
                System.out.println("line: "+ line);
            }
        }
        return errors;
    }

    
}
