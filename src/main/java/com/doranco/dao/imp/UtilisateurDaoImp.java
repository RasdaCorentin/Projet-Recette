/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.entities.Utilisateur;
import com.doranco.interfaces.UtilisateurInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private EntityManager entityManager = null;
    private EntityTransaction transaction = null;

    public UtilisateurDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        entityManager = daoFactory.getEntityManager();
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        System.out.println("<----------- Recuperation liste utilisateurs  ----------->");
        List<Utilisateur> listeUsers = new ArrayList<>();
        try {
            entityManager = daoFactory.getEntityManager();
            Query query = entityManager.createQuery("SELECT user from Utilisateur user");
            listeUsers = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Erreur recuperation liste des utlisateurs: " + ex);
            return null;
        }
        return listeUsers;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        System.out.println("<----------- Appel methode create user ------->");
        // Password hash
        String salt = BCrypt.gensalt();
        String passwordHash = BCrypt.hashpw(user.getMdp(), salt);
        user.setMdp(passwordHash);
        user.setSalt(salt);
        user.setActif(true);
        user.setDateCreation(new Date().toString());
        user.setDateModification(new Date().toString());

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            System.out.println("<----------- Creation Utilisateur avec success ------->");
            System.out.println(user);
            return user;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur creation Utilisateur \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user, String email) {
        Utilisateur userBd = this.findUtilisateurByEmail(email);
        if (userBd != null) {
            System.out.println("<----------- Appel methode update user ------->");
            // Password hash
            String salt = BCrypt.gensalt();
            String passwordHash = BCrypt.hashpw(user.getMdp(), salt);
            userBd.setMdp(passwordHash);
            userBd.setSalt(salt);
            userBd.setActif(true);
            userBd.setNom(user.getNom());
            userBd.setEmail(user.getEmail());
            userBd.setDateModification(new Date().toString());

            try {
                entityManager =daoFactory.getEntityManager();
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.merge(userBd);
                transaction.commit();
                System.out.println("<----------- Update Utilisateur avec success ------->");
                System.out.println(userBd);
                return userBd;

            } catch (Exception ex) {
                transaction.rollback();
                System.out.println("Erreur update Utilisateur \n" + ex.getMessage());

            } finally {
                if (entityManager != null) {
                    entityManager.close();
                }
            }
        }
        return null;
    }

    @Override
    public Utilisateur loginUtilisateur(Utilisateur user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String email) {
        Utilisateur user = new Utilisateur();
        Query query = entityManager.createQuery("SELECT user from Utilisateur user WHERE email=:email");
        query.setParameter("email", email);

        if (query.getResultList().isEmpty()) {
            System.out.println("Ce nom utilisateur n'existe pas");
            return null;
        }
        user = (Utilisateur) query.getResultList().get(0);
        System.out.println("Utilisateur trouve" + user);
        return user;
    }

    @Override
    public boolean desactiverUtilisateur(String email) {

        Utilisateur user = this.findUtilisateurByEmail(email);
        if (user == null) {
            System.out.println("Email non trouve");
            return false;
        }
        user.setActif(false);

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            System.out.println("<----------- Desactivation Utilisateur avec success ------->");
            System.out.println(user);

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur desactivation Utilisateur \n" + ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return true;
    }

    @Override
    public boolean comparerPassword(String passwordTemp, Utilisateur user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteUtilisateur(int id) {
        Utilisateur user = this.findUtilisateurId(id, false);
         if (user == null) {
            return "Erreur supression. Aucun ID correspondant";
        }
        try {
            
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(user);
            transaction.commit();
            return "Supression utilisateur avec success";
            
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

    @Override
    public Utilisateur findUtilisateurId(int id, boolean  closeEntityManager) {
        Utilisateur user = new Utilisateur();
        try {
            Query query = entityManager.createQuery("SELECT user from Utilisateur user WHERE id=:id");
            query.setParameter("id", id);
            if (query.getResultList().isEmpty()) {
                System.out.println("Cet utilisateur n'existe pas");
                return null;
            }
            user = (Utilisateur) query.getResultList().get(0);
            System.out.println("Utilisateur trouve" + user);
        } catch (Exception ex) {
            System.out.println("Erreur recuperation liste" + ex.getMessage());
        } finally {
            if (entityManager != null && closeEntityManager) {
                entityManager.close();
            }
        }
        return user;
    }
    

}
