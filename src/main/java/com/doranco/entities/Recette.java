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
    @Column(name="id_recette")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String DateCrea;
    private String DateModif;
    private String libelle;
    private String description;
    private String refImage;
    
    /*
    
    Les relations
    
    */
    
    @ManyToOne
    private Utilisateur utilisateur;
    
    @Transient
    @OneToMany(mappedBy = "recette")
    private List<Ingredient> listeIngredients = new ArrayList<>();
    
    /*
    
    Option ManyToMany
    @Column(name="id_recette")
    private int id;
     */
//    @ManyToMany()
//    @JoinTable(name="Recette_Ingredient",
//    joinColumns = {@JoinColumn(name= "id_recette")},
//    inverseJoinColumns = {@JoinColumn(name="id_ingredient")})
//    private List<Ingredient> listeIngredients = new ArrayList<>();
    
   
    
    
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
        
        if (listeIngredients != null) {
        
        return "ID :" + this.getId()
                + "\nLIBELLE : " + this.getLibelle()
                + "\nDESCRIPTION : " + this.getDescription()
                + "\nDATE CREA : " + this.DateCrea
                + "\nDATE MODIF : " + this.DateModif
                + "\nLISTE D'INGREDIENTS : " + this.getListeIngredients()
                + "\n";
    } else {
        return "ID :" + this.getId()
                + "\nLIBELLE : " + this.getLibelle()
                + "\nDESCRIPTION : " + this.getDescription()
                + "\nDATE CREA : " + this.DateCrea
                + "\nDATE MODIF : " + this.DateModif
                + "\n";
        }
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

}
