/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.iinterface;

import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author 33767
 */
public interface RecetteDaoInterface {

    //, Lister toutes les Recette.
    List<Recette> getListeRecettes();
    
    //, Lister toutes les Recette du user
    List<Recette> getListeRecettesByIdUser(int id);

    //, Créer un Recette.
    Recette createRecette(Recette recette, Utilisateur utilisateur);

    //, Lire une recette.
    Recette findRecetteById(int id);
    
    // Update recette
    Recette updateRecette(Recette recette, Utilisateur utilisateur);

    //Delete recette 
    Recette deleteRecetteSSIng(Recette recette);
}

