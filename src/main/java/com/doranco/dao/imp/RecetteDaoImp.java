/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.IngredientDaoInterface;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
import com.doranco.dao.interfaces.RecetteDaoInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
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
public class RecetteDaoImp implements RecetteDaoInterface {

    private DaoFactory daoFactory;
    
    private UtilisateurDaoInterface utilisateurDaoInterface;

    public RecetteDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
    }

    @Override
    public List<Recette> getListeRecette() {
        EntityManager entityManager = null;
        List<Recette> listeRecettes = new ArrayList<>();
        try {
            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT rec FROM Recette rec", Recette.class);
            listeRecettes = query.getResultList();
        } catch (Exception ex) {

            System.out.println("Erreur liste Recettes \n" + ex);

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listeRecettes;
    }

    @Override
    public Recette createRecette(Recette recette) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;     
        
        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

            /**
             * Gestion alambiquée du format de la date, parce que pourquoi pas ?
             * (Sinon, le but est d'obtenir un format spécifique et très précis, ici)
             */
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
            String formatDate = dateFormat.format(date);

            //Initialisation de la date de création
            recette.setDateCreation(date);

            //Début des affaires sérieures
            transaction.begin();
          
            //Ajout du tableau des ingrédients
            List<Ingredient> listeIngredients = new ArrayList<>(recette.getListeIngredients());
            
            //Appel de l'interface pour les ingredients
            //IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
            //Boucle pour parcourir la ArrayList
            int i = 0;
            while(i < listeIngredients.size()){
                Ingredient ingredient = listeIngredients.get(i);
                ingredient.setRecette(recette);
                ingredient.setDateCreation(date);
                entityManager.persist(ingredient);
                i++;
            }
            
            //On persiste la recette en base
            entityManager.persist(recette);
            
            transaction.commit();
            System.out.println("<----------- Creation de la recette avec succes ------->");
            return recette;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la creation de la recette \n");
            ex.printStackTrace();

        } finally {
            System.out.println("Ici intervient la clause FINALLY");
            
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Recette readRecette(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recette updateRecette(Recette recette, int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Recette recetteAModifier = entityManager.find(Recette.class, id);
            if (recetteAModifier != null) {
                transaction = entityManager.getTransaction();

                Date now = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                System.out.println(simpleDateFormat.format(now));

                recetteAModifier.setLibelle(recette.getLibelle());
                recetteAModifier.setDescription(recette.getDescription());
                recetteAModifier.setRefImage(recette.getRefImage());

                recetteAModifier.setUtilisateur(recette.getUtilisateur());

                recetteAModifier.setDateModification(now);

                transaction.begin();
                entityManager.persist(recetteAModifier);
                transaction.commit();
                System.out.println("<----------- Mise a jour Recette avec success ------->");
                return recette;

            }
            System.out.println("<----------- Recette avec id non trouve ------->");
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

    @Override
    public boolean deleteRecette(int id) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Recette recetteAModifier = entityManager.find(Recette.class, id);
            if (recetteAModifier != null) {
                transaction = entityManager.getTransaction();

                transaction.begin();
                entityManager.remove(recetteAModifier);
                transaction.commit();
                System.out.println("<-----------Suppression avec succes ------->");
                return true;

            }
            System.out.println("<----------- Recette non trouvee avec l'id fournie ------->");
            return false;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la mise a jour de la Recette \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;

    }

    @Override
    public Recette findRecetteById(int id) {
        
        EntityManager entityManager = daoFactory.getEntityManager();

        Query query = entityManager.createQuery("SELECT recette from Recette recette WHERE recette.id =:id");

        query.setParameter("id", id);

        //Si c'est nul
        if (query.getResultList().isEmpty()) {
            System.out.println("Cette recette n'existe pas.");
            return null;
        }

        //Sinon (Penser à caster puisque la méthode attend un objet Utilisateur en retour)
        Recette recette = (Recette) query.getResultList().get(0);
        System.out.println("Recette trouvée dans la base : " + recette);
        return recette;
        
    }

}
