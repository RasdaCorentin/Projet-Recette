/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.RecetteDaoInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
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
--------------------------------------------------------------------------------------------------------------------------
                                                 Liste Recette
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/liste/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeRecettesByIdUser(@PathParam(value = "id") int id) {
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                //Ajouter to To string pour info ciblé
                .entity(recetteDaoInterface.getListeRecettesByIdUser(id))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }
    /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Liste Recette
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/enregistrez/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeRecette() {
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                //Ajouter to To string pour info ciblé
                .entity(recetteDaoInterface.getListeRecettes())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }
    /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Read Recette
--------------------------------------------------------------------------------------------------------------------------
     */
@Path("/read/{id}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response readRecette(@PathParam(value = "id") int id){
    recetteDaoInterface = daoFactory.getRecetteDaoInterface();
    recette = recetteDaoInterface.findRecetteById(id);
    
    //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                .entity(recette)
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
}
    /*
--------------------------------------------------------------------------------------------------------------------------
                                                Création Recette
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRecette(String stringUserData) {
        //Convertis String en un objet Json data
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //Recupération du recette
        String jsonRecette = jSONObjectData.get("recette").toString();
        //Recupération du user
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        // Instancie dans la classe recette les infos récup
         recette = jsonb.fromJson(jsonRecette, Recette.class);
        // Instancie dans la classe utilisateur les infos récup
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);
        

//      Lancement de la Methode Connect                 
        
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();
        recette = recetteDaoInterface.createRecette(recette, utilisateur);


//Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                .entity(recette)
                .build();

        return response;
    }
        /*
--------------------------------------------------------------------------------------------------------------------------
                                                Update Recette
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRecette(Recette recette, @PathParam(value = "id") int id) {        
        Response response = Response
                .ok(recetteDaoInterface.updateRecette(recette, id))
                .build();

        return response;
    }
}


