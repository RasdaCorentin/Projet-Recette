/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doranco.dao.interfaces;

import com.doranco.entities.Ingredient;
import java.util.List;

/**
 *
 * @author samha
 */
public interface IngredientDaoInterface {
    
    List<Ingredient> getListeIngredient();
    
    //Create (constructeur sans id)
    Ingredient createIngredient(Ingredient ingredient);
    
    //Read Utilisateur à partir de son identifiant
    Ingredient readIngredient (int id);
    
    //Update (constructeur avec id)
    Ingredient updateIngredient(Ingredient ingredient, int id);
    
    //Delete un Utilisateur
    boolean deleteIngredient (int id);
    
}
