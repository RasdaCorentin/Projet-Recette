/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.RecetteInterface;
import com.doranco.dao.interfaces.UtilisateurInterface;
import com.doranco.entities.Recette;
import com.doranco.entities.RoleUtilisateur;
import com.doranco.entities.Utilisateur;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
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
 * @author elair
 */
@Path("/recette")
public class RecetteController {

    private final DaoFactory daoFactory;
    private final UtilisateurInterface utilisateurInterface;
    private final RecetteInterface recetteInterface;

    public RecetteController() {
        daoFactory = new DaoFactory();
        recetteInterface = daoFactory.getRecetteInterface();
        utilisateurInterface = daoFactory.getUtilisateurInterface();
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRecette(String stringJsonData) {
        //convertir string en json
        JSONObject jSONObjectData = new JSONObject(stringJsonData);
        //récupération de l'email
        String email = jSONObjectData.getString("email");

        //récup de la recette en string
        String jsonRecetteString = jSONObjectData.get("recette").toString();
        //utilisation de json-b
        Jsonb jsonb = JsonbBuilder.create();
        Recette recette = jsonb.fromJson(jsonRecetteString, Recette.class);
        Utilisateur user = utilisateurInterface.findUserByEmail(email);

        if (user != null) {
            recette.setUtilisateur(user);
            recette = recetteInterface.create(recette);
            if (recette != null) {
                Response response = Response.ok(recette).build();
                return response;
            }
        }

        Response response = Response.status(500).entity("Utilisateur non trouvé").build();
        daoFactory.closeEntityManagerFactory();

        return response;
    }

    @Path("/update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
//    public Response update(Recette recette) {
    public Response update(String stringJsonData){
        //convertir string en json
        JSONObject jSONObjectData=new JSONObject(stringJsonData);
        //récupération de l'email
        String email=jSONObjectData.getString("email");
        
       //récup de la recette en string
        String jsonRecetteString=jSONObjectData.get("recette").toString();
        //utilisation de json-b
        Jsonb jsonb=JsonbBuilder.create();
        Recette recette=jsonb.fromJson(jsonRecetteString, Recette.class);

        recette = recetteInterface.update(recette, recette.getId());
        if (recette != null) {
            return Response.ok(recette).build();
        }
        return Response.status(500).entity("Erreur update recette").build();
    }

//    @Path("/create-R")
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createRecette(Recette recette){
//        Utilisateur user=utilisateurInterface.findUserByEmail(email);
//        recette.setUtilisateur(user);
//        recette=recetteInterface.create(recette);
//        return Response.ok(recette).build();
//    }
    
//    @Path("/update-R/{id}")
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response update(Recette recette, @PathParam(value = "id")int id){
//        recette=new Recette();
//        Response response=Response
//                .ok(recetteInterface.update(recette, id))
//                .build();
//        return response;
//    }
}
