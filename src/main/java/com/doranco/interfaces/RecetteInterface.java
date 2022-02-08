/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doranco.interfaces;

import com.doranco.entities.Recette;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface RecetteInterface {
    /**
     * Cette fontion retiurne la liste de toute les recettes dans le systeme
     * @return 
     */
    List<Recette> findAllRecettes();
    Recette createRecette(Recette recette, String email);
    Recette updateRecette(Recette recette);
    Recette findRecetteById(int id, boolean closeEntityManager);
    String deleteRecette(int id); 
}
