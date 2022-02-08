package com.yoann_bezard.dao.entiites;

import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    
    private String dateCreation;
    
    private String dateModification;
    
    @Column( unique = true, nullable = false )
    private String libelle;
    
    private String quantite;
    
    /**
     * Constructeur vide.
     */
    public Ingredient() { }
    
    /**
     * Constructeur pour l'ajout d'un nouvel ingrédient.
     * @param dateCreation
     * @param dateModification
     * @param libelle
     * @param quantite 
     */
    public Ingredient( String dateCreation, String dateModification, String libelle, String quantite ) {
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.libelle = libelle;
        this.quantite = quantite;
    }
    
    /**
     * Constructeur pour la modification d'un ingrédient.
     * @param dateModification
     * @param libelle
     * @param quantite 
     */
    public Ingredient( String dateModification, String libelle, String quantite ) {
        this.dateModification = dateModification;
        this.libelle = libelle;
        this.quantite = quantite;
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
    
    @Override
    public String toString() {
        return "Date de création des informations de l'ingrédient : " + this.dateCreation + ".\n" +
                "Date de la dernière modification des informations de l'ingrédient : " + this.dateModification + ".\n" +
                "Libellé : " + this.libelle + ".\n" +
                "Quantité disponible : " + this.quantite + ".\n" +
                "--------------------" + "\n";
    }
    
}