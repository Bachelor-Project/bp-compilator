/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import base.enums.CompilatorType;
import base.strategies.CPPCompilation;
import base.strategies.JavaCompilation;
import base.strategies.PascalCompilation;
import base.strategies.PythonCompilation;
import interfaces.Compilation;

/**
 *
 * @author Dato
 */
public class CompilationFactory {
    
//    private static final String JAVA = "java";
//    private static final String PYTHON = "python";
//    private static final String CPP = "c++";
//    private static final String PASCAL = "pascal";
    
    public static Compilation getCompilation(CompilatorType type){
        Compilation result;
        switch (type) {
            case CPP:
                result = new CPPCompilation();
                break;
            case JAVA:
                result = new JavaCompilation();
                break;
            case PASCAL:
                result = new PascalCompilation();
                break;
            default:
                result = new PythonCompilation();
                break;
        }
        return result;
    }
}
