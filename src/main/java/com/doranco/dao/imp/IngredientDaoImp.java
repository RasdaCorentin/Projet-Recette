/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.IngredientDaoInterface;
import com.doranco.entities.Ingredient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


/*
 * @author 
 */
public class IngredientDaoImp implements IngredientDaoInterface {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    LocalDateTime now = LocalDateTime.now();
    
    private DaoFactory daoFactory;
    
    public IngredientDaoImp(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }
    
    
/*
--------------------------------------------------------------------------------------------------------------------------
                                                 Liste ingredient avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
*/    
//Utilise Jquery pour avoir une liste d'ingredient depuis la base de données
    @Override
    public List<Ingredient> getListeIngredients() {
        
        EntityManager entityManager = null;
        List<Ingredient> listeIngredients = new ArrayList<>();
       
        try {
// ------------------------------------------Methode-------------------------------------------------- 

            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT e FROM Ingredient e", Ingredient.class);
            listeIngredients = query.getResultList();
            
// ---------------------------------------FIN Methode--------------------------------------------------            
        } catch (Exception ex) {

            System.out.println("Erreur lister Ingredients \n" + ex);
//            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listeIngredients;
    }
    
    
/*
--------------------------------------------------------------------------------------------------------------------------
                                                Création Ingredient avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
*/
    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        
//        EntityManager entityManager = null;
//        EntityTransaction transaction = null;
        
        try {
//            entityManager = daoFactory.getEntityManager();
//            transaction = entityManager.getTransaction();
            
// ------------------------------------------Methode-------------------------------------------------- 
            
            ingredient.setDateCrea(dtf.format(now));
            ingredient.setDateModif(dtf.format(now));
            
//  Il faut ajouter le nom de la recette lié à l'ingredient                    
//            transaction.begin();
//            entityManager.persist(ingredient);
//            transaction.commit();

            System.out.println("<----------- Creation Ingredient " + ingredient.toString() + " avec success ------->");
            return ingredient;
 
// ---------------------------------------FIN Methode-------------------------------------------------- 

        } catch (Exception ex) {
//            transaction.rollback();
            System.out.println("Erreur creation Ingredient \n");
            ex.printStackTrace();
//
//        } finally {
//            if (entityManager != null) {
//                entityManager.close();
            }
//        }
        return null;
    }
/*
--------------------------------------------------------------------------------------------------------------------------
                                                Update Ingredient avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
*/
   @Override
    public Ingredient updateIngredient(Ingredient ingredient, int id) {
        
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Ingredient ingredientAModifier = entityManager.find(Ingredient.class, id);
            if (ingredientAModifier != null) {
                transaction = entityManager.getTransaction();



                ingredientAModifier.setLibelle(ingredient.getLibelle());
                ingredientAModifier.setQuantite(ingredient.getQuantite());

                ingredientAModifier.setRecette(ingredient.getRecette());

                ingredientAModifier.setDateModif(dtf.format(now));

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
/*
--------------------------------------------------------------------------------------------------------------------------
                                                Delete Ingredient avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
*/
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
