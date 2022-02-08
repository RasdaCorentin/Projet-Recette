/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doranco.interfaces;

import com.doranco.entities.Ingredient;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IngredientInterface {
        /**
     * Cette fontion retiurne la liste de toute les Ingredients dans le systeme
     * @return 
     */
    List<Ingredient> findAllIngredients();
    Ingredient createIngredient(Ingredient ingredient);
    Ingredient updateIngredient(Ingredient ingredient);
    Ingredient findIngredientById(int id, boolean closeEntityManager);
    String deleteIngredient(int id); 
}
