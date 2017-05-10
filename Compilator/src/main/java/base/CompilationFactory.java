/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import base.enums.CompilatorType;
import base.compilations.CPPCompilation;
import base.compilations.JavaCompilation;
import base.compilations.PascalCompilation;
import base.compilations.PythonCompilation;
import interfaces.Compilation;

/**
 *
 * @author Dato
 */
public class CompilationFactory {
    
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
            case PYTHON:
                result = new PythonCompilation();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }
}
