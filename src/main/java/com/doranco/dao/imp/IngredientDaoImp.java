/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.IngredientInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Recette;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author elair
 */
public class IngredientDaoImp implements IngredientInterface{

    private final DaoFactory daoFactory;

    public IngredientDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Ingredient create(Ingredient ingredient) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        //conversion date
        Date date=Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate=dateFormat.format(date);
        ingredient.setDateCreation(strDate);
        
        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();
            
            transaction.begin();
            entityManager.persist(ingredient);
            transaction.commit();
            System.out.println("<----------- Création Ingredient avec succès ------->");
            System.out.println(ingredient);
            return ingredient;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur création Ingredient \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public boolean update(Ingredient ingredient, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient findIngredientByLibelle(String libelle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
