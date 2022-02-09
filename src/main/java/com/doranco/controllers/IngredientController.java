/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.IngredientDaoInterface;
import com.doranco.entities.Ingredient;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 33767
 */
@Path("/utilisateur/ingredient")
public class IngredientController {
        DaoFactory daoFactory = new DaoFactory();

        IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
    Jsonb jsonb = JsonbBuilder.create();
        /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Liste Ingredient
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/admin/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeIngredient() {  

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                //Ajouter to To string pour info ciblé
                .entity(ingredientDaoInterface.getListeIngredients())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }
           /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Update Ingredient
--------------------------------------------------------------------------------------------------------------------------
    */
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredient(Ingredient ingredient, @PathParam(value = "id") int id) {
        
        ingredient = new Ingredient("Libelle update", "1");

        Response response = Response
                .ok(ingredientDaoInterface.updateIngredient(ingredient, id))
                .build();

        return response;
        
    }
        /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Delete Ingredient
--------------------------------------------------------------------------------------------------------------------------
    */
    @DELETE
    @Path("admin/delete/{id}")
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
