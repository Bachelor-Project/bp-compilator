/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.compilations;

import helpers.CompileError;
import interfaces.Compilation;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Dato
 */
public class CPPCompilation extends Compilation {

    public CPPCompilation(){
        compilatorPath = "";
    }

    @Override
    protected File createFileFor(StringBuffer codeBuff) {
        return new File("");
    }

    @Override
    protected List<CompileError> processStream(InputStream stream) {
        return null;
    }
    
    
}
