/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Admin
 */
@Entity
public class Recette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    private String description;
    private String reference_image;
    private String dateCreation;
    private String dateModification;

    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
    
    @OneToMany(mappedBy = "recette")
    private List<Ingredient> listIngredients = new ArrayList<>();

    public Recette() {
    }

    public Recette(String libelle, String description) {
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
     * @return the reference_image
     */
    public String getReference_image() {
        return reference_image;
    }

    /**
     * @param reference_image the reference_image to set
     */
    public void setReference_image(String reference_image) {
        this.reference_image = reference_image;
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
     * @return the listIngredients
     */
    public List<Ingredient> getListIngredients() {
        return listIngredients;
    }

    /**
     * @param listIngredients the listIngredients to set
     */
    public void setListIngredients(List<Ingredient> listIngredients) {
        this.listIngredients = listIngredients;
    }

    @Override
    public String toString() {

        String result = "Id: " + this.id + "\n Libelle: "
                + this.libelle + "\n Description William: " + this.description;
        if (this.utilisateur != null) {
            result += "\n Utilisateur" + this.utilisateur;
        }
        if (!this.listIngredients.isEmpty()) {
            result += this.listIngredients;
        }
        return result;

    }

}
