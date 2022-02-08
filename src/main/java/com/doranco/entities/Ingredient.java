/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author samha
 */
@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateModification = new Date();

    private String libelle;

    private int quantite;

    /**
     * Relation plusieurs ingredients pour une recette
     */
    @ManyToOne
    private Recette recette;

    /**
     * Constructeur sans param
     */
    public Ingredient() {
    }

    /**
     * Constructeur avec id
     *
     * @param id
     * @param libelle
     * @param quantite
     */
    public Ingredient(int id, String libelle, int quantite) {
        this.id = id;
        this.libelle = libelle;
        this.quantite = quantite;
    }

    /**
     * Constructeur sans id
     *
     * @param libelle
     * @param quantite
     */
    public Ingredient(String libelle, int quantite) {
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
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    
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
    
    
    
    @Override
    public String toString() {
        return "ID :" + this.id
                + "\nLIBELLE : " + this.libelle
                + "\nQUANTITE : " + this.quantite
                + "\n";
    }

    
}
