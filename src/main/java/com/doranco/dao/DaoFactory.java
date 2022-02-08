/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao;

import com.doranco.dao.imp.IngredientDaoImp;
import com.doranco.dao.imp.RecetteDaoImp;
import com.doranco.dao.imp.UtilisateurDaoImp;
import com.doranco.dao.interfaces.IngredientInterface;
import com.doranco.dao.interfaces.RecetteInterface;
import com.doranco.dao.interfaces.UtilisateurInterface;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Admin
 */
public class DaoFactory {
    
    private final EntityManagerFactory entityManagerFactory;

    public DaoFactory() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("tp-pu");
    }

    public EntityManager getEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }

    public void closeEntityManagerFactory() {
        this.entityManagerFactory.close();
    }
    
    public UtilisateurInterface getUtilisateurInterface(){
        return new UtilisateurDaoImp(this);
    }
    
    public RecetteInterface getRecetteInterface(){
        return new RecetteDaoImp(this);
    }
    
    public IngredientInterface getIngredientInterface(){
        return new IngredientDaoImp(this);
    }
}
