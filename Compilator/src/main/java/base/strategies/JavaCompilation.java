/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.strategies;

import helpers.CompileResult;
import interfaces.Compilation;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dato
 */
public class JavaCompilation extends Compilation {

    public JavaCompilation(){
        compilatorPath = "C:\\Users\\Dato\\Documents\\GitHub\\BachelorProject\\extra_files\\compilators\\java\\bin\\javac.exe";
    }
    
    public CompileResult makeCompileOf(StringBuffer code) {
        try {
            File file = createFileWith("java");
            writeCodeInto(file, code);
            Process proc = Runtime.getRuntime().exec(compilatorPath + " " + file.getAbsolutePath());
            proc.waitFor();
            if (proc.exitValue() == 0){
                System.out.println("------------------ OK ------------------");
            }
            else {
                System.out.println("------------------ ERROR ------------------");
                printLines(proc.getErrorStream());
            }
            file.delete();
            return null;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(JavaCompilation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private File createFileWith(String ext) {
        return new File("bla." + ext);
    }

    private void printLines(InputStream errorStream) throws IOException {
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(errorStream))) {
            String line;
            while( (line = buff.readLine()) != null){
                System.out.println("line: " + line);
            }
        }
    }

    private void writeCodeInto(File file, StringBuffer codeBuffer) {
        
    }

    @Override
    protected File createFileFor(StringBuffer codeBuff) {
        return new File("");
    }

    @Override
    protected void processStream(InputStream stream) {
    }
    
}
