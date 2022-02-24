/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.IngredientDaoInterface;
import com.doranco.dao.iinterface.RecetteDaoInterface;
import com.doranco.dao.iinterface.UtilisateurDaoInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Recette;
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
public class RecetteDaoImp implements RecetteDaoInterface {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    Ingredient ingredient = new Ingredient();
    private DaoFactory daoFactory;

    public RecetteDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Liste Recette avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
     */
//Utilise Jquery pour avoir une liste d'recette depuis la base de données
    @Override
    public List<Recette> getListeRecettes() {

        EntityManager entityManager = null;
        List<Recette> listeRecettes = new ArrayList<>();

        try {
// ------------------------------------------Methode-------------------------------------------------- 

            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT e FROM Recette e", Recette.class);
            listeRecettes = query.getResultList();

// ---------------------------------------FIN Methode--------------------------------------------------            
        } catch (Exception ex) {

            System.out.println("Erreur lister Recettes \n" + ex);
//            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listeRecettes;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                Création Recette avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public Recette createRecette(Recette recette, Utilisateur utilisateur) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();
            System.out.println(recette);
// ------------------------------------------Methode--------------------------------------------------              
// Marche pour ajouter une recette / utilisateur / ingredient
            UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
            utilisateur = utilisateurDaoInterface.findUtilisateurByNom(utilisateur);
            
            if (utilisateur != null) {
                //Start transaction
                transaction.begin();


                List<Ingredient> listeIngredient = new ArrayList<>(recette.getListIngredients());
                IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
                for (int index = 0; index < listeIngredient.size(); index++) {
                    Ingredient ingredient = listeIngredient.get(index);
                        ingredient = ingredientDaoInterface.createIngredient(ingredient, utilisateur);
                        
                }


//          Ajouter la recette
                
                recette.setUtilisateur(utilisateur);
                recette.setDateCrea(new Date());
                recette.setDateModif(new Date());

                entityManager.merge(recette);

                transaction.commit();
                System.out.println("<----------- Creation Recette avec success ------->");
                return recette;
            }
// ---------------------------------------FIN Methode-------------------------------------------------- 

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur creation Recette \n");
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
                                                Read Recette (ID) 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public Recette findRecetteById(int id) {
        EntityManager entityManager = null;
        Recette recette = new Recette();
        entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery("select util from Recette util where id=:id");
        query.setParameter("id", id);
        if (query.getResultList().isEmpty()) {
            System.out.println("Cet id utilisateur n'existe pas");
            return null;
        }
        recette = (Recette) query.getResultList().get(0);
        return recette;
    }
    /*
--------------------------------------------------------------------------------------------------------------------------
                                                Update Recette
--------------------------------------------------------------------------------------------------------------------------
    */
    @Override
    public Recette updateRecette(Recette recette, Utilisateur utilisateur) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Recette recetteAModifier = entityManager.find(Recette.class, recette.getId());
            UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
            utilisateur = utilisateurDaoInterface.findUtilisateurByNom(utilisateur);
            System.out.println("Recuperation de la recette !!!!!!!!!!!! " + recetteAModifier);

                        
            if (recetteAModifier != null) {
                transaction = entityManager.getTransaction();

                Date now = new Date();
        
                recetteAModifier.setLibelle(recette.getLibelle());
                recetteAModifier.setDescription(recette.getDescription());
                recetteAModifier.setRefImage(recette.getRefImage());

                recetteAModifier.setUtilisateur(utilisateur);

                recetteAModifier.setDateModif(now);

                transaction.begin();
                List<Ingredient> listeIngredientAModifier = new ArrayList<>(recetteAModifier.getListIngredients());
                List<Ingredient> listeIngredient = new ArrayList<>(recette.getListIngredients());
                IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
                for (int index2 = 0; index2 < listeIngredient.size(); index2++) {
                        Ingredient ingredient = listeIngredient.get(index2);
                    
                                
                    Ingredient ingredientAModifier = listeIngredientAModifier.get(index2);
                   Ingredient ingredientBDD = ingredientDaoInterface.updateIngredient(ingredient, utilisateur, ingredientAModifier.getId());                                        
                                    System.out.println(ingredientBDD);
                }
                
                entityManager.merge(recetteAModifier);
                transaction.commit();
                System.out.println("<----------- Mise a jour Recette avec succes ------->");
                return recetteAModifier;

            }
            System.out.println("<----------- Recette avec id non trouvee ------->");
            return null;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur mise a jour Recette \n");
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
                                                 Liste Recette by User avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
     */

    @Override
    public List<Recette> getListeRecettesByIdUser(int id) {

        EntityManager entityManager = null;
        List<Recette> listeRecettes = new ArrayList<>();

        try {
// ------------------------------------------Methode-------------------------------------------------- 

            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT e FROM Recette e where utilisateur_idUtilisateur=:id", Recette.class);
            query.setParameter("id", id);
            if (query.getResultList().isEmpty()) {
            System.out.println("Cet id utilisateur n'existe pas");
            return null;
        }
            listeRecettes = query.getResultList();

// ---------------------------------------FIN Methode--------------------------------------------------            
        } catch (Exception ex) {

            System.out.println("Erreur lister Recettes \n" + ex);
//            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listeRecettes;
    }
      /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Delete Recette sans les ingredient. 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public Recette deleteRecetteSSIng(Recette recette) {
       
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

//. -------------------------------------------------------------------------------------------
             
            recette = entityManager.find(Recette.class, recette.getId());

            
            if (recette != null) {
                                List<Ingredient> listeIngredient = new ArrayList<>(recette.getListIngredients());
                IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
                recette.setUtilisateur(null);
                recette.setListIngredients(null);
                transaction = entityManager.getTransaction();

                transaction.begin();
                entityManager.remove(recette);
                transaction.commit();
                for (int index = 0; index < listeIngredient.size(); index++) {
                    Ingredient ingredient = listeIngredient.get(index);
                        ingredient = entityManager.find(Ingredient.class, ingredient.getId());
                        ingredient = ingredientDaoInterface.deleteIngredient(ingredient.getId());
                        
                }
                
                

                System.out.println("<-----------Suppression de la recette avec succès. ------->");

//. ---------------------------------------FIN-------------------------------------------------- 
                return recette;
            }
            System.out.println("<----------- Aucun utilisateur associé à cette id n'a pus être trouvé pour la suppression. ------->");

            return null;

        } catch (Exception ex) {
            transaction.rollback();

            System.out.println("Erreur lors de la tentative de suppression d'un utilisateur. \n");

            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
       
    }
}
