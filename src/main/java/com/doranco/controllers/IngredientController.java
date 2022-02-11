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

import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.json.JSONObject;


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

    @Path("/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeIngredient() {  

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)

                //Ajouter To string pour info ciblée

                .entity(ingredientDaoInterface.getListeIngredients())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

            /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Create Ingredient
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIngredient(Ingredient ingredient) {
        DaoFactory daoFactory = new DaoFactory();

        IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
        ingredient = ingredientDaoInterface.createIngredient(ingredient);

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(ingredient.toString())
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
        
        DaoFactory daoFactory = new DaoFactory();
        IngredientDaoInterface ingredientDaoInterface=daoFactory.getIngredientDaoInterface();
        ingredient = ingredientDaoInterface.updateIngredient(ingredient, id);

        Response response = Response
                .ok(ingredient.toString())

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

            /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Read Ingredient
--------------------------------------------------------------------------------------------------------------------------
    */
   
    @Path("/read/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readIngredient(@PathParam(value = "id") int id) {

        DaoFactory daoFactory = new DaoFactory();
        IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
        Ingredient ingredient = ingredientDaoInterface.findIngredientById(id);

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                .entity(ingredient.toString())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

}
