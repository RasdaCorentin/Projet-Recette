/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.iinterface;

import com.doranco.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author 33767
 */
public interface UtilisateurDaoInterface {

//.---------------------------------------------ADMIN COMMAND-------------------------------------------------
    
    //, Lister tout les Utilisateur.
    List<Utilisateur> getListeUtilisateurs();

    //, Désactiver le compte d'un utilisateur.
    Utilisateur deactivateUtilisateur(int id);

    //, Activer le compte d'un utilisateur.
    Utilisateur activateUtilisateur(int id);

    //, Faire disparaître un utilisateur.
    Utilisateur vanishUtilisateur(Utilisateur utilisateur, int id);

    //, Supprimer un utilisateur.
    boolean deleteUtilisateur(int id);
    
//.----------------------------------ADMIN/NEW USER COMMAND (À interdire aux user-------------------------------------------------
    
    //, Créer un Utilisateur.
    Utilisateur createUtilisateur(Utilisateur utilisateur);

    //, Connecter un Utilisateur.
    Utilisateur connectUtilisateur(Utilisateur utilisateur);    
    
//.---------------------------------------------Outils-------------------------------------------------

    //, Compare les utilisateurs et le renvoie s'il existe.
    Utilisateur loginUtilisateur(Utilisateur utilisateur);

    //, Compare le mot de passe pour le login.
    boolean comparePassword(String passwordTemp, Utilisateur utilisateur);

    //, Lire un Utilisateur a partir de son Nom.
    Utilisateur findUtilisateurByNom(Utilisateur utilisateur);

    //, Lire un utilisateur à partir de son ID.
    Utilisateur findUtilisateurById(int id);

//.---------------------------------------------ADMIN / USER-------------------------------------------------

    //, Modifier un utilisateur.
    Utilisateur updateUtilisateur(Utilisateur utilisateur);

    //, Lire un utilisateur.
    Utilisateur readUtilisateur(int id);
}