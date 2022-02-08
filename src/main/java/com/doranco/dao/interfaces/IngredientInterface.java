/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doranco.dao.interfaces;

import com.doranco.entities.Ingredient;

/**
 *
 * @author elair
 */
public interface IngredientInterface {
    Ingredient create(Ingredient ingredient);
    boolean update(Ingredient ingredient, int id);
    Ingredient findIngredientByLibelle(String libelle);
}
