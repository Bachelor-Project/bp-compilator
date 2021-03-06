/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author Dato
 */
public class CompilationError {
    
    private int line;
    private String errorText = "";
    private String inCode = "";
    
//    public CompilationError(boolean isCorrect, String errorText){
//        this.isCorrect = isCorrect;
//        this.errorText = errorText;
//    }
    
    public CompilationError(String errorText){
        this.errorText = errorText;
    }
    
    public CompilationError(){}

    public int getLine() {
        return line;
    }
    
    public String getErrorText() {
        return errorText;
    }

    public String getCode() {
        return inCode;
    }
    
    
    public void setLine(int line) {
        this.line = line;
    }
    
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public void setCode(String inCode) {
        this.inCode = inCode;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final CompilationError other = (CompilationError) obj;
        return errorText.equals(other.errorText);
    }

    @Override
    public String toString() {
        return "CompileError:" + "line=" + line + ", errorText=" + errorText + ", inCode:\n" + inCode + '}';
    }
    
}
