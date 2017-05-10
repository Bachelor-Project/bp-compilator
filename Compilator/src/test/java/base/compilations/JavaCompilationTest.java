/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.compilations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Dato
 */
public class JavaCompilationTest {
    
    private JavaCompilation instance;
    
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
        instance = new JavaCompilation();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * The method read file with 'fileName' and returns its content.
     * @param filePath Path of file with name.
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private String getFileContent(String filePath) throws FileNotFoundException, IOException{
        String result = "";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        while ((line = reader.readLine()) != null) {            
            result += line;
        }
        return result;
    }
    
    /**
     * Test of createFileFor method, of class JavaCompilation.
     * @throws java.lang.Exception
     */
//    @Test
    public void test_CreateFileFor_FileExists() throws Exception {
        StringBuffer codeBuffDummy = new StringBuffer();
        File result = instance.createFileFor("Some", codeBuffDummy);
        
        Assert.assertTrue(result.exists());
        result.delete();
    }
    /**
     * Test of createFileFor method, of class JavaCompilation.
     * @throws java.lang.Exception
     */
//    @Test
    public void test_CreateFileFor_WriteContent() throws Exception {
        String fileName = "Some";
        String mainContent = "public static void main(String[] args){\n}";
        File result = instance.createFileFor(fileName, new StringBuffer(mainContent));
        
        String expContent = "public class " + fileName + " {\n" + "\t" + mainContent + "\n" + "}";
        String res = getFileContent(result.getAbsolutePath());
        System.out.println("---------------- " + res);
        
        Assert.assertEquals(expContent, getFileContent(result.getAbsolutePath()));
        result.delete();
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
