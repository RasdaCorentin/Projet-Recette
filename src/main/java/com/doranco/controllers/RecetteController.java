/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.RecetteDaoInterface;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
import com.doranco.entities.Recette;
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
@Path("/v1/recette")
public class RecetteController {
    
    private DaoFactory daoFactory;
    
    private RecetteDaoInterface recetteDaoInterface;
    
    private UtilisateurDaoInterface utilisateurDaoInterface;
    
    public RecetteController () {
        daoFactory = new DaoFactory();
        utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();
    }
    
    
    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlisteRecette () {

        Response response = Response
                .ok(recetteDaoInterface.getListeRecette())
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
    }
    
    /***
     * Création simple pour un utilisateur std
     * @param recette
     * @param id
     * @return 
     */
    @POST 
    @Produces(MediaType.APPLICATION_JSON) 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Path("/create/{id}")
    public Response createRecette(Recette recette, @PathParam(value = "id") int id) {
        
        Utilisateur user = utilisateurDaoInterface.findUserById(id);
        
        System.out.println("AFFICHAGE DE L'UTILISATEUR TROUVE : " + user);
        
        recette.setUtilisateur(user);
        
        Response response = Response
                .ok(recetteDaoInterface.createRecette(recette))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
      
    }
  
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateRecette(Recette recette, @PathParam(value = "id") int id) {
//        
//        recette = new Recette("Libelle update", "Description update", "urlImage update");

        Response response = Response
                .ok(recetteDaoInterface.updateRecette(recette, id))
                .build();
        
        return Response.ok(recette).build();
        
    }
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteRecette(@PathParam(value = "id") int id) {
        
        Response response = Response
                .ok(recetteDaoInterface.deleteRecette(id))
                .build();

        daoFactory.closeEntityManagerFactory();
        
        return response;
        
    }
    
}
