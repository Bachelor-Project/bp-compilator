/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import base.compilations.JavaCompilation;
import helpers.CompileError;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dato
 */
public class CompilationTest {
    
    public CompilationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makeCompilation method, of class Compilation.
     */
    @Test
    public void testMakeCompilation() {
        StringBuffer code = new StringBuffer("public static void main(String []args){\n" +
"		String a = \"aba\";\n" +
"		StringBuffer b = new StringBuffer();\n" +
"     }");
        Compilation instance = new JavaCompilation();
        List<CompileError> result = instance.makeCompilation(code);
        
        for (CompileError compileError : result) {
            System.out.println(compileError);
        }
        
        Assert.assertTrue(true);
    }

    /**
     * Test of createFileFor method, of class Compilation.
     * @throws java.lang.Exception
     */
//    @Test
    public void testCreateFileFor() throws Exception {
        System.out.println("createFileFor");
        StringBuffer codeBuff = null;
        Compilation instance = new CompilationImpl();
        File expResult = null;
        File result = instance.createFileFor(codeBuff);
    }

    /**
     * Test of processStream method, of class Compilation.
     */
//    @Test
    public void testProcessStream() {
        System.out.println("processStream");
        InputStream stream = null;
        Compilation instance = new JavaCompilation();
//        instance.processStream(stream, );
    }

    public class CompilationImpl extends Compilation {

        @Override
        public File createFileFor(StringBuffer codeBuff) throws IOException {
            return null;
        }

        @Override
        protected List<CompileError> processStream(InputStream stream) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
