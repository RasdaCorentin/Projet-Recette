package com.yoann_bezard.dao.interfaces;

import com.yoann_bezard.dao.entiites.Ingredient;
import java.util.List;

public interface IngredientInterface {
    //; Afficher la liste des ingrésients.
    List<Ingredient> getListeIngredient();
    //; Afficher un ingrédient en particulier.
    Ingredient findIngredient( Ingredient ingredient );
    
    //; Créer un nouvel ingrédient.
    Ingredient createIngredient( Ingredient ingredient );
    
    //; Mettre à jour un ingrédient.
    Ingredient updateIngredient( Ingredient ingredient );
    
    //; Supprimer un ingrédient.
    boolean deleteIngredient( Ingredient ingredient );
}
