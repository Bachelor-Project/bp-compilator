/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import base.CompilationFactory;
import base.compilations.JavaCompilation;
import base.enums.CompilatorType;
import helpers.CodeData;
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

    private CodeData makeCodeDataWith(String progLang, String user, int taskId, String code){
        CodeData cd = new CodeData(progLang, code);
        cd.setUser(user);
        cd.setTaskId(taskId);
        return cd;
    }
    
    /**
     * Test of makeCompilation method, of class Compilation.
     */
    @Test
    public void test_MakeCompilation_Java() {
        String code = "public static void main(String []args){\n" +
                "		String a = \"aba\";\n" +
                "		StringBuffer b = new StringBuffer();\n" +
                "     }";
        CodeData cd = new CodeData("java", code);
        cd.setTaskId(8);
        cd.setUser("testUser");
        
        List<CompileError> result = CompilationFactory.getCompilation(CompilatorType.JAVA).makeCompilation(cd);
        
        for (CompileError compileError : result) {
            System.out.println(compileError);
        }
        
        Assert.assertTrue(true);
    }
    @Test
    public void test_MakeCompilation_Python() {
        String code = "if __name__ == '__main__':\n" +
                            "	print 'Hello, world";
        CodeData cd = makeCodeDataWith("python", "testUser", 8, code);
        
        List<CompileError> result = CompilationFactory.getCompilation(CompilatorType.PYTHON).makeCompilation(cd);
        
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
//        File result = instance.createFileFor(codeBuff);
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
        protected File createFileFor(String fileName, StringBuffer codeBuff) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected List<CompileError> processStream(InputStream stream) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
