/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.IngredientDaoInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Utilisateur;

import org.json.JSONObject;

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

/**
 *
 * @author 33767
*/

@Path("/utilisateur/ingredient")
public class IngredientController {
    Ingredient ingredient = new Ingredient();
    DaoFactory daoFactory = new DaoFactory();
    IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
    Jsonb jsonb = JsonbBuilder.create();

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Liste Ingredient
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/enregistrez/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeIngredient() {  

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(ingredientDaoInterface.getListeIngredients())
                .build();

        daoFactory.closeEntityManagerFactory();
        return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Read Ingredient
:--------------------------------------------------------------------------------------------------------------------------
   */

    @Path("/read/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readIngredient(@PathParam(value = "id") int id) {

        Ingredient ingredient = ingredientDaoInterface.findIngredientById(id);

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (ingredient != null) {
            Response response = Response
            .status(Response.Status.ACCEPTED)
            .entity(ingredient.toString())
            .build();

        daoFactory.closeEntityManagerFactory();
        return response;
        } 

        //! ----------Si l'id n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucun ingrédient ne possédant cette id n'a pus être trouvé.")
                .build();
            return response;
        }

    }
        /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Find Ingredient By Libelle
:--------------------------------------------------------------------------------------------------------------------------
    */
    @Path("/read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readIngredientParNom(String stringUserData) {

        //µ Convertis String en un objet Json data
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //µ Récupération de l'ingrédient
        String jsonRecette = jSONObjectData.get("ingredient").toString();
        //µ Instancie dans la classe ingredient les infos récup
        Ingredient ingredient = jsonb.fromJson(jsonRecette, Ingredient.class);

        ingredient = ingredientDaoInterface.findIngredientByLibelle(ingredient);

        //. ----------Vérification du libellé.----------

        //$ ----------Si le libellé est bien trouvé.----------
        if (ingredient != null) {
            Response response = Response
            .status(Response.Status.ACCEPTED)
            .entity(ingredient.toString())
            .build();

        daoFactory.closeEntityManagerFactory();
        return response;
        } 

        //! ----------Si le libellé n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucun ingrédient ne possédant cette id n'a pus être trouvé.")
                .build();
            return response;
        }

    }
        /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Liste Ingredient By Id User
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/liste/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeRecettesByIdUser(@PathParam(value = "id") int id) {
       

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(ingredientDaoInterface.getListeIngredients())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

}