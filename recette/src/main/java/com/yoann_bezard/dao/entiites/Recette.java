package com.yoann_bezard.dao.entiites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Recette implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    
    private String dateCreation;
    
    private String dateModification;
    
    private String libelle;
    
    private String description;
    
    /**
     * Constructeur vide.
     */
    public Recette() { }
    
    /**
     * Constructeur pour l'ajout d'une nouvelle recette.
     * @param dateCreation
     * @param dateModification
     * @param libelle
     * @param description 
     */
    public Recette( String dateCreation, String dateModification, String libelle, String description ) {
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.libelle = libelle;
        this.description = description;
    }
    
    /**
     * Constructeur pour la modification d'une recette.
     * @param dateModification
     * @param libelle
     * @param description 
     */
    public Recette( String dateModification, String libelle, String description ) {
        this.dateModification = dateModification;
        this.libelle = libelle;
        this.description = description;
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
    
    @Override
    public String toString() {
        return "Date de création des informations de la recette : " + this.dateCreation + ".\n" +
                "Date de la dernière modification des informations de la recette : " + this.dateModification + ".\n" +
                "Libellé : " + this.libelle + ".\n" +
                "La description de la recette : " + this.description + ".\n" +
                "--------------------" + "\n";
    }

}