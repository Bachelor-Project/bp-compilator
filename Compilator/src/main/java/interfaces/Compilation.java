/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import helpers.CompileResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dato
 */
public abstract class Compilation {
    
    protected String compilatorPath = "";
    
    public final CompileResult makeCompilation(StringBuffer code){
        CompileResult result = new CompileResult();
        try {
            File file = createFileFor(code);
            Process proc = Runtime.getRuntime().exec(compilatorPath + " " + file.getAbsolutePath());
            proc.waitFor();
            boolean isCorrect = (proc.exitValue() == 0);
            result.setIsCorrect(isCorrect);
            if (!isCorrect){
                processStream(proc.getErrorStream());
            }
            
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Compilation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    protected abstract File createFileFor(StringBuffer codeBuff);
    
    protected abstract void processStream(InputStream stream);
}
