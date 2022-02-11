/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;


import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;



/**
 *
 * @author Admin
 */
@Entity
public class Recette implements Serializable {
   private static final long serialVersionUID = 1L;

    /*
    
    Les attributs 
    
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date DateCrea;
    private Date DateModif;
    private String libelle;
    private String description;
    private String refImage;
    
    /*
    
    Les relations
    
    */
    
    @ManyToOne
    private Utilisateur utilisateur;
  
// A Implementer avec Ingredient + Many To Many
  
    /*
        
    Les Constructeurs
        
    */

    // Constructeur vide sans parametres
    public Recette() {
        
    }
    //Constructeur avec id
    public Recette(int id) {
        this.id = id;
    }
    // Constructeur sans id
    public Recette(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }
    //Constructeur complet
    public Recette(int id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }
    
    /*
    
    Methode toString
    
    */

@Override
    public String toString() {
        return "ID :" + this.getId()
                + "\nLIBELLE : " + this.getLibelle()
                + "\nDESCRIPTION : " + this.getDescription()
                + "\nDATE CREA : " + this.DateCrea
                + "\nDATE MODIF : " + this.DateModif

                + "\n";
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

// Ajouter Getters & Setters Date
  
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
    
    /*
    
    Getters & Setters de Relation
    
    */

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

}
