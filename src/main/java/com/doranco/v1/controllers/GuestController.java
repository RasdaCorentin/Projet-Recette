/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.v1.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
import com.doranco.interfaces.RecetteInterface;
import com.doranco.interfaces.UtilisateurInterface;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author Admin
 */
@Path("/v1/guest")
public class GuestController {

    private final DaoFactory daoFactory;
    private final UtilisateurInterface utilisateurInterface;

    public GuestController() {
        daoFactory = new DaoFactory();
        utilisateurInterface = daoFactory.getUtilisateurInterface();
    }

    @POST
    @Path("/create-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Utilisateur user) {
        user = utilisateurInterface.createUtilisateur(user);
        if (user == null) {
            return Response.status(500).entity("Erreur").build();
        }
        return Response.ok(user).build();
    }
}
