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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


/**
 *
 * @author 33767
 */

@Entity
public class Ingredient implements Serializable{
    private static final long serialVersionUID = 1L;

    /*
    Les attributs 
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idIngredient")
    private int id;
    
    private String libelle;
    private String quantite;

    private Date dateCrea;
    private Date dateModif;

    /*
    Les relations
    */

    @Transient
    @ManyToMany( mappedBy = "listIngredients", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY) 
    private List<Recette> listRecettes = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Utilisateur utilisateur;
    /*
    Les Constructeurs
    */
    
    /**
     * Constructeur vide sans paramètres.
     */
    public Ingredient() {
        
    }

    /**
     * Constructeur avec id.
     * @param id
     */
    public Ingredient(int id) {
        this.id = id;
    }

    /**
     * Constructeur sans id.
     * @param libelle
     * @param quantite
     */
    public Ingredient(String libelle, String quantite) {
        this.libelle = libelle;
        this.quantite = quantite;
    }

    /**
     * Constructeur libelle.
     * @param libelle
     */
    public Ingredient(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Constructeur complet.
     * @param id
     * @param libelle
     * @param quantite
     */
    public Ingredient(int id, String libelle, String quantite) {
        this.id = id;
        this.libelle = libelle;
        this.quantite = quantite;
    }

    /*
    Méthode toString.
    */

    @Override
    public String toString() {
            return "\n Id: "
                    + this.getId()
                    + "\n Libelle: "
                    + this.getLibelle()
                    + "\n Quantite: "
                    + this.getQuantite()
                    + "\nDATE CREA : " + this.getDateCrea()
                    + "\nDATE MODIF : " + this.getDateModif()
                    + "\nLISTE RECETTES : " + this.getListRecettes();
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

    /*
    
    Getters & Setters de Relation
    
    */

    /**
     * @return the ListRecettes
     */
    public List<Recette> getListRecettes() {
        return listRecettes;
    }

    /**
     * @param listRecettes the listRecettes to set
     */
    public void setListRecettes(List<Recette> listRecettes) {
        this.listRecettes = listRecettes;
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

}