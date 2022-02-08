/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doranco.interfaces;

import com.doranco.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UtilisateurInterface {
    List<Utilisateur> findAllUtilisateurs();
    Utilisateur createUtilisateur(Utilisateur user);
    Utilisateur updateUtilisateur(Utilisateur user, String email);
    Utilisateur loginUtilisateur(Utilisateur user);
    Utilisateur findUtilisateurByEmail(String email);
    Utilisateur findUtilisateurId(int id, boolean  closeEntityManager);
    boolean desactiverUtilisateur(String email);
    boolean comparerPassword(String passwordTemp, Utilisateur user);
    String deleteUtilisateur(int id);
}
