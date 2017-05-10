/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import helpers.CodeData;
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
    
    public final List<CompileError> makeCompilation(CodeData data){
        List<CompileError> errors = new ArrayList<>();
        try {
            String name = data.getUser() + "_" + data.getTaskId();
            File file = createFileFor(name, new StringBuffer(data.getCode()));
            Process proc = Runtime.getRuntime().exec(compilatorPath + " " + file.getAbsolutePath());
            proc.waitFor();
            boolean isCorrect = (proc.exitValue() == 0);
            System.out.println("isCorrect: " + isCorrect);
            if (!isCorrect){
                errors = processStream(proc.getErrorStream());
            }
//            file.delete();
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Compilation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return errors;
    }
    
    protected abstract File createFileFor(String fileName, StringBuffer codeBuff) throws IOException;
    
    protected abstract List<CompileError> processStream(InputStream stream) throws IOException;

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compilation other = (Compilation) obj;
        if (!this.compilatorPath.equals(other.compilatorPath)) {
            return false;
        }
        return this.tempFilesDirectory.equals(other.tempFilesDirectory);
    }
    
    
}
