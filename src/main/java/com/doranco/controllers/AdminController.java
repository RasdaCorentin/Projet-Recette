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
@Path("/v1/utilisateur/admin")
public class AdminController {

    private DaoFactory daoFactory;

    private UtilisateurDaoInterface utilisateurDaoInterface;

    private RecetteDaoInterface recetteDaoInterface;

    private IngredientDaoInterface ingredientDaoInterface;

    public AdminController() {
        daoFactory = new DaoFactory();
        utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        recetteDaoInterface = daoFactory.getRecetteDaoInterface();
        ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
    }

    /**
     * ************************UTILISATEUR*******************************************
     */
    
    /**
     * Permet de lister les utilisateurs
     *
     * @return
     */
    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlisteUtilisateur() {

        Response response = Response
                .ok(utilisateurDaoInterface.getListeUtilisateur())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

    /**
     * Permet de créer un utilisateur standard
     *
     * @param user
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create-user")
    public Response createUser(Utilisateur user) {

//        Utilisateur nouvelUtilisateur = new Utilisateur("Hulk","123", "brucebanner@test.org", RoleUtilisateur.USER);
        Response response = Response
                .ok(utilisateurDaoInterface.create(user))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

    /**
     * Permet à un admin de modifier tout compte utilisateur grâce à l'id depuis
     * le nom d'utilisateur jusqu'au rôle, ainsi que le désactiver si besoin
     *
     * @param utilisateur
     * @param id
     * @return
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateAdmin(Utilisateur utilisateur, @PathParam(value = "id") int id) {

//        utilisateur = new Utilisateur("Username update", "mdp update", "email update", RoleUtilisateur.USER, true);

        Response response = Response
                .ok(utilisateurDaoInterface.updateUtilisateur(utilisateur, id))
                .build();

        return response;

    }

    /**
     * Permet de supprimer un compte utilisateur, en utilisant l'id
     *
     * @param user
     * @param id
     * @return
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteUser(Utilisateur user, @PathParam(value = "id") int id) {

        Response response = Response
                .ok(utilisateurDaoInterface.deleteUtilisateur(id))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;

    }

    /**
     * *********************************RECETTE********************************************
     */
    
    /**
     * Pour faire des tests avec Postman
     *
     * @param recette
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/creation-recette")
    public Response creationRecette(Recette recette) {
        return Response.ok(recette).build();
    }

    /**
     * Code original version Corentin Dok Rasda Agent Smith factorisé par mes
     * soins. Il permet de créer une recette et de la relier à un utilisateur
     * grâce à l'id de ce dernier, au moment de la création. LE TOP : Ca
     * fonctionne très bien !
     *
     * @param recette
     * @param id
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create-recette/{id}")
    public Response createRecette(Recette recette, @PathParam(value = "id") int id) {

//          Pour générer en dur une recette, sinon la créer dans Postman
//        Recette nouvelleRecette = new Recette("Dango", "Recette sucrée,un dessert traditionnel de la cuisine japonaise", "src/img/dango.jpg");
//        RecetteDaoInterface recetteDaoInterface = daoFactory.getRecetteDaoInterface();
        Utilisateur utilisateur = utilisateurDaoInterface.findUserById(id);

        recette.setUtilisateur(utilisateur);

        Response response = Response
                .ok(recetteDaoInterface.createRecette(recette))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

    //En cours de développement
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update-recette/{id}")
    public Response updateRecette(Recette recette, @PathParam(value = "id") int id) {

        Response response = Response
                .ok(recetteDaoInterface.updateRecette(recette, id))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;

    }

    //En cours de développement
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete-recette/{id}")
    public Response deleteRecette(@PathParam(value = "id") int id) {

        Response response = Response
                .ok(recetteDaoInterface.deleteRecette(id))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;

    }

    /**
     * ******************************INGREDIENT**************************************
     */
    /**
     * Permet de créer un ingrédient en le liant à une recette, grâce à l'id de
     * celle-ci
     *
     * @param ingredient
     * @param id
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create-ingredient/{id}")
    public Response createIngredient(Ingredient ingredient, @PathParam(value = "id") int id) {

//        Ingredient nouvelIngredient = new Ingredient();
//        
//        IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
        Recette recette = recetteDaoInterface.findRecetteById(id);

        ingredient.setRecette(recette);

        Response response = Response
                .ok(ingredientDaoInterface.createIngredient(ingredient))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;

    }

    //En cours de développement
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update-ingredient/{id}")
    public Response updateIngredient(Ingredient ingredient, @PathParam(value = "id") int id) {

        Response response = Response
                .ok(ingredientDaoInterface.updateIngredient(ingredient, id))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;

    }

    //En cours de développement
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete-ingredient/{id}")
    public Response deleteIngredient(@PathParam(value = "id") int id) {

        Response response = Response
                .ok(ingredientDaoInterface.deleteIngredient(id))
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;

    }

}
