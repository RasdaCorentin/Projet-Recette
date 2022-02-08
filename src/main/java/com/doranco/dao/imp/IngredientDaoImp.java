/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.IngredientDaoInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Recette;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author samha
 */
public class IngredientDaoImp implements IngredientDaoInterface {

    private DaoFactory daoFactory;
    
    public IngredientDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public List<Ingredient> getListeIngredient() {
        
        EntityManager entityManager = null;
        List<Ingredient> listeIngredients = new ArrayList<>();
        try {
            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT ing FROM Ingredient ing", Ingredient.class);
            listeIngredients = query.getResultList();
        } catch (Exception ex) {

            System.out.println("Erreur pour récupérer la liste des ingrédients \n" + ex);
//            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listeIngredients;
        
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
            String formatDate = dateFormat.format(date);

            ingredient.setDateCreation(date);

            transaction.begin();
            entityManager.persist(ingredient);
            transaction.commit();
            System.out.println("<----------- Creation de l'ingredient avec succes ------->");
            return ingredient;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la creation de l'ingredient \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
        
    }

    @Override
    public Ingredient readIngredient(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient, int id) {
        
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Ingredient ingredientAModifier = entityManager.find(Ingredient.class, id);
            if (ingredientAModifier != null) {
                transaction = entityManager.getTransaction();

                Date now = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                System.out.println(simpleDateFormat.format(now));

                ingredientAModifier.setLibelle(ingredient.getLibelle());
                ingredientAModifier.setQuantite(ingredient.getQuantite());

                ingredientAModifier.setRecette(ingredient.getRecette());

                ingredientAModifier.setDateModification(now);

                transaction.begin();
                entityManager.persist(ingredientAModifier);
                transaction.commit();
                System.out.println("<----------- Mise a jour de l'ingredient avec succes ------->");
                return ingredient;

            }
            System.out.println("<----------- Ingredient avec id non trouvé en base de données ------->");
            return null;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la mise a jour de l'ingredient \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
        
    }

    @Override
    public boolean deleteIngredient(int id) {
        
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Ingredient ingredientAModifier = entityManager.find(Ingredient.class, id);
            if (ingredientAModifier != null) {
                transaction = entityManager.getTransaction();

                transaction.begin();
                entityManager.remove(ingredientAModifier);
                transaction.commit();
                System.out.println("<-----------Suppression avec succes ------->");
                return true;

            }
            System.out.println("<----------- Ingredient non trouvé avec l'id fournie ------->");
            return false;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la mise a jour de l'ingredient \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;
        
    }
    
}
