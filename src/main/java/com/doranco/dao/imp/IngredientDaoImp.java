/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.entities.Ingredient;
import com.doranco.interfaces.IngredientInterface;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Admin
 */
public class IngredientDaoImp implements IngredientInterface {

    private final DaoFactory daoFactory;
    private EntityManager entityManager = null;
    private EntityTransaction transaction = null;

    public IngredientDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        entityManager = daoFactory.getEntityManager();
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredient.setDateCreation(new Date().toString());
        ingredient.setDateModification(new Date().toString());
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(ingredient);
            transaction.commit();
            System.out.println("<----------- Creation ingredient avec success ------->");
            System.out.println(ingredient);
            return ingredient;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur creation ingredient " + ex.getMessage());

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient findIngredientById(int id, boolean closeEntityManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteIngredient(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
