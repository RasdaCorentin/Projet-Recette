/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
import com.doranco.entities.RoleUtilisateur;
import com.doranco.entities.Utilisateur;
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
@Path("/v1/utilisateur")
public class UtilisateurController {
   
    /**
     * Code William et Agnieszka
     */
    private DaoFactory daoFactory;
    
    private UtilisateurDaoInterface utilisateurDaoInterface;
    
    public UtilisateurController () {
        daoFactory = new DaoFactory();
        utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
    }
       
    
    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlisteUtilisateur () {

        Response response = Response
                .ok(utilisateurDaoInterface.getListeUtilisateur())
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
    }
     
    
    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response nouvelUtilisateur (Utilisateur user) {

//        Response response = Response
//                .status(Response.Status.FORBIDDEN)
//                .entity("Bonjour, vous n'êtes pas inscrit sur le site. Veuillez vous enregistrer SVP.")
//                .build();
        
        Response response = Response
                .status(Response.Status.OK)
                .entity(utilisateurDaoInterface.create(user))
                .build();

        //Donner la possibilité de s'enregistrer ensuite
        
        daoFactory.closeEntityManagerFactory();
        
        return response;
    }
    
       
    @POST 
    @Produces(MediaType.APPLICATION_JSON) 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Path("/create")
    public Response createUser(Utilisateur user) {

//        Utilisateur nouvelUtilisateur = new Utilisateur("Hulk","123", "brucebanner@test.org", RoleUtilisateur.USER);
        
        Response response = Response
                .ok(utilisateurDaoInterface.create(user))
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
    }
    
    /**
     * Version de l'update réservée aux admins, pour modifier tous les params
     * @param utilisateur
     * @param id
     * @return 
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateAdmin(Utilisateur utilisateur, @PathParam(value = "id") int id) {

//        utilisateur = new Utilisateur("Username update", "mdp update", "email update3", RoleUtilisateur.USER, false);

        Response response = Response
                .ok(utilisateurDaoInterface.updateUtilisateur(utilisateur, id))
                .build();

        return response;

    }
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON) 
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam(value = "id") int id) {       
     
        Response response = Response
                .ok(utilisateurDaoInterface.deleteUtilisateur(id))
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
        
    }
}
