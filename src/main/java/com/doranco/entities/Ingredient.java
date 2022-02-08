/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author 33767
 */
@Entity
public class Ingredient implements Serializable{
    private static long serialVersionUID = 1L;
    
    /*
    
    Les attributs 
    
    */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    private String quantite;
    private String DateCrea;
    private String DateModif;
    
    /*
    
    Les relations
    
    */
    @JsonbTransient
    @ManyToOne
    private Recette recette;
    
    /*
        
    Les Constructeurs
        
    */
    

    // Constructeur vide sans parametres
    public Ingredient() {
        
    }
    //Constructeur avec id
    public Ingredient(int id) {
        this.id = id;
    }
    // Constructeur sans id
    public Ingredient(String libelle, String quantite) {
        this.libelle = libelle;
        this.quantite = quantite;
    }
    //Constructeur libelle
    public Ingredient(String libelle) {
        this.libelle = libelle;
    }
    //Constructeur complet
    public Ingredient(int id, String libelle, String quantite) {
        this.id = id;
        this.libelle = libelle;
        this.quantite = quantite;
    }
    
    /*
    
    Methode toString
    
    */

    @Override
    public String toString() {
            return "\n Id: "
                    + this.getId()
                    + "\n Libelle: "
                    + this.getLibelle()
                    + "\n Quantite: "
                    + this.getQuantite()
                    + "\n Info Recette: "
                    + this.getRecette().toString3();
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
     * @return the quantite
     */
    public String getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    /**
     * @return the DateCrea
     */
    public String getDateCrea() {
        return DateCrea;
    }

    /**
     * @param DateCrea the DateCrea to set
     */
    public void setDateCrea(String DateCrea) {
        this.DateCrea = DateCrea;
    }

    /**
     * @return the DateModif
     */
    public String getDateModif() {
        return DateModif;
    }

    /**
     * @param DateModif the DateModif to set
     */
    public void setDateModif(String DateModif) {
        this.DateModif = DateModif;
    }
    
    /*
    
    Getters & Setters de Relation
    
    */
    
    /**
     * @return the recette
     */
    public Recette getRecette() {
        return recette;
    }

    /**
     * @param recette the recette to set
     */
    public void setRecette(Recette recette) {
        this.recette = recette;
    }


}
