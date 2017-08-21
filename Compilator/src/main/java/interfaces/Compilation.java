/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import helpers.CodeData;
import helpers.CompilationError;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    protected String fileExtention = "";
    
    private final String tempFilesDirectory = "/home/dato/Documents/project/codesData/users";
    
    public final List<CompilationError> makeCompilation(CodeData data){
        List<CompilationError> errors = new ArrayList<>();
        try {
            String userFolderPath = makeFolder(tempFilesDirectory + File.separator + data.getUser());
            String fullPathOfFile = userFolderPath + File.separator + data.getTaskName() + fileExtention;
            File file = new File(fullPathOfFile);
            if (file.exists()){
                file.delete();
            }
            
            writeUserCodeInto(file, data.getCode());
            
            String[] langSpecificCompileCommands = compilatorPath.split(" ");
            String[] commands = new String[langSpecificCompileCommands.length + 1];
            System.arraycopy(langSpecificCompileCommands, 0, commands, 0, langSpecificCompileCommands.length);
            commands[langSpecificCompileCommands.length] = data.getTaskName() + fileExtention;
            
            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.directory(new File(userFolderPath));
            Process proc = pb.start();
            proc.waitFor();
            
            boolean isCorrect = (proc.exitValue() == 0);
            System.out.println("isCorrect: " + isCorrect);
            if (!isCorrect){
                errors = processStream(proc.getErrorStream());
            }
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Compilation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return errors;
    }
    
    private String makeFolder(String path){
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }
        return dir.getAbsolutePath();
    }
    
    /**
     * The method creates file with specific extention and write user code in it.
     * @param file The file created for user code compilation.
     * @param code The user code.
     * @throws IOException If file does not create correctly.
     */
    protected abstract void writeUserCodeInto(File file, String code) throws IOException;
    
    /**
     * The method process error stream and creates List of error entry.
     * @param stream The input stream of error.
     * @return List of compilation error entries.
     * @throws IOException If stream does not process correctly.
     */
    protected abstract List<CompilationError> processStream(InputStream stream) throws IOException;
    protected List<CompilationError> processStream(OutputStream stream) {
        System.out.println("-------------------\n" + stream);
        
        return null;
    }
    
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
