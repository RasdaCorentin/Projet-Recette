/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.v1.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.entities.Recette;
import com.doranco.interfaces.RecetteInterface;
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
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Admin
 */
@Path("/v1/secured/recette")
public class RecetteController {

    private final DaoFactory daoFactory;
    private final UtilisateurInterface utilisateurInterface;
    private final RecetteInterface recetteInterface;

    public RecetteController() {
        daoFactory = new DaoFactory();
        utilisateurInterface = daoFactory.getUtilisateurInterface();
        recetteInterface = daoFactory.getRecetteInterface();
    }

    @POST
    @Path("/create-recette")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRecette(String stringJsonData) {

        JSONObject jSONObjectData = new JSONObject(stringJsonData);
        String email = jSONObjectData.getString("email");
          String jsonRecetteString = jSONObjectData.get("recette").toString();
         Jsonb jsonb = JsonbBuilder.create();
        Recette recette = jsonb.fromJson(jsonRecetteString, Recette.class);
        recette = recetteInterface.createRecette(recette, email);

        if (recette != null) {
            return Response.ok(recette).build();
        }
        return Response.status(500).entity("Echec creation de la recette").build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findAllRecette() {
        List<Recette> listRecettes = recetteInterface.findAllRecettes();

        return Response.ok(listRecettes).build();
    }

    @PUT
    @Path("/update-recette")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRecette(Recette recette) {

        recette = recetteInterface.updateRecette(recette);

        if (recette != null) {
            return Response.ok(recette).build();
        }
        return Response.status(500).entity("Echec update de la recette").build();
    }

    @DELETE
    @Path("/delete-recette/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecette(@PathParam(value = "id") int id) {
        String result = recetteInterface.deleteRecette(id);
        return Response.ok(result).build();
    }
}
