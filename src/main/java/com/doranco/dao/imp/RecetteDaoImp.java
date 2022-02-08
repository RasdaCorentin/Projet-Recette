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

// ------------------------------------------Methode--------------------------------------------------              
// Marche pour ajouter une recette / utilisateur
            UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
            utilisateur = utilisateurDaoInterface.findUtilisateurByNom(utilisateur);

            if (utilisateur != null) {
                //Start transaction
                transaction.begin();
//          Ajouter les ingredient

                List<Ingredient> listeIngredient = new ArrayList<>(recette.getListeIngredients());
                IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
                for (int index = 0; index < listeIngredient.size(); index++) {
                    Ingredient ingredient = listeIngredient.get(index);
                    ingredient.setRecette(recette);
                    System.out.println(ingredient);
                    ingredient = ingredientDaoInterface.createIngredient(ingredient);
                    entityManager.persist(ingredient);
                }

//          Ajouter la recette           
                recette.setUtilisateur(utilisateur);
                recette.setDateCrea(dtf.format(now));
                recette.setDateModif(dtf.format(now));

                entityManager.persist(recette);

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
                                                Outils 
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
        recette.toString2();
        return recette;
    }
}
