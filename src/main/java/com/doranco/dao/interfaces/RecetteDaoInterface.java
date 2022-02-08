/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doranco.dao.interfaces;

import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author samha
 */
public interface RecetteDaoInterface {

    Recette findRecetteById(int id);
    
    List<Recette> getListeRecette();
    
    //Create (constructeur sans id)
    Recette createRecette(Recette recette);
    
    //Read Utilisateur à partir de son identifiant
    Recette readRecette (int id);
    
    //Update (constructeur avec id)
    Recette updateRecette(Recette recette, int id);
    
    //Delete un Utilisateur
    boolean deleteRecette (int id);
    
}
