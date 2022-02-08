/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
import com.doranco.entities.Utilisateur;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author samha
 */
public class UtilisateurDaoImp implements UtilisateurDaoInterface {

    private DaoFactory daoFactory;

    public UtilisateurDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Utilisateur create(Utilisateur user) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        //Code d'Abdel Mohamed Ali
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String formatDate = dateFormat.format(date);

        user.setDateCreation(date);

        //Hashage du mot de passe à la creation
        String salt = BCrypt.gensalt();
        String PasswordHash = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(PasswordHash);
        user.setSalt(salt);

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            System.out.println("<----------- Creation de l'utilisateur avec succes ------->");
            System.out.println(user);
            return user;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la creation de l'utilisateur \n");
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

        //Variable pour garder temp le mdp non hashé
        String passwordTemp = user.getPassword();

        //Maintea=nant on compare (dans l'autre sens, ca marche pas)
        user = findUserByName(user);

        //Si l'utilisateur n'existe pas
        if (user == null) {
            return null;
        }

        if (comparePassword(passwordTemp, user)) {
            return user;
        }

        return null;
       
    }

    @Override
    public Utilisateur findUserByName(Utilisateur user) {

        EntityManager entityManager = daoFactory.getEntityManager();

        Query query = entityManager.createQuery("SELECT user from Utilisateur user WHERE username =:username");

        query.setParameter("username", user.getUsername());

        //Si c'est nul
        if (query.getResultList().isEmpty()) {
            System.out.println("Ce username n'existe pas.");
            return null;
        }

        //Sinon (Penser à caster puisque la méthode attend un objet Utilisateur en retour)
        user = (Utilisateur) query.getResultList().get(0);
        System.out.println("Utilisateur trouvé dans la base : " + user);
        return user;

    }
    
    
    @Override
    public Utilisateur findUserById(int id) {
        EntityManager entityManager = daoFactory.getEntityManager();

        Query query = entityManager.createQuery("SELECT user from Utilisateur user WHERE user.id =:id");

        query.setParameter("id", id);

        //Si c'est nul
        if (query.getResultList().isEmpty()) {
            System.out.println("Cet utilisateur n'existe pas.");
            return null;
        }

        //Sinon (Penser à caster puisque la méthode attend un objet Utilisateur en retour)
        Utilisateur user = (Utilisateur) query.getResultList().get(0);
        System.out.println("Utilisateur trouvé dans la base : " + user);
        return user;
    }
    
    

    @Override
    public boolean comparePassword(String passwordTemp, Utilisateur user) {
        String passwordHash = BCrypt.hashpw(passwordTemp, user.getSalt());

        return passwordHash.compareTo(user.getPassword()) == 0;
    }

    @Override
    public List<Utilisateur> getListeUtilisateur() {
        EntityManager entityManager = null;
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        try {
            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT util FROM Utilisateur util", Utilisateur.class);
            listeUtilisateurs = query.getResultList();
        } catch (Exception ex) {

            System.out.println("Erreur liste Utilisateurs \n" + ex);
//            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listeUtilisateurs;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            System.out.println("<----------- Creation Utilisateur avec succes ------->");
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
    public Utilisateur readUtilisateur(int id) {
        return daoFactory.getEntityManager().find(Utilisateur.class, id);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur, int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Utilisateur utilisateurAModifier = entityManager.find(Utilisateur.class, id);
            if (utilisateurAModifier != null) {
                transaction = entityManager.getTransaction();

                /**
                 * Gestion de la date et du format de son affichage en BDD lors de la modif
                 */
                Date now = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                System.out.println(simpleDateFormat.format(now));
              
                //Hashage du mot de passe à la modification
                String salt = BCrypt.gensalt();
                String PasswordHash = BCrypt.hashpw(utilisateurAModifier.getPassword(), salt);
            
                /**
                 * Liste des champs modifiable lors de la mise à jour d'un utilisateur
                 * Les 4 premiers doivent être accessibles à un USER
                 * Les 2 autres sont des champs réservés aux ADMIN
                 */
                utilisateurAModifier.setUsername(utilisateur.getUsername());
                utilisateurAModifier.setPassword(PasswordHash);
                utilisateurAModifier.setSalt(salt);
                utilisateurAModifier.setEmail(utilisateur.getEmail());
                
                utilisateurAModifier.setDateModification(now);
                
                utilisateurAModifier.setRole(utilisateur.getRole());
                utilisateurAModifier.setActif(utilisateur.isActif());

                transaction.begin();
                entityManager.persist(utilisateurAModifier);
                transaction.commit();
                System.out.println("<----------- Mise a jour etudiant avec success ------->");
                return utilisateur;

            }
            System.out.println("<----------- etudiant avec id non trouve ------->");
            return null;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur mise a jour etudiant \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public boolean deleteUtilisateur(int id) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            Utilisateur utilisateurAModifier = entityManager.find(Utilisateur.class, id);
            if (utilisateurAModifier != null) {
                transaction = entityManager.getTransaction();

                transaction.begin();
                entityManager.remove(utilisateurAModifier);
                transaction.commit();
                System.out.println("<-----------Suppression avec success ------->");
                return true;

            }
            System.out.println("<----------- Utilisateur avec id non trouvee ------->");
            return false;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur mise a jour Utilisateur \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;

    }

    

}
