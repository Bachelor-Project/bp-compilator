/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import helpers.CompileError;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dato
 */
public abstract class Compilation {
    
    protected String compilatorPath = "";
    protected String tempFilesDirectory = "";
    
    public final List<CompileError> makeCompilation(StringBuffer code){
        List<CompileError> errors = new ArrayList<>();
        try {
            File file = createFileFor(code);
            Process proc = Runtime.getRuntime().exec(compilatorPath + " " + file.getAbsolutePath());
            proc.waitFor();
            boolean isCorrect = (proc.exitValue() == 0);
            if (!isCorrect){
                errors = processStream(proc.getErrorStream());
            }
            file.delete();
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Compilation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return errors;
    }
    
    protected abstract File createFileFor(StringBuffer codeBuff) throws IOException;
    
    protected abstract List<CompileError> processStream(InputStream stream) throws IOException;
}
