/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.UtilisateurDaoInterface;
import com.doranco.entities.RoleUtilisateur;
import com.doranco.entities.Utilisateur;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;

/**
 *
 * @author Admin
 */
//A ajouter pour implémenter  les conditions
@Provider
public class RequestFilter implements ContainerRequestFilter {

    /*
--------------------------------------------------------------------------------------------------------------------------
                                              Verif Rôle Authentification 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String urlPath = requestContext.getUriInfo().getPath();

        if (urlPath.contains("enregistrez")) {
            return;
        }

        System.out.println("Je prépare les autorisations...");

        /*Je récupère la valeur de l'Authorization dans le Headers 
et retire le basic ce qui me donne un code crypté que je décode*/
        String basicAuth = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        basicAuth = basicAuth.replace("Basic ", "");
        String authDecode = new String(Base64.getDecoder().decode(basicAuth));
//       System.out.println(authDecode);      
        String[] credentials = authDecode.split(":");
        String nom = credentials[0];
        String password = credentials[1];


/*J'enregistre le username et password 
Les utilise dans la fonction login 
S'il nous renvoie un utilisateur existant
Si utilisateur ok utilisateur = utilisateur
Sinon utilisateur == null
alors je vérifie s'il est Admin*/
        Utilisateur utilisateur = new Utilisateur(nom, password);
        DaoFactory daoFactory = new DaoFactory();
        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
        utilisateur = utilisateurDaoInterface.loginUtilisateur(utilisateur);

//Ici je créer les condition d'authentification,
//J'ajoute une condition pour enregistrez les nouveau utilisateurs

        if ((utilisateur != null)) {
            if (urlPath.contains("admin")) {
                if (utilisateur.isAdmin()) {
                    return;
                } else {
                    Response response = Response.status(Response.Status.FORBIDDEN).entity("T'es pas Admin COCO").build();
                    requestContext.abortWith(response);
                }
            }
            return;
        }
        Response response = Response.status(Response.Status.FORBIDDEN).entity("Vous devez vous enreegistrez")
                .build();
        requestContext.abortWith(response);
    }
}
