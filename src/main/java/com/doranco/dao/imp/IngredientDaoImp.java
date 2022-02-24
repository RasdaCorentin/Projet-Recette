/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.IngredientDaoInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Utilisateur;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    public Ingredient createIngredient(Ingredient ingredient, Utilisateur utilisateur) {
        
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        
        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

            
// ------------------------------------------Methode-------------------------------------------------- 
            ingredient.setUtilisateur(utilisateur);
            ingredient.setDateCrea(new Date());
            ingredient.setDateModif(new Date());
            
//  Il faut ajouter le nom de la recette lié à l'ingrédient                    
            transaction.begin();
            entityManager.persist(ingredient);
            transaction.commit();

            System.out.println("<----------- Création Ingrédient avec succès ------->");

            return ingredient;
 
// ---------------------------------------FIN Methode-------------------------------------------------- 

        } catch (Exception ex) {

            transaction.rollback();
            System.out.println("Erreur création Ingrédient \n");
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
                                                Outils
--------------------------------------------------------------------------------------------------------------------------
*/
    @Override
    public Ingredient findIngredientById(int id) {
        EntityManager entityManager = null;
        Ingredient ingredient = new Ingredient();
        entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery("select ing from Ingredient ing where id=:id");
        query.setParameter("id", id);
        if (query.getResultList().isEmpty()) {
            System.out.println("Cet id d'ingrédient n'existe pas");
            return null;
        }
        ingredient = (Ingredient) query.getResultList().get(0);
        return ingredient;
    }

/*
--------------------------------------------------------------------------------------------------------------------------
                                                Update Ingredient avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
*/
   @Override
    public Ingredient updateIngredient(Ingredient ingredient, Utilisateur utilisateur, int id) {
        
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Ingredient ingredientAModifier = entityManager.find(Ingredient.class, id);
            if (ingredientAModifier != null) {
                transaction = entityManager.getTransaction();

                ingredientAModifier.setUtilisateur(utilisateur);
                ingredientAModifier.setLibelle(ingredient.getLibelle());
                ingredientAModifier.setQuantite(ingredient.getQuantite());

                ingredientAModifier.setDateModif(new Date());

                transaction.begin();
                entityManager.merge(ingredientAModifier);
                transaction.commit();

                System.out.println("<----------- Mise à jour de l'ingrédient avec succès ------->");
                return ingredientAModifier;

            }
            System.out.println("<----------- Ingrédient avec id non trouvé en base de données ------->");
            return null;

        } catch (Exception ex) {
            transaction.rollback();

            System.out.println("Erreur lors de la mise à jour de l'ingrédient \n");

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
    public Ingredient deleteIngredient(int id) {
        
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Ingredient ingredientAModifier = entityManager.find(Ingredient.class, id);
            System.out.println(ingredientAModifier);
            if (ingredientAModifier != null) {
                transaction = entityManager.getTransaction();
                 ingredientAModifier.setUtilisateur(null);
                 ingredientAModifier.setListRecettes(null);
                 
                transaction.begin();
                entityManager.remove(ingredientAModifier);
                transaction.commit();

                System.out.println("<-----------Suppression avec succès ------->");
                return null;

            }
            System.out.println("<----------- Ingrédient non trouvé avec l'id fournie ------->");
            return null;

        } catch (Exception ex) {
            transaction.rollback();

            System.out.println("Erreur lors de la mise à jour de l'ingrédient \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
        
    }

       @Override
    public Ingredient findIngredientByLibelle(Ingredient ingredient) {
       EntityManager entityManager = null;
        entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery("select ing from Ingredient ing where libelle=:libelle");
        query.setParameter("libelle", ingredient.getLibelle());
        if (query.getResultList().isEmpty()) {
            System.out.println("---------- Ce nom d'ingrédient n'existe pas. ----------");
            return null;
        }
        ingredient = (Ingredient) query.getResultList().get(0);
        return ingredient;
    }

    @Override
    public List<Ingredient> getListeIngredientByIdUser(int id) {

        EntityManager entityManager = null;
        List<Ingredient> listeIngredients = new ArrayList<>();

        try {
// ------------------------------------------Methode-------------------------------------------------- 

            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT e FROM Ingredient e where utilisateur_idUtilisateur=:id", Ingredient.class);
            query.setParameter("id", id);
            if (query.getResultList().isEmpty()) {
            System.out.println("Cet id utilisateur n'existe pas");
            return null;
        }
            listeIngredients = query.getResultList();

// ---------------------------------------FIN Methode--------------------------------------------------            
        } catch (Exception ex) {

            System.out.println("Erreur lister Recettes \n" + ex);
//            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listeIngredients;
    }
 }



