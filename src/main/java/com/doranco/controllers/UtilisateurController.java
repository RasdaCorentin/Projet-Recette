/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.doranco.controllers;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.UtilisateurDaoInterface;
import com.doranco.entities.RoleUtilisateur;
import com.doranco.entities.Utilisateur;

import org.json.JSONObject;

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
import jakarta.ws.rs.core.Response.Status;

/**
 *
 * @author 33767
*/
@Path("/utilisateur")
public class UtilisateurController {

    Jsonb jsonb = JsonbBuilder.create();

    /*
.-----------------------------------------------ADMIN COMMAND--------------------------------------------------------------

:--------------------------------------------------------------------------------------------------------------------------
                                                % Liste Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
*/

    @Path("/admin/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeUtilisateur() {

        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        Response response = Response
                .status(Response.Status.CREATED)
                .entity(utilisateurDaoInterface.getListeUtilisateurs())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Deactivate Utilisateur 
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/admin/deactivate/{id}")
    @PUT
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response deactivateUserBd( @PathParam( value = "id" ) int id ) {

            DaoFactory daoFactory = new DaoFactory();
            UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
            utilisateurDaoInterface.deactivateUtilisateur(id);

            Response response = Response
                    .ok( "L'utilisateur " + id + " à été désactivé avec succès." )
                    .build();

            daoFactory.closeEntityManagerFactory();
            return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Activate Utilisateur 
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/admin/activate/{id}")
    @PUT
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response activateUserBd( Utilisateur user, @PathParam( value = "id" ) int id ) {

            DaoFactory daoFactory = new DaoFactory();
            UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
            utilisateurDaoInterface.activateUtilisateur(id);

            Response response = Response
                    .ok( "L'utilisateur " + id + " à été activé avec succès." )
                    .build();

            daoFactory.closeEntityManagerFactory();
            return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Vanish Utilisateur 
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/admin/vanish/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response vanishUtilisateur(Utilisateur utilisateur, @PathParam(value = "id") int id) {

        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        utilisateurDaoInterface.vanishUtilisateur(utilisateur, id);

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(utilisateurDaoInterface.getListeUtilisateurs())
                .build();

        daoFactory.closeEntityManagerFactory();
        return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Delete Utilisateur 
:--------------------------------------------------------------------------------------------------------------------------
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

    /*
.---------------------------------------------ADMIN / NEW USER COMMAND-----------------------------------------------------

:--------------------------------------------------------------------------------------------------------------------------
                                                % Création Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/enregistrez")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUtilisateur(Utilisateur utilisateur) {
        DaoFactory daoFactory = new DaoFactory();

        utilisateur.setRole(RoleUtilisateur.USER);
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        utilisateur = utilisateurDaoInterface.createUtilisateur(utilisateur);

        Response response = Response
                .status(Response.Status.CREATED)
                .entity("Bienvenue : " + utilisateur.toString() + "Tu dois maintenant allez te connecter.")
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Connecter Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/enregistrez/connect")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectUtilisateur(String stringUserData) {
        //µ Convertis String en un objet Json data.
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //µ Récupération du user.
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        //µ Instancie dans la classe utilisateur les infos récupérer.
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);
        //µ Lancement de la méthode Connect.
        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        utilisateur = utilisateurDaoInterface.connectUtilisateur(utilisateur);

        Response response = Response
                .status(Response.Status.CREATED)
                .entity("Bienvenue : " + utilisateur.toString() + " Tu peux maintenant publier tes recettes.")
                .build();
        return response;
    }

    /*
.---------------------------------------------ADMIN / USER COMMAND--------------------------------------------------------

:--------------------------------------------------------------------------------------------------------------------------
                                                % Update Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/user/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUtilisateur(String stringUserData) {
        //µ Convertis String en un objet Json data.
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //µ Récupération du user.
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        //µ Instancie dans la classe utilisateur les infos récupérer.
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);
        //µ Lancement de la méthode Update.
        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();

        //§ Je vérifie dans la base de donnée l'existence du nom que souhaite prendre l'utilisateur.
        EntityManager entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery( "SELECT user FROM Utilisateur user WHERE nom=:nom" );
        query.setParameter( "nom", utilisateur.getNom() );

        //. ----------Les vérifications.----------

        //$ ----------Le nom n'est pas encore utilisé.----------
        if (query.getResultList().isEmpty()) {
            utilisateur = utilisateurDaoInterface.updateUtilisateur(utilisateur);
        }
        //$ ----------Le nom est déjà utilisé.----------
        else {
            Utilisateur utilisateurBdd = (Utilisateur) query.getResultList().get(0);
            String nomUtilisateurBdd = utilisateurBdd.getNom();
            String nomUtilisateurUpdate = utilisateur.getNom();

            //$ ----------L'utilisateur souhaite conserver son nom.----------
            if (nomUtilisateurBdd.equals(nomUtilisateurUpdate)) {
                utilisateur = utilisateurDaoInterface.updateUtilisateur(utilisateur);
            }
            //$ ----------Le nom est déjà utilisé par un autre utilisateur.----------
            else {
                Response response = Response
                    .status(Status.FORBIDDEN)
                    .entity( "Le nom " + nomUtilisateurBdd + " est déjà utilisé par un autre utilisateur." )
                    .build();
                return response;
            }
        }

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(utilisateur.toString())
                .build();
        return response;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Read Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Path("/read/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUtilisateur(@PathParam(value = "id") int id) {

        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        Utilisateur utilisateur = utilisateurDaoInterface.findUtilisateurById(id);

        Response response = Response
                .status(Response.Status.CREATED)
                .entity(utilisateur.toString())
                .build();

        daoFactory.closeEntityManagerFactory();

        return response;
    }

}