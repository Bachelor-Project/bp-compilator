/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import helpers.CompileResult;

/**
 *
 * @author Dato
 */
public interface CompilatorManager {
    
    public CompileResult makeCompile(String progLang, StringBuffer code);
    
}