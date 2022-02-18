/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @Column(name="idRecette")
    private int id;
    
    private Date dateCrea;
    private Date dateModif;
    
    private String libelle;
    private String description;
    //Encore A IMPLEMENTER
    private String refImage;
    
    /*
    
    Les relations
    
    */

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Utilisateur utilisateur;
  
// A Implementer avec Ingredient + Many To Many

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Ingredient_Recette_Association",
            joinColumns = @JoinColumn(name = "idRecette"),
            inverseJoinColumns = @JoinColumn(name = "idIngredient") )
    private List<Ingredient> listIngredients = new ArrayList<>();
  
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
//Changer la methode pour afficher liste Ingredient
@Override
    public String toString() {
        if(listIngredients != null){
                    return "ID :" + this.getId()
                + "\nLIBELLE : " + this.getLibelle()
                + "\nDESCRIPTION : " + this.getDescription()
                + "\nDATE CREA : " + this.getDateCrea()
                + "\nDATE MODIF : " + this.getDateModif()
                + "\nLISTE INGREDIENTS : " + this.getListIngredients()
                +"\nUTILISATEUR : " + this.getUtilisateur()

                + "\n";
        }
        return "ID :" + this.getId()
                + "\nLIBELLE : " + this.getLibelle()
                + "\nDESCRIPTION : " + this.getDescription()
                + "\nDATE CREA : " + this.getDateCrea()
                + "\nDATE MODIF : " + this.getDateModif()
                +"\nUTILISATEUR : " + this.getUtilisateur()
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

        /**
     * @return the DateCrea
     */
    public Date getDateCrea() {
        return dateCrea;
    }

    /**
     * @param dateCrea
     */
    public void setDateCrea(Date dateCrea) {
        this.dateCrea = dateCrea;
    }

    /**
     * @return the DateModif
     */
    public Date getDateModif() {
        return dateModif;
    }

    /**
     * @param dateModif
     */
    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
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

    /**
     * @return the ListIngredients
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



}
