/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.v1.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.entities.Utilisateur;
import com.doranco.interfaces.UtilisateurInterface;
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
 * @author Admin
 */
@Path("/v1/secured/utilisateur")
public class UtilisateurController {

    private final DaoFactory daoFactory;
    private final UtilisateurInterface utilisateurInterface;

    public UtilisateurController() {
        daoFactory = new DaoFactory();
        utilisateurInterface = daoFactory.getUtilisateurInterface();
    }

    @GET
    @Path("/find-all-utilisateurs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUtilisateurs() {
        return Response.ok(utilisateurInterface.findAllUtilisateurs()).build();
    }

    @PUT
    @Path("/update-utilisateur")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUtilisateur(String stringJsonData) {
        JSONObject jSONObjectData = new JSONObject(stringJsonData);
        String email = jSONObjectData.getString("email");
         String jsonUtilisateurString = jSONObjectData.get("utilisateur").toString();
             Jsonb jsonb = JsonbBuilder.create();
        Utilisateur user = jsonb.fromJson(jsonUtilisateurString, Utilisateur.class);
        user = utilisateurInterface.updateUtilisateur(user, email);
        if (user != null) {
            return Response.ok(user).build();
        }
        return Response.status(500).entity("Echec update utilisateur").build();
    }

    @DELETE
    @Path("/delete-utilisateur/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecette(@PathParam(value = "id") int id) {
        String result = utilisateurInterface.deleteUtilisateur(id);
        return Response.ok(result).build();
    }

}
