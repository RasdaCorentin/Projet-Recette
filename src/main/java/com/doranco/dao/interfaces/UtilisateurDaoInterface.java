/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.interfaces;

import com.doranco.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author samha
 */
public interface UtilisateurDaoInterface {
    
    
    /**
     * Ensemble des méthodes pour gérer la connexion, l'auth et les autorisations
     * @param user
     * @return 
     */
    Utilisateur create(Utilisateur user);
    
    Utilisateur login (Utilisateur user);
    
    Utilisateur findUserByName (Utilisateur user);
    
    Utilisateur findUserById (int id);
    
    boolean comparePassword(String passwordTemp, Utilisateur user);
    
    
    /**
     * Liste des méthodes CRUD
     * @return 
     */
    List<Utilisateur> getListeUtilisateur();
    
    //Create (constructeur sans id)
    Utilisateur createUtilisateur(Utilisateur user);
    
    //Read Utilisateur à partir de son identifiant
    Utilisateur readUtilisateur (int id);
    
    //Update (constructeur avec id)
    Utilisateur updateUtilisateur(Utilisateur user, int id);
    
    //Delete un Utilisateur
    boolean deleteUtilisateur (int id);
    
    
}
