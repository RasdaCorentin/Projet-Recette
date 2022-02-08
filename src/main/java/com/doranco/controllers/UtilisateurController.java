/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.controllers;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.RecetteInterface;
import com.doranco.dao.interfaces.UtilisateurInterface;
import com.doranco.entities.RoleUtilisateur;
import com.doranco.entities.Utilisateur;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
@Path("/user")
public class UtilisateurController {
    private final DaoFactory daoFactory;
    private final UtilisateurInterface utilisateurInterface;
    
    public UtilisateurController(){
        daoFactory=new DaoFactory();
        utilisateurInterface=daoFactory.getUtilisateurInterface();
    }
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(){
        Response response = Response
                .status(Response.Status.OK)
                .entity("Bonjour")
                .build();
        
        return response;
    }
    
    @Path("/admin/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloAdmin(){
        Response response = Response
                .status(Response.Status.OK)
                .entity("Bonjour Admin")
                .build();
              
        return response;
    }
    
    @Path("/create-user")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUserBD(){
        Utilisateur user1=new Utilisateur("Orianne","orianne@orange.fr","123", RoleUtilisateur.ADMIN);
        Utilisateur user2=new Utilisateur("Anna","anna@gmail.com","1234", RoleUtilisateur.USER);
        utilisateurInterface.create(user1);
        utilisateurInterface.create(user2);
        Response response = Response.status(Response.Status.OK).entity("User créé").build();
                
        return response;
    }
    
    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Utilisateur user){
        Utilisateur newUser= new Utilisateur(user.getNom(),user.getEmail(),user.getMdp(),RoleUtilisateur.USER);
        utilisateurInterface.create(newUser);
        
        Response response = Response.status(Response.Status.OK).entity("User créé").build();
                
        daoFactory.closeEntityManagerFactory();
        
        return response;
    }
    
    @Path("/register-admin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdmin(Utilisateur user){
        Utilisateur newUser= new Utilisateur(user.getNom(),user.getEmail(),user.getMdp(),RoleUtilisateur.ADMIN);
        utilisateurInterface.create(newUser);
        
        Response response = Response.status(Response.Status.OK).entity("Admin créé").build();
                
        daoFactory.closeEntityManagerFactory();
        
        return response;
    }
    
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Utilisateur user){
        
        user=utilisateurInterface.login(user);
        Response response = Response.ok(user).build();
        daoFactory.closeEntityManagerFactory();
        return response;
    }

    @Path("/update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Utilisateur newUtilisateur, @PathParam(value = "id")int id){
               
        return Response
                    .status(Response.Status.CREATED)
                    .entity(utilisateurInterface.update(newUtilisateur, id))
                    .build();
    }
    
    @Path("/admin/desactiver/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response desactiverUser(@PathParam(value = "id")int id){
      
        return Response
                    .status(Response.Status.CREATED)
                    .entity(utilisateurInterface.desactiver(id))
                    .build();
    }
}
