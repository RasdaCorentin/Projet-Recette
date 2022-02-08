/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author elair
 */
@Entity
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String dateCreation;
    private String dateModification;
    private String libelle;
    private String description;
    private String referenceImage;

    @ManyToOne()
    private Utilisateur utilisateur;
    
    @OneToMany(mappedBy = "recette")
    private List<Ingredient> listeIngredients=new ArrayList<>();
    
    public Recette(){
        
    }
    
    public Recette(String libelle, String description){
        this.libelle=libelle;
        this.description=description;
    }
    
    public Recette(String libelle, String description, String referenceImage){
        this.libelle=libelle;
        this.description=description;
        this.referenceImage=referenceImage;
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

    /**
     * @return the referenceImage
     */
    public String getReferenceImage() {
        return referenceImage;
    }

    /**
     * @param referenceImage the referenceImage to set
     */
    public void setReferenceImage(String referenceImage) {
        this.referenceImage = referenceImage;
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
    public String toString(){
        String result="Id: "+this.id+"\nLibellé: "+this.libelle+"\nDescription: "+this.description;
        if(this.utilisateur!=null){
            result+="\nUtilisateur: "+this.utilisateur;
        }
        return result;
    }
}
