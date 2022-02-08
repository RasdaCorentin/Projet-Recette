/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.v1.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.entities.Ingredient;
import com.doranco.interfaces.IngredientInterface;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Path("/v1/secured/ingredient")
public class IngredientController {

    private final DaoFactory daoFactory;
    private final IngredientInterface ingredientInterface;

    public IngredientController() {
        this.daoFactory = new DaoFactory();
        this.ingredientInterface = this.daoFactory.getIngredientInterface();
    }

    @POST
    @Path("/create-ingredient")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIngredient(Ingredient ingredient) {
        ingredient = ingredientInterface.createIngredient(ingredient);
        if (ingredient == null) {
            return Response.status(500).entity("Erreur creation ingredient").build();
        }
        return Response.ok(ingredient).build();
    }

}
