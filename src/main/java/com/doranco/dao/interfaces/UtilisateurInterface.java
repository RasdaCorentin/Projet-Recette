/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doranco.dao.interfaces;

import com.doranco.entities.Utilisateur;

/**
 *
 * @author Admin
 */
public interface UtilisateurInterface {
    Utilisateur create(Utilisateur user) ;
    Utilisateur login(Utilisateur user);
    Utilisateur findUserByNom(Utilisateur user);
    Utilisateur findUserById(Utilisateur user, int id);
    Utilisateur findUserByEmail(String email);
    boolean comparerMdp(String mdpTemp, Utilisateur user);
    boolean update(Utilisateur user, int id);
    boolean desactiver(int id);


}
