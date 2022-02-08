/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author elair
 */
@Entity
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String dateCreation;
    private String dateModification;
    private String email;
    private String nom;
    private String mdp;
    private String salt;
    private boolean actif;
    
    @Enumerated(EnumType.STRING)
    private RoleUtilisateur role;
    
    @OneToMany(mappedBy = "utilisateur")
    private List<Recette> listeRecettes=new ArrayList<>();

    public Utilisateur(){
        
    }
    
    public Utilisateur(String nom, String mdp, RoleUtilisateur role) {
        this.nom=nom;
        this.mdp=mdp;
        this.role=role;
    }

    public Utilisateur(String nom, String mdp) {
        this.nom=nom;
        this.mdp=mdp;
    }
    
    public Utilisateur(String nom, String email, String mdp) {
        this.nom=nom;
        this.email=email;
        this.mdp=mdp;
    }

    public Utilisateur(String nom, String email, String mdp, RoleUtilisateur role) {
        this.nom=nom;
        this.email=email;
        this.mdp=mdp;
        this.role=role;
    }
    
    public Utilisateur(String nom, String email, String mdp, RoleUtilisateur role, boolean actif) {
        this.nom=nom;
        this.email=email;
        this.mdp=mdp;
        this.role=role;
        this.actif=actif;
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
     * @return the dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * @return the dateModification
     */
    public String getDateModification() {
        return dateModification;
    }

    /**
     * @param dateModification the dateModification to set
     */
    public void setDateModification(String dateModification) {
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
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
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
    
    public boolean isAdmin(){
        return this.role.equals(RoleUtilisateur.ADMIN);
    }
    
    @Override
    public String toString() {
        return "Id: " + this.id + "\nNom: " + this.nom+"\nRole: " +this.role;
    }
    
}
