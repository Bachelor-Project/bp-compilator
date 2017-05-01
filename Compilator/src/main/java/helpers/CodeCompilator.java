/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import interfaces.CompilatorManager;

/**
 *
 * @author Dato
 */
public class CodeCompilator implements CompilatorManager {

    @Override
    public CompileResult makeCompile(String progLang, StringBuffer code) {
        return new CompileResult(true, "");
    }
    
}
