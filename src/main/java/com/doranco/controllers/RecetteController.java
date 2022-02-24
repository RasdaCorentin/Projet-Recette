/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.RecetteDaoInterface;
import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
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

@Path("/utilisateur/recette")
public class RecetteController {
    DaoFactory daoFactory = new DaoFactory();
    RecetteDaoInterface recetteDaoInterface;
    Recette recette;
    Jsonb jsonb = JsonbBuilder.create();

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Liste Recette By Id User
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/liste/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeRecettesByIdUser(@PathParam(value = "id") int id) {
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(recetteDaoInterface.getListeRecettesByIdUser(id))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }
    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Liste Recette
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/enregistrez/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeRecette() {
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(recetteDaoInterface.getListeRecettes())
                .build();

        daoFactory.closeEntityManagerFactory();
        return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Read Recette
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/read/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readRecette(@PathParam(value = "id") int id){
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();
        recette = recetteDaoInterface.findRecetteById(id);

            Response response = Response
                    .status(Response.Status.CREATED)
                    .entity(recette)
                    .build();

            daoFactory.closeEntityManagerFactory();
            return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Création Recette
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRecette(String stringUserData) {

        //µ Convertis String en un objet Json data
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //µ Récupération du recette
        String jsonRecette = jSONObjectData.get("recette").toString();
        //µ Récupération du user
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        //µ Instancie dans la classe recette les infos récup
        recette = jsonb.fromJson(jsonRecette, Recette.class);
        //µ Instancie dans la classe utilisateur les infos récup
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);

//      Lancement de la Méthode Connect                 
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();
        recette = recetteDaoInterface.createRecette(recette, utilisateur);

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(recette)
                .build();

        return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Update Recette
:--------------------------------------------------------------------------------------------------------------------------
    */

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRecette(String stringUserData) {

        //µ Convertis String en un objet Json data
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //µ Récupération du recette
        String jsonRecette = jSONObjectData.get("recette").toString();
        //µ Récupération du user
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        //µ Instancie dans la classe recette les infos récup
        recette = jsonb.fromJson(jsonRecette, Recette.class);
        //µ Instancie dans la classe utilisateur les infos récup
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);
        
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();
        recette = recetteDaoInterface.updateRecette(recette, utilisateur);

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (recette != null) {
            Response response = Response
            .status(Response.Status.ACCEPTED)
            .entity(recette)
            .build();

        daoFactory.closeEntityManagerFactory();
        return response;
        } 

        //! ----------Si l'id n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucune recette ne possédant cette id n'a pus être trouvé.")
                .build();
            return response;
        }

    }
        /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Delete Recette
:--------------------------------------------------------------------------------------------------------------------------
   */
    @PUT
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecetteUser (String stringUserData) {

        //µ Convertis String en un objet Json data
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //µ Récupération du recette
        String jsonRecette = jSONObjectData.get("recette").toString();
        //µ Instancie dans la classe recette les infos récup
        recette = jsonb.fromJson(jsonRecette, Recette.class);

        recetteDaoInterface = daoFactory.getRecetteDaoInterface();
        recette = recetteDaoInterface.deleteRecetteSSIng(recette);

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if ( recette != null) {
            Response response = Response
            .status(Response.Status.ACCEPTED)
            .entity("La recette a bien été supprimé.")
            .build();

        daoFactory.closeEntityManagerFactory();
        return response;
        } 

        //! ----------Si l'id n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucune recette n'a pu être trouvé.")
                .build();
            return response;
        }

    }
}