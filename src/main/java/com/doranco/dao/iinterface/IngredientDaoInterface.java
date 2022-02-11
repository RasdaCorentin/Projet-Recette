/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.iinterface;

import com.doranco.entities.Ingredient;
import java.util.List;

/**
 *
 * @author 33767
 */
public interface IngredientDaoInterface {
    
    //Lister tout les Ingredient
    List<Ingredient> getListeIngredients();
    
    //Creer un Ingredient
    Ingredient createIngredient(Ingredient ingredient);
    
    //Update (constructeur avec id)
    Ingredient updateIngredient(Ingredient ingredient, int id);
    

    //Lire un utilisateur à partir de son ID
    Ingredient findIngredientById(int id);
    
    //Supprime un Ingredient (COMMAND ADMIN)
    boolean deleteIngredient(int id);
    
}
