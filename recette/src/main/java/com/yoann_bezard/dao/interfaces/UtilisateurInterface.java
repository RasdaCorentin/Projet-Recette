package com.yoann_bezard.dao.interfaces;

import java.util.List;

import com.yoann_bezard.dao.entiites.Utilisateur;

public interface UtilisateurInterface {
    //, Va permettre d'afficher la liste des utilisateurs.
    List<Utilisateur> findAllUtilisateurs();

    //, Va permettre de créer un nouvel utilisateur.
    Utilisateur createUtilisateur( Utilisateur user );

    //, Va permettre à un utilisateur de se connecter.
    Utilisateur loginUtilisateur( Utilisateur user );

    //, Va servir à trouver l'utilisateur, puis à vérifier qu'il a le bon mot de passe.
    Utilisateur findUserByEmail( Utilisateur user );
    boolean comparerPassword( String passwordTemp, Utilisateur user );

    //, Va permettre à un administrateur de modifier un profil.
    Utilisateur updateUtilisateur( Utilisateur user, String email );

    //, Va permettre à un administrateur de supprimer un utilisateur.
    Utilisateur deleteUtilisateur( int id );

    //, Va permettre à un administrateur de désactiver un compte.
    Utilisateur deactivateUtilisateur( String email );

    //, Va permettre à un administrateur de réactiver un compte.
    Utilisateur reactivateUtilisateur( String email );
}