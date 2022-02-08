/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.RecetteInterface;
import com.doranco.dao.interfaces.UtilisateurInterface;
import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author elair
 */
public class RecetteDaoImp implements RecetteInterface{
    private DaoFactory daoFactory;
    
    public RecetteDaoImp(DaoFactory daoFactory) {
        this.daoFactory=daoFactory;
    }
   
    @Override
    public Recette create(Recette recette) {
        EntityManager entityManager=null;
        EntityTransaction transaction=null;
        
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String strDate=dtf.format(now);
        
        recette.setDateCreation(strDate);
        recette.setDateModification(strDate);
        
        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(recette);
            transaction.commit();
            System.out.println("<----------- Création Recette avec succès ------->");
            System.out.println(recette);
            return recette;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur création Recette \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Recette update(Recette recette, int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        
        //conversion date
        Date date=Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate=dateFormat.format(date);
        recette.setDateModification(strDate);
        
        try {
            entityManager = daoFactory.getEntityManager();

            Recette recetteAModifier = entityManager.find(Recette.class, recette.getId());
            if (recetteAModifier != null) {
                transaction = entityManager.getTransaction();

                recetteAModifier.setLibelle(recette.getLibelle());
                recetteAModifier.setDescription(recette.getDescription());
                recetteAModifier.setReferenceImage(recette.getReferenceImage());

                transaction.begin();
                entityManager.persist(recetteAModifier);
                transaction.commit();
                System.out.println("<----------- Mise à jour recette avec success ------->");
                return recetteAModifier;

            }
            System.out.println("<----------- Recette avec id non trouvé ------->");
            

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur mise à jour recette \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Recette findRecetteByLibelle(String libelle) {
        EntityManager entityManager = daoFactory.getEntityManager();

        Query query = entityManager.createQuery("SELECT recette from Recette recette WHERE libelle=:libelle");
        query.setParameter("libelle", libelle);

        if (query.getResultList().isEmpty()) {
            System.out.println("Ce nom de recette n'existe pas");
            return null;
        }
        Recette recette = (Recette) query.getResultList().get(0);
        System.out.println("Recette trouvée" + recette);
        return recette;
    }
    
    @Override
    public Recette findRecetteById(int id) {
        EntityManager entityManager = daoFactory.getEntityManager();

        Query query = entityManager.createQuery("SELECT recette from Recette recette WHERE id=:id");
        query.setParameter("id", id);
        
        if (query.getResultList().isEmpty()) {
            System.out.println("Ce nom de recette n'existe pas");
            return null;
        }
        Recette recette = (Recette) query.getResultList().get(0);
        System.out.println("Recette trouvée" + recette);
        return recette;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

            
}
