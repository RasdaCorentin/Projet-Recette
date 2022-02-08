/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.UtilisateurInterface;
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
@Provider
public class RequestFilter implements ContainerRequestFilter {

  @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String urlPath = requestContext.getUriInfo().getPath();
        if(urlPath.contains("register")){
            return;
        }
        
        System.out.println("Execution de ContainerRequestFilter premier");
        // Récuperation de la valeur de l'entete Authorization
        String basicAuth = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        // On garde uniquement le nom et mdp encodé en base64
        basicAuth = basicAuth.replace("Basic ", "");
        // Décodage et récupération du nom et mdp
        String authDecode = new String(Base64.getDecoder().decode(basicAuth));

        String[] credentials = authDecode.split(":");

        String nom = credentials[0];
        String mdp = credentials[1];

        Utilisateur user = new Utilisateur(nom, mdp);

        DaoFactory daoFactory = new DaoFactory();

        UtilisateurInterface utilisateurInterface = daoFactory.getUtilisateurInterface();

        user = utilisateurInterface.login(user);

        if (user != null) {
            
            if (urlPath.contains("admin")) {
                if (user.isAdmin()) {
                    return;
                } else {
                    Response response = Response.status(Response.Status.FORBIDDEN).entity("Vous n'êtes pas un Admin").build();
                    requestContext.abortWith(response);
                }

            }
            return;
        }
        Response response = Response.status(Response.Status.FORBIDDEN).entity("Accès refusé").build();
        requestContext.abortWith(response);
    }
    
}
