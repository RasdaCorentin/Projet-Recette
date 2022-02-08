/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.entities.Recette;
import com.doranco.entities.Utilisateur;
import com.doranco.interfaces.RecetteInterface;
import com.doranco.interfaces.UtilisateurInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class RecetteDaoImp implements RecetteInterface {

    private final DaoFactory daoFactory;
    private EntityManager entityManager = null;
    private EntityTransaction transaction = null;
    private UtilisateurInterface utilisateurInterface;

    public RecetteDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        entityManager = daoFactory.getEntityManager();

    }

    @Override
    public List<Recette> findAllRecettes() {
        List<Recette> listRecettes = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT r FROM Recette r", Recette.class);
            listRecettes = query.getResultList();
        } catch (Exception ex) {

            System.out.println("Erreur lister Recettes \n" + ex);

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listRecettes;
    }

    @Override
    public Recette createRecette(Recette recette, String email) {
        this.utilisateurInterface = daoFactory.getUtilisateurInterface();
        recette.setDateCreation(new Date().toString());
        recette.setDateModification(new Date().toString());

        Utilisateur user = utilisateurInterface.findUtilisateurByEmail(email);

        if (user == null) {
            return null;
        }
        recette.setUtilisateur(user);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(recette);
            transaction.commit();
            System.out.println("<----------- Creation Recette avec success ------->");
            System.out.println(recette);
            return recette;

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

    @Override
    public Recette updateRecette(Recette recette) {

        Recette recetteBd = this.findRecetteById(recette.getId(), false);

        if (recetteBd == null) {
            return null;
        }
        recetteBd.setLibelle(recette.getLibelle());
        recetteBd.setDescription(recette.getDescription());
        recetteBd.setReference_image(recette.getReference_image());
        recetteBd.setDateModification(new Date().toString());

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(recetteBd);
            transaction.commit();
            System.out.println("<----------- Update Recette avec success ------->");
            System.out.println(recette);
            return recette;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur update Recette \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Recette findRecetteById(int id, boolean closeEntityManager) {
        Recette recette = new Recette();
        try {
            Query query = entityManager.createQuery("SELECT recette from Recette recette WHERE id=:id");
            query.setParameter("id", id);
            if (query.getResultList().isEmpty()) {
                System.out.println("Cette recette n'existe pas");
                return null;
            }
            recette = (Recette) query.getResultList().get(0);
            System.out.println("Recette trouve" + recette);
        } catch (Exception ex) {
            System.out.println("Erreur recuperation liste" + ex.getMessage());
        } finally {
            if (entityManager != null && closeEntityManager) {
                entityManager.close();
            }
        }
        return recette;
    }

    @Override
    public String deleteRecette(int id) {
        Recette recette = this.findRecetteById(id, false);
        if (recette == null) {
            return "Erreur supression. Aucun ID correspondant";
        }
        try {
            
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(recette);
            transaction.commit();
            return "Supression recette avec success";
            
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur BD supression" + ex.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return "Erreur supression BD";
    }
}
