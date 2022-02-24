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
public interface IngredientDaoInterface {
    
    //Lister tout les Ingredients
    List<Ingredient> getListeIngredients();
    
    //, Lister tout les Ingredients du user
    List<Ingredient> getListeIngredientByIdUser(int id);
    
    //Creer un Ingredient
    Ingredient createIngredient(Ingredient ingredient, Utilisateur utilisateur);
    
    //Update (constructeur avec id)
    Ingredient updateIngredient(Ingredient ingredient, Utilisateur utilisateur, int id);
    

    //Lire un utilisateur à partir de son ID
    Ingredient findIngredientById(int id);
    
    //Supprime un Ingredient (COMMAND ADMIN)
    Ingredient deleteIngredient(int id);
    
    //, Lire un Utilisateur a partir de son Nom.
    Ingredient findIngredientByLibelle(Ingredient ingredient);
}
