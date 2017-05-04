/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.compilations;

import java.io.File;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Dato
 */
public class JavaCompilationTest {
    
    public JavaCompilationTest() {
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
     * Test of createFileFor method, of class JavaCompilation.
     */
//    @Test
    public void testCreateFileFor() throws Exception {
        System.out.println("createFileFor");
        StringBuffer codeBuff = null;
        JavaCompilation instance = new JavaCompilation();
        File expResult = null;
        File result = instance.createFileFor(codeBuff);
    }

    /**
     * Test of processStream method, of class JavaCompilation.
     */
//    @Test
    public void testProcessStream() {
        System.out.println("processStream");
        InputStream stream = null;
        JavaCompilation instance = new JavaCompilation();
    }
    
}
