/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author samha
 */
@Entity
@Table(name="utilisateur")
public class Utilisateur implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    //Indique que le nom doit etre unique, et ne peut pas etre nul
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String salt;
    
    /**
     * Attribut chargé de garder en mémoire le jour et l'heure de création d'un utilisateur
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;
    
    /**
     * On donne à cet attribut la date du jour, car il est appelé au moment de la 
     * mise à jour et conserve la date en cours à ce moment-là
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateModification = new Date();
    
    private String email;
    
    private boolean actif = true;
    
    @Enumerated(EnumType.STRING)
    private RoleUtilisateur role = RoleUtilisateur.USER;

    /**
     * Relation un utilisateur pour plusieurs recettes
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recette> listeRecettes = new ArrayList<>();
    
    
    /**
     * Constructeur sans param
     */
    public Utilisateur() {
    }

    
 /**
  * Pour la création
  * @param username
  * @param password
  * @param email
  * @param role 
  */   
public Utilisateur(String username, String password, String email, RoleUtilisateur role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }    
    
    /**
     * Constructeur pour la modif d'un profil perso par un utilisateur std
     * @param username
     * @param password
     * @param email 
     * @param role 
     * @param actif 
     */
    public Utilisateur(String username, String password, String email, RoleUtilisateur role, boolean actif) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.actif = actif;
    }
    
    /**
     * Csontructeur "avancé" pour gérer les recettes attachées à l'utilisateur
     * @param username
     * @param password
     * @param email
     * @param role
     * @param actif
     * @param listeRecettes 
     */
    public Utilisateur(String username, String password, String email, RoleUtilisateur role, boolean actif, List<Recette> listeRecettes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.actif = actif;
        this.listeRecettes = listeRecettes;
    }
    
 
    /**
     * Constructeur pour la modification, réservé aux admins
     * @param role
     * @param actif 
     */
    public Utilisateur(RoleUtilisateur role, boolean actif) {
        this.role = role;
        this.actif = actif;
    }
    
    
    
    /**
     * Constructeur sans role pour l'authentification 
     * @param username
     * @param password 
     */
    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the nom to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

     /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
            
    /**
     * @return the role
     */
    public RoleUtilisateur getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(RoleUtilisateur role) {
        this.role = role;
    }
    
    /**
     * @return the dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    
    /**
     * @return the dateModification
     */
    public Date getDateModification() {
        return dateModification;
    }

    /**
     * @param dateModification the dateModification to set
     */
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }
    
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the actif
     */
    public boolean isActif() {
        return actif;
    }

    /**
     * @param actif the actif to set
     */
    public void setActif(boolean actif) {
        this.actif = actif;
    }
           
   
    /**
     * Sert à déterminer le role et donc les droits d'un utilisateur
     * @return 
     */
    public boolean isAdmin(){
        return this.role.equals(RoleUtilisateur.ADMIN);
    }

    
    /**
     * @return the listeRecettes
     */
    public List<Recette> getListeRecettes() {
        return listeRecettes;
    }

    /**
     * @param listeRecettes the listeRecettes to set
     */
    public void setListeRecettes(List<Recette> listeRecettes) {
        this.listeRecettes = listeRecettes;
    }
    
    
    
   /**
     * N'affiche pas le mot de passe !
     * @return 
     */
    @Override
    public String toString() {
        
        if (listeRecettes != null) {
        
        return "ID :" + this.id
                + "\nNOM : " + this.username
                + "\nEMAIL : " + this.email
                + "\nROLE : " + this.role
                + "\nLISTE DES RECETTES : " + this.listeRecettes
                + "\n";
    } else {
        return "ID :" + this.id
                + "\nNOM : " + this.username
                + "\nEMAIL : " + this.email
                + "\nROLE : " + this.role
                + "\n";
        }
   
    }
}
