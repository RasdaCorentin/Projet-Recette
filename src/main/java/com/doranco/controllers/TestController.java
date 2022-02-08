/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.IngredientDaoInterface;
import com.doranco.dao.interfaces.RecetteDaoInterface;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Recette;
import com.doranco.entities.RoleUtilisateur;
import com.doranco.entities.Utilisateur;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
 * @author samha
 */
@Path("/test")
public class TestController {
    
    /**
     * Pour pouvoir utiliser plus facilement le DAO au cours des tests
     */
    private DaoFactory daoFactory;
    
    private UtilisateurDaoInterface utilisateurDaoInterface;
    
    private RecetteDaoInterface recetteDaoInterface;
    
    private IngredientDaoInterface ingredientDaoInterface;
    
    public TestController () {
        daoFactory = new DaoFactory();
        utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();
        ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
    }
    
        
/*****************BIENVENUE DANS LA ZONE DE TESTS !****************************/    

/********************TOUT CE QUI CONCERNE LA METHODE GET***********************/    
    
    /**
     * Test de connexion pour tous les utilisateurs
     * @return 
     */
    @GET 
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/user")
    public Response connexionSimple() {
        
        Response response = Response
                .status(Response.Status.OK)
                .entity("Bonjour, vous êtes connecté en tant que simple utilisateur.")
                .build();
        
        return response;
    }
    
    /**
     * Test de co qui ne retourne un msg qu'aux admins
     * @return 
     */
    @GET 
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/admin/user")
    public Response connexionAdmin() {
        
        Response response = Response
                .status(Response.Status.OK)
                .entity("Bonjour, vous êtes connecté en mode administrateur.")
                .build();

        return response;
    }   
 
    
/*****************TOUT CE QUI CONCERNE LA METHODE POST*************************/    
    
    /**
     * Permet d'afficher une recette avec ses ingredients quand on est en mode utilisateur/admin
     * @param stringJsonData
     * @return 
     */
    @POST
    @Path("/create-recette-json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRecetteWithJsonB (String stringJsonData) {
        
        JSONObject data = new JSONObject(stringJsonData);
        
        System.out.println("<----- Impression de l'objet JSON data ----->");
        System.out.println(data);

        String jsonRecette = data.get("recette").toString();

        System.out.println("Affichage de recette : " + jsonRecette);

        Jsonb jsonb = JsonbBuilder.create();

        Recette recette = jsonb.fromJson(jsonRecette, Recette.class);
        
        List<Ingredient> listeIngredients = new ArrayList<>(recette.getListeIngredients());
        
        System.out.println(listeIngredients);
                
        System.out.println("<----- Affichage de l'objet recette ----->");
        System.out.println(recette);

        
        return Response.ok(recette).build();
        
    }
    
    /**
     * Permet d'afficher une recette avec ses ingrédients en mode "invité" (/new dans l'url)
     * @param stringJsonData
     * @return 
     */
    @POST
    @Path("/new/create-recette-json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewRecetteWithJson (String stringJsonData) {
        JSONObject data = new JSONObject(stringJsonData);
        
        System.out.println("<----- Impression de l'objet JSON data ----->");
        System.out.println(data);

        String jsonRecette = data.get("recette").toString();

        System.out.println("Affichage de recette : " + jsonRecette);

        Jsonb jsonb = JsonbBuilder.create();

        Recette recette = jsonb.fromJson(jsonRecette, Recette.class);
        
        List<Ingredient> listeIngredients = new ArrayList<>(recette.getListeIngredients());
        
        System.out.println(listeIngredients);
    
        
        System.out.println("<----- Affichage de l'objet recette ----->");
        System.out.println(recette);

        
        return Response.ok(recette).build();
        
    }
    
    
    
    /**
     * Permet d'afficher une recette avec ses ingredients quand on est en mode utilisateur/admin
     * @param stringJsonData
     * @return 
     */
    @POST
    @Path("/create-user-json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response afficherListeRecettesByUserWithJsonB (String stringJsonData) {
        
        JSONObject data = new JSONObject(stringJsonData);
        
        System.out.println("<----- Impression de l'objet JSON data ----->");
        System.out.println(data);

        String jsonUser = data.get("utilisateur").toString();

        System.out.println("Affichage de l'utilisateur : " + jsonUser);

        Jsonb jsonb = JsonbBuilder.create();

        Utilisateur user = jsonb.fromJson(jsonUser, Utilisateur.class);
        
        List<Recette> listeRecettes = new ArrayList<>(user.getListeRecettes());
        
        System.out.println(listeRecettes);
                
        System.out.println("<----- Affichage de l'objet recette ----->");
        System.out.println(user);

        
        return Response.ok(user).build();
        
    }
    
    
    
    
}
