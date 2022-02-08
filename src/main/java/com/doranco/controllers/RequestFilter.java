/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
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
 * @author samha
 */
//ON/OFF pour les tests sur Postman !
@Provider
public class RequestFilter implements ContainerRequestFilter {

    DaoFactory daoFactory = new DaoFactory();

    UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();

    /**
     * From ContainerRequestFilter
     *
     * @param requestContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {       

            //Récupération des infos dans le header (autorisation)
            String basicAuth = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            System.out.println("<----- Basic Auth avant traitement : " + basicAuth + " ----->");
            // Garder uniquement le username et password encode en base64
            basicAuth = basicAuth.replace("Basic ", "");

            System.out.println("<----- Basic Auth après traitement : " + basicAuth + " ----->");
            // Decodage et recuperation de username et password
            String authDecode = new String(Base64.getDecoder().decode(basicAuth));

            String[] credentials = authDecode.split(":");

            System.out.println("<----- Auth Decode : " + authDecode + " ----->");
            String username = credentials[0];
            String password = credentials[1];

            Utilisateur user = new Utilisateur(username, password);

            //System.out.println("<----- Login de l'utilisateur ------>");
            user = utilisateurDaoInterface.login(user);

            if (user != null) {
                String urlPath = requestContext.getUriInfo().getPath();

                if (urlPath.contains("admin")) {
                    if (user.isAdmin()) {
                        return;
                    } else {
                        Response response = Response.status(Response.Status.FORBIDDEN).entity("Vous n'avez pas le statut d'administrateur.").build();
                        requestContext.abortWith(response);
                    }

                }
                return;
            }
            Response response = Response.status(Response.Status.FORBIDDEN).entity("Access refusé.").build();
            requestContext.abortWith(response);

        }
    }

