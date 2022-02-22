/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.IngredientDaoInterface;
import com.doranco.entities.Ingredient;

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
                                                % Create Ingredient
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIngredient(Ingredient ingredient) {

        ingredient = ingredientDaoInterface.createIngredient(ingredient);

        Response response = Response
            .status(Response.Status.CREATED)
            .entity(ingredient.toString())
            .build();

        daoFactory.closeEntityManagerFactory();
        return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Update Ingredient
:--------------------------------------------------------------------------------------------------------------------------
   */
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredient(Ingredient ingredient, @PathParam(value = "id") int id) {

        ingredient = ingredientDaoInterface.updateIngredient(ingredient, id);

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (ingredient != null) {
            Response response = Response
            .status(Response.Status.ACCEPTED)
            .entity(ingredient)
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
                                                % Delete Ingredient
:--------------------------------------------------------------------------------------------------------------------------
   */
    @DELETE
    @Path("admin/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteIngredient(Ingredient ingredient, @PathParam(value = "id") int id) {

        ingredient = ingredientDaoInterface.updateIngredient(ingredient, id);
        ingredientDaoInterface.deleteIngredient(id);

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (ingredient != null) {
            Response response = Response
            .status(Response.Status.ACCEPTED)
            .entity("L'ingrédient ayant l'id : " + id + " a bien été supprimé.")
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

}