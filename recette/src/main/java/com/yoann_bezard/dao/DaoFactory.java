package com.yoann_bezard.dao;

import com.yoann_bezard.dao.imp.IngredientDaoImp;
import com.yoann_bezard.dao.imp.RecetteDaoImp;
import com.yoann_bezard.dao.imp.UtilisateurDaoImp;
import com.yoann_bezard.dao.interfaces.IngredientInterface;
import com.yoann_bezard.dao.interfaces.RecetteInterface;
import com.yoann_bezard.dao.interfaces.UtilisateurInterface;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {

    //§ Permet de créer un contexte de persistance.
    private final EntityManagerFactory entityManagerFactory;
    
    public DaoFactory() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("tp-pu");
    }
    
    public EntityManager getEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }
    
    public void closeEntityManagerFactory () {
        this.entityManagerFactory.close();
    }
    
    public UtilisateurInterface getUtilisateurInterface() {
        return new UtilisateurDaoImp( this );
    }
    
    public RecetteInterface getRecetteInterface() {
        return new RecetteDaoImp( this );
    }
    
    public IngredientInterface getIngredientInterface() {
        return new IngredientDaoImp( this );
    }
    
}