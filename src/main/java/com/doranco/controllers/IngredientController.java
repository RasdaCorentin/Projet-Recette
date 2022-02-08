/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.IngredientDaoInterface;
import com.doranco.entities.Ingredient;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author samha
 */
@Path("/v1/ingredient")
public class IngredientController {
    
    private DaoFactory daoFactory;
    
    private IngredientDaoInterface ingredientDaoInterface;
    
    public IngredientController () {
        daoFactory = new DaoFactory();
        ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
    }
    
    
    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeIngredient() {
        
        Response response = Response
                .ok(ingredientDaoInterface.getListeIngredient())
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
        
    }
    
    /**
     * Création simple, sans le rattacher à une recette
     * @param ingredient
     * @return 
     */
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIngredient(Ingredient ingredient) {
        
//        Ingredient nouvelIngredient = new Ingredient("Riz rond", 25);
        
        Response response = Response
                .ok(ingredientDaoInterface.createIngredient(ingredient))
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
        
    }
    
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredient(Ingredient ingredient, @PathParam(value = "id") int id) {
        
        ingredient = new Ingredient("Libelle update", 0);

        Response response = Response
                .ok(ingredientDaoInterface.updateIngredient(ingredient, id))
                .build();

        return response;
        
    }
    
    
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteIngredient(Ingredient ingredient, @PathParam(value = "id") int id) {
        
        Response response = Response
                .ok(ingredientDaoInterface.deleteIngredient(id))
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
        
    }
    
}
