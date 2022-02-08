/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.IngredientDaoInterface;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 33767
 */
@Path("/utilisateur/ingredient")
public class IngredientController {

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

        DaoFactory daoFactory = new DaoFactory();

        IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
        

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                //Ajouter to To string pour info ciblé
                .entity(ingredientDaoInterface.getListeIngredients())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }
}
