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

    //, Lister tout les ingrédients.
    List<Ingredient> getListeIngredients();

    //, Créer un ingrédient.
    Ingredient createIngredient(Ingredient ingredient);

    //, Update (constructeur avec id).
    Ingredient updateIngredient(Ingredient ingredient, int id);

    //, Lire un ingrédient à partir de son id.
    Ingredient findIngredientById(int id);

    //, Lire un ingrédient a partir de son libelle.
    Ingredient findIngredientByLibelle(Ingredient ingredient);

    //, Supprime un Ingredient (COMMAND ADMIN).
    boolean deleteIngredient(int id);

}