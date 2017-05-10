/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.compilator;

import base.CompilationFactory;
import base.enums.CompilatorType;
import helpers.CodeData;
import helpers.CompileError;
import interfaces.Compilation;
import java.util.List;
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
    
//    private Compilation compManager;
    
    public CompilatorService(){
//        this(new CodeCompilator());
    }
    
//    public CompilatorService(Compilation compManager){
//        this.compManager = compManager;
//    }
    
    
    /**
     * The method make compilation of given code on given programming language.
     * @param cd The language and code data object.
     * @return Response with compilation errors, or no content - error free.
     */
    @PUT
    public Response compileCode(CodeData cd){
        CompilatorType compType = getAppropTypeFrom(cd.getProgLang());
        Compilation compilator = CompilationFactory.getCompilation(compType);
        Response.ResponseBuilder responseBuilder;
        if (compilator == null) {
            responseBuilder = Response.status(405);
        }
        else {
            List<CompileError> compileResult = compilator.makeCompilation(cd);
            responseBuilder = (compileResult.isEmpty()) ? Response.status(204) : Response.status(404).entity(compileResult);
        }
        return responseBuilder.build();
    }
    
    /**
     * The method converts string type to compilation enumerator. If no such value exists, return null;
     * @param type The string value of type (CaseInsenstitive).
     * @return Appropriate type or null.
     */
    private CompilatorType getAppropTypeFrom(String type){
        CompilatorType result = CompilatorType.None;
        try{
            result = CompilatorType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) { }
        return result;
    }
}
