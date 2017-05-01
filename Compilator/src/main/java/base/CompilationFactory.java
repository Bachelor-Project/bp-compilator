/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import base.strategies.CPPCompilation;
import base.strategies.JavaCompilation;
import base.strategies.PascalCompilation;
import base.strategies.PythonCompilation;
import interfaces.CompilationStrategy;

/**
 *
 * @author Dato
 */
public class CompilationFactory {
    
    private static final String JAVA = "java";
    private static final String PYTHON = "python";
    private static final String CPP = "c++";
    private static final String PASCAL = "pascal";
    
    public static CompilationStrategy getCompilation(String lang){
        CompilationStrategy result;
        String langToLower = lang.toLowerCase();
        if (langToLower.equals(JAVA)){
            result = new JavaCompilation();
        }
        else if (langToLower.equals(PYTHON)){
            result = new PythonCompilation();
        }
        else if (langToLower.equals(CPP)){
            result = new CPPCompilation();
        }
        else {
            result = new PascalCompilation();
        }
        return result;
    }
}
