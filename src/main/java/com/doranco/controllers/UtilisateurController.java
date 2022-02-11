/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.UtilisateurDaoInterface;
import com.doranco.entities.RoleUtilisateur;
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
@Path("/utilisateur")
public class UtilisateurController {

    Jsonb jsonb = JsonbBuilder.create();

//---------------------------------------------ADMIN COMMAND-------------------------------------------------
/*
--------------------------------------------------------------------------------------------------------------------------
                                                 Liste Utilisateur
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeUtilisateur() {

        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                //Ajouter to To string pour info ciblé
                .entity(utilisateurDaoInterface.getListeUtilisateurs())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Disconnect Utilisateur 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/admin/disconnect/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response disconnectUtilisateur(Utilisateur utilisateur, @PathParam(value = "id") int id) {

        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        utilisateurDaoInterface.disconnectUtilisateur(utilisateur, id);

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                .entity(utilisateurDaoInterface.getListeUtilisateurs())
                .build();

        daoFactory.closeEntityManagerFactory();
        return response;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Delete Utilisateur 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/admin/deleteU/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUtilisateur(@PathParam(value = "id") int id) {
        DaoFactory daoFactory = new DaoFactory();

        UtilisateurDaoInterface UtilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();

        UtilisateurDaoInterface.deleteUtilisateur(id);
        daoFactory.closeEntityManagerFactory();

        Response response = Response
                .status(Response.Status.CREATED)
                .entity("Utilisateur id :" + id + " Supprimé")
                .build();
        return response;
    }

//---------------------------------------------ADMIN / NEW USER COMMAND-------------------------------------------------
/*
--------------------------------------------------------------------------------------------------------------------------
                                                Création Utilisateur
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/enregistrez")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUtilisateur(String stringUserData) {
        //Convertis String en un objet Json data
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //Recupération du user
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        // Instancie dans la classe utilisateur les infos récup
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);
        
        DaoFactory daoFactory = new DaoFactory();
        utilisateur.setRole(RoleUtilisateur.USER);
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        utilisateur = utilisateurDaoInterface.createUtilisateur(utilisateur);

        //Creation daoFactory.closeEntityManagerFactory();d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                .entity("Bienvenue : " + utilisateur + "Tu dois maintenant te connecter")
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                Connecter Utilisateur
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/enregistrez/connect")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectUtilisateur(String stringUserData) {
        //Convertis String en un objet Json data
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //Recupération du user
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        // Instancie dans la classe utilisateur les infos récup
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);
        //Lancement de la Methode Connect 
        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        utilisateur = utilisateurDaoInterface.connectUtilisateur(utilisateur);

        Response response = Response
                .status(Response.Status.CREATED)
                .entity("Bienvenue : " + utilisateur.toString() + " Tu peux maintenant publier tes recettes")
                .build();

        return response;
    }

//---------------------------------------------ADMIN / USER COMMAND-------------------------------------------------
/*
--------------------------------------------------------------------------------------------------------------------------
                                                Update Utilisateur
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/user/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUtilisateur(String stringUserData) {
        //Convertis String en un objet Json data
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //Recupération du user
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        // Instancie dans la classe utilisateur les infos récup
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);
        //Lancement de la Methode Update 
        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        utilisateur = utilisateurDaoInterface.updateUtilisateur(utilisateur);

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                .entity(utilisateur.toString())
                .build();

        return response;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Read Utilisateur
--------------------------------------------------------------------------------------------------------------------------
     */
    @Path("/read/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUtilisateur(@PathParam(value = "id") int id) {

        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        Utilisateur utilisateur = utilisateurDaoInterface.findUtilisateurById(id);

        //Creation d'une réponse
        Response response = Response
                .status(Response.Status.CREATED)
                .entity(utilisateur.toString())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }
}
