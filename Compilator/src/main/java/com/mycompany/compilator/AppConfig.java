/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.compilator;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Dato
 */
@ApplicationPath("/")
public class AppConfig extends ResourceConfig {
    
    
    public AppConfig(){
        super();
        
        packages("com.mycompany.compilator");
    }
}
