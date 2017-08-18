/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.compilator;

import base.CompilationFactory;
import base.JavaExecution;
import helpers.TaskData;
import base.enums.CompilatorType;
import helpers.CodeData;
import helpers.CompilationError;
import interfaces.Compilation;
import interfaces.Execution;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Dato
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExecutorService {
    
//    private Compilation compManager;
    
    public ExecutorService(){
        executionsMap.put("java", new JavaExecution());
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
    @Path("compile")
    public Response compileCode(CodeData cd){
        CompilatorType compType = getAppropTypeFrom(cd.getLang());
        Compilation compilator = CompilationFactory.getCompilation(compType);
        Response.ResponseBuilder responseBuilder;
        if (compilator == null) {
            String noCompilator = "There is no compilator on " + cd.getLang();
            responseBuilder = Response.status(405).type(MediaType.TEXT_PLAIN).entity(noCompilator);
        }
        else {
            List<CompilationError> compileResult = compilator.makeCompilation(cd);
            
            System.out.println("compilation result: " + compileResult);
            
            responseBuilder = (compileResult.isEmpty()) ? Response.status(204) : Response.status(400).entity(compileResult);
        }
        return responseBuilder.build();
//        return responseBuilder.header("access-control-allow-origin", "*").build();
    }
    
    /**
     * The method converts string type to compilation enumerator. If no such value exists, return null;
     * @param type The string value of type (CaseInsenstitive).
     * @return Appropriate type or null.
     */
    private CompilatorType getAppropTypeFrom(String type){
        CompilatorType result = CompilatorType.Empty;
        try{
            result = CompilatorType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) { }
        return result;
    }
    
    @GET
    @Path("a")
    public Response test(){
        return Response.status(200).type(MediaType.TEXT_PLAIN).entity("T E S T").build();
    }
    
    
    
    
    private final Map<String, Execution> executionsMap = new HashMap<>();
    private final String filesDataServiceURL = "http://localhost:8080/files_data/";
    
    @POST
    @Path("run_code")
    public Response codeExec(@QueryParam("lang") String lang, 
                            @QueryParam("username") String userName, 
                            @QueryParam("taskId") int taskId, 
                            @QueryParam("tests") List<String> testsIds, 
                            @QueryParam("compiled") boolean compiled){
        System.out.println("lang: " + lang);
        System.out.println("user: " + userName);
        System.out.println("taskId: " + taskId);
//        System.out.println("tests.size(): " + testsIds.size());
        System.out.println("compiled: " + compiled);
        
        if (compiled){
            if (executionsMap.containsKey(lang)){
                Execution execution = executionsMap.get(lang);
//                TaskData td = getTaskDataFor(taskId);
                
                return Response.status(200).type(MediaType.TEXT_PLAIN).encoding("success").build();
            }
            else {
                return Response.status(400).type(MediaType.TEXT_PLAIN).entity("No Language support.").build();
            }
        }
        return Response.status(400).type(MediaType.TEXT_PLAIN).entity("No Compiled").build();
    }
    
    // connect to FilesData service and gives TaskInfo.
//    private TaskData getTaskDataFor(int taskId){
//        Client jerseyClient = ClientBuilder.newClient();
//        WebTarget taskTarget = jerseyClient.target(filesDataServiceURL).path("api/tasks/" + taskId);
//        Invocation.Builder invoc = taskTarget.request(MediaType.TEXT_PLAIN);
//        String res = invoc.get(String.class);
//        System.out.println("task data: " + res);
//        return null;
//    }
}
