/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.UtilisateurInterface;
import com.doranco.entities.Utilisateur;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Admin
 */
public class UtilisateurDaoImp implements UtilisateurInterface {

    private DaoFactory daoFactory;

    public UtilisateurDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Utilisateur create(Utilisateur user) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        //conversion date
        Date date=Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate=dateFormat.format(date);
        user.setDateCreation(strDate);
        user.setDateModification(strDate);
        
        // Mdp hash
        String salt = BCrypt.gensalt();
        String mdpHash = BCrypt.hashpw(user.getMdp(), salt);
        user.setMdp(mdpHash);
        user.setSalt(salt);

        user.setActif(true);
        
        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            System.out.println("<----------- Creation Utilisateur avec succès ------->");
            System.out.println(user);
            return user;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur création Utilisateur \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Utilisateur login(Utilisateur user) {
        EntityManager entityManager = null;

        entityManager = daoFactory.getEntityManager();
        
        String mdpTemp = user.getMdp();
        
        user = findUserByNom(user);
        if (user == null) {
            return null;
        }
        
        if (comparerMdp(mdpTemp, user)) {
            return user;
        }
        return null;
    }

    @Override
    public Utilisateur findUserByNom(Utilisateur user) {
        EntityManager entityManager = daoFactory.getEntityManager();

        Query query = entityManager.createQuery("SELECT user from Utilisateur user WHERE nom=:username");
        query.setParameter("username", user.getNom());

        if (query.getResultList().isEmpty()) {
            System.out.println("Ce nom d'utilisateur n'existe pas");
            return null;
        }
        user = (Utilisateur) query.getResultList().get(0);
        System.out.println("Utilisateur trouvé " + user);
        return user;
    }

    @Override
    public boolean comparerMdp(String mdpTemp, Utilisateur user) {
        String mdpHash = BCrypt.hashpw(mdpTemp, user.getSalt());
        
        return mdpHash.compareTo(user.getMdp()) == 0;
    }

    @Override
    public Utilisateur findUserById(Utilisateur user, int id) {
        EntityManager entityManager = daoFactory.getEntityManager();

        Query query = entityManager.createQuery("SELECT user from Utilisateur user WHERE id=:id");
        query.setParameter("id", user.getId());

        if (query.getResultList().isEmpty()) {
            System.out.println("Cet id d'utilisateur n'existe pas");
            return null;
        }
        user = (Utilisateur) query.getResultList().get(0);
        System.out.println("Utilisateur trouvé " + user);
        return user;
    }

    @Override
    public Utilisateur findUserByEmail(String email) {
        EntityManager entityManager = daoFactory.getEntityManager();
        
        Query query = entityManager.createQuery("SELECT user from Utilisateur user WHERE email=:email");
        query.setParameter("email", email);

        if (query.getResultList().isEmpty()) {
            System.out.println("Cet email d'utilisateur n'existe pas");
            return null;
        }
        Utilisateur user = (Utilisateur) query.getResultList().get(0);
        System.out.println("Utilisateur trouvé " + user);
        return user;
    }


    @Override
    public boolean update(Utilisateur user, int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        
        //conversion date
        Date date=Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate=dateFormat.format(date);
        user.setDateModification(strDate);
        
        // Mdp hash
        String salt = BCrypt.gensalt();
        String mdpHash = BCrypt.hashpw(user.getMdp(), salt);
        user.setMdp(mdpHash);
        user.setSalt(salt);
        
        try {
            entityManager = daoFactory.getEntityManager();

            Utilisateur utilisateurAModifier = entityManager.find(Utilisateur.class, user.getId());
            if (utilisateurAModifier != null) {
                transaction = entityManager.getTransaction();

                utilisateurAModifier.setNom(user.getNom());
                utilisateurAModifier.setEmail(user.getEmail());
                utilisateurAModifier.setMdp(user.getMdp());

                transaction.begin();
                entityManager.persist(utilisateurAModifier);
                transaction.commit();
                System.out.println("<----------- Mise à jour user avec success ------->");
                return true;

            }
            System.out.println("<----------- Utilisateur avec id non trouvé ------->");
            return false;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur mise à jour user \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public boolean desactiver(int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Utilisateur utilisateurAModifier = entityManager.find(Utilisateur.class, id);
            if (utilisateurAModifier != null && utilisateurAModifier.isActif()) {
                transaction = entityManager.getTransaction();

                utilisateurAModifier.setActif(false);
                transaction.begin();
                entityManager.persist(utilisateurAModifier);
                transaction.commit();
                System.out.println("<-----------Désactivation avec succès ------->");
                return true;

            }
            System.out.println("<----------- Utilisateur avec id non trouvé ------->");
            return false;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur mise à jour user \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;
    }

       
}
