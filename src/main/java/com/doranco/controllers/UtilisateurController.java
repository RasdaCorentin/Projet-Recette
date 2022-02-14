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

import org.mindrot.jbcrypt.BCrypt;


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
        Utilisateur utilisateur = utilisateurDaoInterface.findUtilisateurById(id);
        utilisateurDaoInterface.deactivateUtilisateur(id);

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (utilisateur != null) {
            Response response = Response
                .ok( "L'utilisateur " + id + " à été désactivé avec succès." )
                .build();

        daoFactory.closeEntityManagerFactory();
        return response;
        } 

        //! ----------Si l'id n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucun utilisateur ne possédant cette id n'a pus être trouvé.")
                .build();
            return response;
        }

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
        Utilisateur utilisateur = utilisateurDaoInterface.findUtilisateurById(id);
        utilisateurDaoInterface.activateUtilisateur(id);

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (utilisateur != null) {
            Response response = Response
                .ok( "L'utilisateur " + id + " à été activé avec succès." )
                .build();

        daoFactory.closeEntityManagerFactory();
        return response;
        } 

        //! ----------Si l'id n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucun utilisateur ne possédant cette id n'a pus être trouvé.")
                .build();
            return response;
        }

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
        Utilisateur utilisateurBdd = utilisateurDaoInterface.findUtilisateurById(id);
        utilisateurDaoInterface.vanishUtilisateur(utilisateur, id);

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (utilisateurBdd != null) {
            Response response = Response

                .status(Response.Status.CREATED)
                .entity(utilisateurDaoInterface.getListeUtilisateurs())
                .build();

        daoFactory.closeEntityManagerFactory();
        return response;
        } 

        //! ----------Si l'id n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucun utilisateur ne possédant cette id n'a pus être trouvé.")
                .build();
            return response;
        }

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

        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        Utilisateur utilisateurBdd = utilisateurDaoInterface.findUtilisateurById(id);
        utilisateurDaoInterface.deleteUtilisateur(id);
        daoFactory.closeEntityManagerFactory();

        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (utilisateurBdd != null) {
            Response response = Response
                .status(Response.Status.ACCEPTED)
                .entity("Utilisateur id :" + id + " Supprimé")
                .build();
            daoFactory.closeEntityManagerFactory();
            return response;
        } 

        //! ----------Si l'id n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucun utilisateur ne possédant cette id n'a pus être trouvé.")
                .build();
            return response;
        }

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
    public Response createUtilisateur(String stringUserData) {
        //µ Convertis String en un objet Json data.
        JSONObject jSONObjectData = new JSONObject(stringUserData);
        //µ Récupération du user.
        String jsonUtilisateur = jSONObjectData.get("utilisateur").toString();
        //µ Instancie dans la classe utilisateur les infos récupérer.
        Utilisateur utilisateur = jsonb.fromJson(jsonUtilisateur, Utilisateur.class);
        //µ Lancement de la méthode Connect.
        
        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        System.out.println(utilisateur);
        //§ Je vérifie dans la base de donnée l'existence du nom que souhaite prendre l'utilisateur.
        EntityManager entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery( "SELECT user FROM Utilisateur user WHERE nom=:nom" );
        query.setParameter( "nom", utilisateur.getNom() );

        //. ----------Vérification du nom souhaité par l'utilisateur.----------

        //$ ----------Le nom n'est pas encore utilisé.----------
        if (query.getResultList().isEmpty()) {
            utilisateur.setRole(RoleUtilisateur.USER);
            utilisateur = utilisateurDaoInterface.createUtilisateur(utilisateur);
    
            Response response = Response
                    .status(Response.Status.CREATED)
                    .entity(utilisateur)
                    .build();
    
            daoFactory.closeEntityManagerFactory();
    
            return response;
        }

        //! ----------Le nom est déjà utilisé par un autre utilisateur.----------
        else {
                Response response = Response
                    .status(Response.Status.FORBIDDEN)
                    .entity("Le nom " + utilisateur.getNom() + " est déjà utilisé par un autre utilisateur.")
                    .build();
                return response;
        }

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
        Utilisateur utilisateurAConnecter = utilisateurDaoInterface.findUtilisateurByNom(utilisateur);

        //. ----------Vérification du faite que l'utilisateur existe dans la base de donnée.----------

        if (utilisateurAConnecter != null) {

            //§ Récupération du mot de passe entré par l'utilisateur pour la connexion.
            String passwordTemp = utilisateur.getPassword();
            String passwordHash = BCrypt.hashpw(passwordTemp, utilisateurAConnecter.getSalt());

            //. ----------Vérification des identifiants de l'utilisateur.----------

            if ( passwordHash.compareTo(utilisateurAConnecter.getPassword()) == 0 && utilisateurAConnecter.getNom().equals(utilisateur.getNom()) ) {

                //. ----------Je vérifie que l'utilisateur est bien nouveau sur le site.----------
                if (utilisateurAConnecter.getDateModif() == null) {

                    //? ----------Laisser passer A-38 donner à l'utilisateur.----------
                    utilisateur = utilisateurDaoInterface.connectUtilisateur(utilisateur);

                    Response response = Response
                            .status(Response.Status.ACCEPTED)
                            .entity(utilisateur)
                            .build();
                    return response;

                } else {
                    Response response = Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Vous n'êtes pas nouveau ! Voyez avec les administrateurs pour qu'elle raison vous avez été bannis !")
                        .build();
                    return response;
                }


            }

            //! ----------Erreur sur les identifiants.----------
            else {

                Response response = Response
                    .status(Response.Status.FORBIDDEN)
                    .entity("Vos identifiants sont incorrect !")
                    .build();
                return response;

            }

        }

        //! ----------Le nom d'utilisateur n'existe pas.----------
        Response response = Response
            .status(Response.Status.NOT_FOUND)
            .entity("Le nom " + utilisateur.getNom() + " n'est associé à aucun compte sur le site.")
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

        //§ Récupération de l'utilisateur qui va être modifié.
        Utilisateur utilisateurAModifier = utilisateurDaoInterface.findUtilisateurByNom(utilisateur);

        //. ----------Vérification du faite que l'utilisateur existe dans la base de donnée.----------

        if (utilisateurAModifier != null) {

            //§ Récupération du mot de passe entré par l'utilisateur pour la connexion.
            String passwordTemp = utilisateur.getPassword();
            String passwordHash = BCrypt.hashpw(passwordTemp, utilisateurAModifier.getSalt());

            //. ----------Vérification des identifiants de l'utilisateur.----------

            if ( passwordHash.compareTo(utilisateurAModifier.getPassword()) == 0 && utilisateurAModifier.getNom().equals(utilisateur.getNom()) ) {

                //. ----------Vérification du nouveau nom souhaité par l'utilisateur.----------

                //§ Je vérifie dans la base de donnée l'existence du nom que souhaite prendre l'utilisateur.
                EntityManager entityManager = daoFactory.getEntityManager();
                Query query = entityManager.createQuery( "SELECT user FROM Utilisateur user WHERE nom=:nom" );
                query.setParameter( "nom", utilisateur.getNom() );

                //$ ----------Le nom n'est pas encore utilisé.----------
                if (query.getResultList().isEmpty()) {
                    utilisateur = utilisateurDaoInterface.updateUtilisateur(utilisateur);
                }

                //! ----------Le nom est déjà utilisé.----------
                else {
                    Utilisateur utilisateurBdd = (Utilisateur) query.getResultList().get(0);
                    String nomUtilisateurBdd = utilisateurBdd.getNom();
                    String nomUtilisateurUpdate = utilisateur.getNom();

                    //$ ----------L'utilisateur souhaite conserver son nom.----------
                    if (nomUtilisateurBdd.equals(nomUtilisateurUpdate)) {
                        utilisateur = utilisateurDaoInterface.updateUtilisateur(utilisateur);
                    }

                    //! ----------Le nom est déjà utilisé par un autre utilisateur.----------
                    else {
                        Response response = Response
                            .status(Response.Status.FORBIDDEN)
                            .entity("Le nom " + nomUtilisateurBdd + " est déjà utilisé par un autre utilisateur.")
                            .build();
                        return response;
                    }

                }

            //! ----------Erreur sur les identifiants.----------
            } else {
                Response response = Response
                    .status(Response.Status.FORBIDDEN)
                    .entity("Vos identifiants sont incorrect !")
                    .build();
                return response;
            }

            //. ----------Création de l'utilisateur.----------

            Response response = Response
                    .status(Response.Status.CREATED)
                    .entity(utilisateur)
                    .build();
            return response;

        }

        //! ----------Le nom d'utilisateur n'existe pas.----------
        Response response = Response
            .status(Response.Status.NOT_FOUND)
            .entity("Le nom " + utilisateur.getNom() + " n'est associé à aucun compte sur le site.")
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
        //. ----------Vérification de l'id.----------

        //$ ----------Si l'id est bien trouvé.----------
        if (utilisateur != null) {
            Response response = Response
            .status(Response.Status.CREATED)
            .entity(utilisateur)
            .build();

        daoFactory.closeEntityManagerFactory();
        return response;
        } 

        //! ----------Si l'id n'est pas trouvé.----------
        else {
            Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity("Aucun utilisateur ne possédant cette id n'a pus être trouvé.")
                .build();
            return response;
        }

    }

}
