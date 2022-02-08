package com.yoann_bezard.dao.entiites;

import com.yoann_bezard.dao.enums.RoleUtilisateur;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private Date dateCreation;
    private Date dateModification;

    @Column( unique = true, nullable = false )
    private String email;

    private String nom;
    private String mdp;

    @Enumerated( EnumType.STRING )
    private RoleUtilisateur roleUtilisateur;

    private boolean actif;
    private String salt;
    
    /**
     * Constructeur vide.
     */
    public Utilisateur() { }
    
    /**
     * Constructeur pour la création d'un utilisateur.
     * @param dateCreation
     * @param dateModification
     * @param email
     * @param nom
     * @param mdp
     * @param role
     * @param actif 
     */
    public Utilisateur( Date dateCreation, Date dateModification, String email, String nom, String mdp, RoleUtilisateur role, boolean actif ) {
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.email = email;
        this.nom = nom;
        this.mdp = mdp;
        this.roleUtilisateur = role;
        this.actif = actif;
    }
    
    /**
     * Constructeur pour la modification d'un d'un utilisateur.
     * @param dateModification
     * @param email
     * @param nom
     * @param mdp
     * @param role
     * @param actif 
     */
    public Utilisateur( Date dateModification, String email, String nom, String mdp, RoleUtilisateur role, boolean actif ) {
        this.dateModification = dateModification;
        this.email = email;
        this.nom = nom;
        this.mdp = mdp;
        this.roleUtilisateur = role;
        this.actif = actif;
    }
    
    /**
     * Constructeur pour la connexion d'un utilisateur existant.
     * @param email
     * @param mdp 
     */
    public Utilisateur( String email, String mdp ) {
        this.email = email;
        this.mdp = mdp;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * @return the roleUtilisateur
     */
    public RoleUtilisateur getRoleUtilisateur() {
        return roleUtilisateur;
    }

    /**
     * @param roleUtilisateur the roleUtilisateur to set
     */
    public void setRoleUtilisateur(RoleUtilisateur roleUtilisateur) {
        this.roleUtilisateur = roleUtilisateur;
    }
    
    /**
     * @return the actif
     */
    public boolean getActif() {
        return actif;
    }

    /**
     * @param actif the actif to set
     */
    public void setActif(boolean actif) {
        this.actif = actif;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Permet de savoir si un utilisateur est ou non un administrateur.
     * @return
     */
    public boolean isAdmin() {
        return this.roleUtilisateur.equals( RoleUtilisateur.ADMIN );
    }

    /**
     * Permet de vérifier que le compte d'un utilisateur est bien actif.
     * @return 
     */
    public boolean isActif() {
        return ( this.actif == true ) ;
    }

    @Override
    public String toString() {
        return "Date de création des informations de l'utilisateur : " + this.dateCreation + ".\n" +
                "Date de la dernière modification des informations de l'utilisateur : " + this.dateModification + ".\n" +
                "Adresse e-mail de l'utilisateur : " + this.email + ".\n" +
                "Nom de l'utilisateur : " + this.nom + ".\n" +
                "Mot de passe : " + this.mdp + ".\n" +
                "Salt : " + this.salt + ".\n" +
                "Rôle de l'utilisateur : " + this.roleUtilisateur + ".\n" +
                "État du compte de l'utilisateur : " + this.actif + ".\n" +
                "--------------------" + "\n";
    }
}