package com.yoann_bezard.dao.interfaces;

import com.yoann_bezard.dao.entiites.Recette;
import java.util.List;

public interface RecetteInterface {
    //; Afficher la liste des recettes.
    List<Recette> getListeRecette();
    //; Afficher une recette en particulier.
    Recette findRecette( Recette recette );
    
    //; Créer une nouvelle recette.
    Recette createRecette( Recette recette );
    
    //; Mettre à jour une recette.
    Recette updateRecette( Recette recette );
    
    //; Supprimer une recette.
    boolean deleteRecette( Recette recette );
}