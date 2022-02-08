/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doranco.dao.interfaces;

import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;

/**
 *
 * @author elair
 */
public interface RecetteInterface {
    Recette create(Recette recette);
    Recette update(Recette recette, int id);
    Recette findRecetteByLibelle(String libelle);
    Recette findRecetteById(int id);
    boolean delete(int id);
}
