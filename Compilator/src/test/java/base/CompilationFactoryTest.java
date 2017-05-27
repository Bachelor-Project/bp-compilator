/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import base.compilations.CPPCompilation;
import base.compilations.JavaCompilation;
import base.compilations.PascalCompilation;
import base.compilations.PythonCompilation;
import base.enums.CompilatorType;
import interfaces.Compilation;
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
public class CompilationFactoryTest {
    
    public CompilationFactoryTest() {
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
     * Test of getCompilation method, of class CompilationFactory.
     */
    @Test
    public void test_GetCompilation_None() {
        CompilatorType type = CompilatorType.Empty;
        Compilation result = CompilationFactory.getCompilation(type);
        
        Assert.assertTrue(result == null);
    }
    
    /**
     * Test of getCompilation method, of class CompilationFactory.
     */
    @Test
    public void test_GetCompilation_CPP() {
        CompilatorType type = CompilatorType.CPP;
        Compilation expComp = new CPPCompilation();
        Compilation result = CompilationFactory.getCompilation(type);
        
        Assert.assertTrue(expComp.equals(result));
    }
    
    
    /**
     * Test of getCompilation method, of class CompilationFactory.
     */
    @Test
    public void test_GetCompilation_Java() {
        CompilatorType type = CompilatorType.JAVA;
        Compilation expComp = new JavaCompilation();
        Compilation result = CompilationFactory.getCompilation(type);
        
        Assert.assertTrue(expComp.equals(result));
    }
    
    
    /**
     * Test of getCompilation method, of class CompilationFactory.
     */
    @Test
    public void test_GetCompilation_Pascal() {
        CompilatorType type = CompilatorType.PASCAL;
        Compilation expComp = new PascalCompilation();
        Compilation result = CompilationFactory.getCompilation(type);
        
        Assert.assertTrue(expComp.equals(result));
    }
    
    
    /**
     * Test of getCompilation method, of class CompilationFactory.
     */
    @Test
    public void test_GetCompilation_Python() {
        CompilatorType type = CompilatorType.PYTHON;
        Compilation expComp = new PythonCompilation();
        Compilation result = CompilationFactory.getCompilation(type);
        
        Assert.assertTrue(expComp.equals(result));
    }
    
}
