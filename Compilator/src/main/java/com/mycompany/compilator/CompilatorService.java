/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.compilator;

import base.CompilationFactory;
import helpers.CodeData;
import helpers.CompileResult;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Dato
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompilatorService {
    
//    private CompilationStrategy compManager;
    
    public CompilatorService(){
//        this(new CodeCompilator());
    }
    
//    public CompilatorService(CompilationStrategy compManager){
//        this.compManager = compManager;
//    }
    
    @PUT
    public Response compileCode(CodeData cd){
        CompileResult compileResult = CompilationFactory.getCompilation(cd.getProgLang()).makeCompile(cd.getProgLang(), new StringBuffer(cd.getCode()));
        Response.ResponseBuilder responseBuilder;
        if (compileResult.isCorrect()){
            responseBuilder = Response.status(204);
        }
        else {
            responseBuilder = Response.status(404).entity(compileResult);
        }
        return responseBuilder.build();
    }
}
