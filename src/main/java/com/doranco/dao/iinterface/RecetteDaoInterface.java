/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.iinterface;

import com.doranco.entities.Ingredient;
import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author 33767
 */
public interface RecetteDaoInterface {
    
    //Lister tout les Recette
    List<Recette> getListeRecettes();
    
    //Creer un Recette
    Recette createRecette(Recette recette, Utilisateur utilisateur);
        
    //Read recette
    Recette findRecetteById(int id);
 
}
