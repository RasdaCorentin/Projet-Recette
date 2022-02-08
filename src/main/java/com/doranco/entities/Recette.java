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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 *
 * @author samha
 */
@Entity
@Table(name = "recette")
public class Recette implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateModification = new Date();
    
    private String libelle;
    
    private String description;
    
    private String refImage;

    /**
     * Relation plusieurs recettes pour un utilisateur
     */
    @ManyToOne
    private Utilisateur utilisateur;
    
    /**
     * Relation une recette pour plusieurs ingrédients
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "recette", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingredient> listeIngredients = new ArrayList<>();
    
    /**
     * Constructeur sans param
     */
    public Recette() {
    }

//
//    /**
//     * Constructeur sans id
//     * @param libelle
//     * @param description
//     * @param refImage 
//     */
//    public Recette(String libelle, String description, String refImage) {
//        this.libelle = libelle;
//        this.description = description;
//        this.refImage = refImage;
//
//    }
    
    
    public Recette (String libelle, String description, String refImage, List<Ingredient> listeIngredients) {
        this.libelle = libelle;
        this.description = description;
        this.refImage = refImage;
        this.listeIngredients = listeIngredients;
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
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the refImage
     */
    public String getRefImage() {
        return refImage;
    }

    /**
     * @param refImage the refImage to set
     */
    public void setRefImage(String refImage) {
        this.refImage = refImage;
    }
    
    
    /**
     * @return the utilisateur
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * @param utilisateur the utilisateur to set
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    
    /**
     * @return the listeIngredients
     */
    public List<Ingredient> getListeIngredients() {
        return listeIngredients;
    }

    /**
     * @param listeIngredients the listeIngredients to set
     */
    public void setListeIngredients(List<Ingredient> listeIngredients) {
        this.listeIngredients = listeIngredients;
    }

    
    
    
    @Override
    public String toString() {
        
        if (listeIngredients != null) {
        
        return "ID :" + this.id
                + "\nLIBELLE : " + this.libelle
                + "\nDESCRIPTION : " + this.description
                + "\nLISTE D'INGREDIENTS : " + this.listeIngredients
                + "\n";
    } else {
        return "ID :" + this.id
                + "\nLIBELLE : " + this.libelle
                + "\nDESCRIPTION : " + this.description
                + "\n";
        }

    }
    
    
}
