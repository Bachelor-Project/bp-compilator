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
//        compilatorPath = "C:\\Users\\Dato\\Documents\\GitHub\\BachelorProject\\extra_files\\compilators\\java\\bin\\javac.exe";
        compilatorPath = "javac.exe";
        tempFilesDirectory = "C:\\Users\\Dato\\Documents\\GitHub\\BachelorProject\\extra_files\\compilators\\java\\";
    }
    
//    public CompileError makeCompileOf(StringBuffer code) {
//        try {
//            File file = createFileWith("java");
//            writeCodeInto(file, code);
//            Process proc = Runtime.getRuntime().exec(compilatorPath + " " + file.getAbsolutePath());
//            proc.waitFor();
//            if (proc.exitValue() == 0){
//                System.out.println("------------------ OK ------------------");
//            }
//            else {
//                System.out.println("------------------ ERROR ------------------");
//                printLines(proc.getErrorStream());
//            }
//            file.delete();
//            return null;
//        } catch (IOException | InterruptedException ex) {
//            Logger.getLogger(JavaCompilation.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return null;
//    }

    private void printLines(InputStream errorStream) throws IOException {
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(errorStream))) {
            String line;
            while( (line = buff.readLine()) != null){
                System.out.println("line: " + line);
            }
        }
    }

    @Override
    protected File createFileFor(StringBuffer codeBuff) throws IOException {
        String path = tempFilesDirectory + "bla.java";
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(codeBuff.toString());
        writer.flush();
        return file;
    }

    @Override
    protected List<CompileError> processStream(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        List<CompileError> errors = new ArrayList<>();
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
        return errors;
    }
    
}
