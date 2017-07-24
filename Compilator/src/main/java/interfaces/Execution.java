/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import helpers.ExecResult;
import helpers.TaskData;

/**
 *
 * @author dato
 */
public abstract class Execution {
    
    public abstract ExecResult[] run(String codeFilePath, TaskData taskData, String taskDir, String[] testsIds);
    
}
