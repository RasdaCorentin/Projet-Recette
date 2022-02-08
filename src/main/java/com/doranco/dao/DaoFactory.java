/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao;

import com.doranco.dao.imp.IngredientDaoImp;
import com.doranco.dao.imp.RecetteDaoImp;
import com.doranco.dao.imp.UtilisateurDaoImp;
import com.doranco.dao.interfaces.IngredientDaoInterface;
import com.doranco.dao.interfaces.RecetteDaoInterface;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author samha
 */
public class DaoFactory {
    
    private final EntityManagerFactory entityManagerFactory;
    
    public DaoFactory() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("projet-recette-pu");
    }
    
    public EntityManager getEntityManager(){
        return this.entityManagerFactory.createEntityManager();
    }
    
    public void closeEntityManagerFactory(){
        this.entityManagerFactory.close();
    }
    
    public UtilisateurDaoInterface getUtilisateurDaoInterface(){
        return new UtilisateurDaoImp(this);
    }
    
    public RecetteDaoInterface getRecetteDaoInterface() {
        return new RecetteDaoImp(this);
    }
    
    public IngredientDaoInterface getIngredientDaoInterface() {
        return new IngredientDaoImp(this);
    }
    
}
