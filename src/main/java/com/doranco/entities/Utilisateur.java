/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;


/**
 *
 * @author 33767
 */

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Utilisateur implements Serializable{
    private static long serialVersionUID = 1L;

    /*
    Les attributs 
    */

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)

    private Date DateCrea;
    private Date DateModif;

    private String nom;
    private String newNom;
    private String password;
    private String newPassword;
    private String salt;
    private String email;
    private boolean statuts;

    @Enumerated(EnumType.STRING)
    private RoleUtilisateur role;

    /*
    Les relations
    */

    @Transient
    @OneToMany(mappedBy = "utilisateur")
//  @OneToMany(mappedBy = "utilisateur", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Recette> listeRecettes = new ArrayList<>();

    /*
    Booléen de vérification de rôle & de statuts
    */

    /**
     * @param statuts the statuts to set
     */
    public void setStatuts(boolean statuts) {
        this.statuts = statuts;
    }

    public boolean isAdmin(){
        return this.getRole().equals(RoleUtilisateur.ADMIN);
    }

    public boolean isUser(){
        return this.getRole().equals(RoleUtilisateur.USER);
    }

    public boolean isActif() {
        return (this.statuts == true);
    }

    /*
    Les Constructeurs
    */

    /**
     * Constructeur vide sans paramètres.
     */
    public Utilisateur() { 
    }

    /**
     * Constructeur avec l'id.
     * @param id
     */
    public Utilisateur(int id) {
        this.id = id;
    }

    /**
     * Constructeur rôle.
     * @param nom
     * @param password
     * @param role
     * @param email
     */
    public Utilisateur(String nom, String password, RoleUtilisateur role, String email) {
        this.nom = nom;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    /**
     * Constructeur sans id.
     * @param nom
     * @param password
     */
    public Utilisateur(String nom, String password) {
        this.nom = nom;
        this.password = password;
    }    

    /**
     * Constructeur complet.
     * @param id
     * @param nom
     * @param password
     * @param email
     */
    public Utilisateur(int id, String nom, String password, String email) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.email = email;
    }

    /*
    Méthode toString.
    */

    @Override
    public String toString() {
        if (listeRecettes != null) {

            return "ID :" + this.id
                    + "\nNOM : " + this.nom
                    + "\nEMAIL : " + this.email
                    + "\nDATE CREA : " + this.DateCrea
                    + "\nDATE MODIF : " + this.DateModif
                    + "\nROLE : " + this.role
                    + "\nLISTE DES RECETTES : " + this.listeRecettes
                    + "\n";
        } else {
            return "ID :" + this.id
                    + "\nNOM : " + this.nom
                    + "\nEMAIL : " + this.email
                    + "\nDATE CREA : " + this.DateCrea
                    + "\nDATE MODIF : " + this.DateModif
                    + "\nROLE : " + this.role
                    + "\n";
        }

    }

    /*
    Getters & Setters
    */

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
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
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
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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
     * @return the DateCrea
     */
    public Date getDateCrea() {
        return DateCrea;
    }

    /**
     * @param DateCrea the DateCrea to set
     */
    public void setDateCrea(Date DateCrea) {
        this.DateCrea = DateCrea;
    }

    /**
     * @return the DateModif
     */
    public Date getDateModif() {
        return DateModif;
    }

    /**
     * @param DateModif the DateModif to set
     */
    public void setDateModif(Date DateModif) {
        this.DateModif = DateModif;
    }
    /**
     * @return the statuts
     */
    public boolean isStatuts() {
        return statuts;
    }    
    /**
     * @return the newNom
     */
    public String getNewNom() {
        return newNom;
    }

    /**
     * @param newNom the newNom to set
     */
    public void setNewNom(String newNom) {
        this.newNom = newNom;
    }

    /*
    Getters & Setters de Relation
    */

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
}
