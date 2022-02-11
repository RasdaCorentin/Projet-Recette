/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.dao.imp;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.UtilisateurDaoInterface;
import com.doranco.entities.Utilisateur;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.mindrot.jbcrypt.BCrypt;

/*
 * @author 
 */
public class UtilisateurDaoImp implements UtilisateurDaoInterface {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    private DaoFactory daoFactory;

    public UtilisateurDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Liste Utilisateur avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
     */
//Utilise Jquery pour avoir une liste d'utilisateur depuis la base de données
    @Override
    public List<Utilisateur> getListeUtilisateurs() {

        EntityManager entityManager = null;
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();

        try {
// ------------------------------------------------------------------------------------------- 

            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT e FROM Utilisateur e", Utilisateur.class);
            listeUtilisateurs = query.getResultList();

// ---------------------------------------FIN--------------------------------------------------            
        } catch (Exception ex) {

            System.out.println("Erreur lister Utilisateurs \n" + ex);
//            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return listeUtilisateurs;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                Création Utilisateur avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

// ------------------------------------------------------------------------------------------- 
            String salt = BCrypt.gensalt();
            String passwordHash = BCrypt.hashpw(utilisateur.getPassword(), salt);
            utilisateur.setDateCrea(dtf.format(now));
            utilisateur.setDateModif(dtf.format(now));
            utilisateur.setPassword(passwordHash);
            utilisateur.setSalt(salt);
            utilisateur.setStatuts(false);
            transaction.begin();
            entityManager.persist(utilisateur);
            transaction.commit();
            System.out.println("<----------- Creation Utilisateur avec success ------->");
            return utilisateur;

// ---------------------------------------FIN-------------------------------------------------- 
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

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                Outils 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public Utilisateur findUtilisateurByNom(Utilisateur utilisateur) {
        EntityManager entityManager = null;
        entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery("select util from Utilisateur util where nom=:nom");
        query.setParameter("nom", utilisateur.getNom());
        if (query.getResultList().isEmpty()) {
            System.out.println("Ce nom utilisateur n'existe pas");
            return null;
        }
        utilisateur = (Utilisateur) query.getResultList().get(0);
        return utilisateur;
    }

    @Override
    public Utilisateur findUtilisateurById(int id) {
        EntityManager entityManager = null;
        Utilisateur utilisateur = new Utilisateur();
        entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery("select util from Utilisateur util where id=:id");
        query.setParameter("id", id);
        if (query.getResultList().isEmpty()) {
            System.out.println("Cet id utilisateur n'existe pas");
            return null;
        }
        utilisateur = (Utilisateur) query.getResultList().get(0);
        return utilisateur;
    }

    /*
    login lié à la compare
     */
    @Override
    public Utilisateur loginUtilisateur(Utilisateur utilisateur) {
        EntityManager entityManager = null;

        entityManager = daoFactory.getEntityManager();
        String passwordTemp = utilisateur.getPassword();

        utilisateur = findUtilisateurByNom(utilisateur);
        if (utilisateur == null) {
            return null;
        }
        if (comparePassword(passwordTemp, utilisateur)) {
            return utilisateur;
        }
        return null;
    }

    @Override
    public boolean comparePassword(String passwordTemp, Utilisateur utilisateur) {
        String passwordHash = BCrypt.hashpw(passwordTemp, utilisateur.getSalt());
        if (passwordHash.compareTo(utilisateur.getPassword()) == 0) {
            return true;
        }
        return false;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                 Delete Utilisateur avec DAO FACTORY 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public boolean deleteUtilisateur(int id) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

// -------------------------------------------------------------------------------------------
            Utilisateur utilisateur = entityManager.find(Utilisateur.class, id);
            if (utilisateur != null) {
                transaction = entityManager.getTransaction();

                transaction.begin();
                entityManager.remove(utilisateur);
                transaction.commit();
                System.out.println("<-----------Supression avec success ------->");

// ---------------------------------------FIN-------------------------------------------------- 
                return true;
            }
            System.out.println("<----------- utilisateur avec id non trouve ------->");
            return false;

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur mise a jour utilisateur \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;
    }

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                Update Utilisateur 
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();
// -------------------------------------------------------------------------------------------
/*
Fonction filtre Comparaison de Mot de passe : //
*/
            //
            String passwordTemp = utilisateur.getPassword();

            //Recupr utilisateur              
            Utilisateur utilisateurAModifier = findUtilisateurByNom(utilisateur);
            if (utilisateurAModifier != null) {
            transaction = entityManager.getTransaction();
                //
                String passwordHash = BCrypt.hashpw(passwordTemp, utilisateurAModifier.getSalt());
                //
                if (passwordHash.compareTo(utilisateurAModifier.getPassword()) == 0) {

                    utilisateurAModifier.setDateModif(dtf.format(now));
                    utilisateurAModifier.setNom(utilisateur.getNewNom());
                    utilisateurAModifier.setEmail(utilisateur.getEmail());
//                    utilisateurAModifier.setPassword(passwordHash);

                    transaction.begin();
                    entityManager.merge(utilisateurAModifier);
                    transaction.commit();
                    System.out.println("<----------- Mise a jour Utilisateur avec success ------->");
                    return utilisateurAModifier;
                }
                //
                System.out.println("<----------- Mot de passe incorrect ------->");
                //
                return utilisateur;
            }
            System.out.println("<----------- Utilisateur avec Nom non trouve ------->");
            return null;

// ---------------------------------------FIN-------------------------------------------------- 
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

    /*
--------------------------------------------------------------------------------------------------------------------------
                                                Deconnecter Utilisateur
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public Utilisateur disconnectUtilisateur(Utilisateur utilisateur, int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            System.out.println("------------------ DEBUT CHANGEMENT ---------");
// -------------------------------------------------------------------------------------------

            utilisateur = entityManager.find(Utilisateur.class, id);
            if (utilisateur != null) {
                transaction = entityManager.getTransaction();

                String salt = BCrypt.gensalt();
                String passwordHash = BCrypt.hashpw(utilisateur.getPassword(), salt);

                utilisateur.setPassword(passwordHash);
                utilisateur.setDateModif(dtf.format(now));
                utilisateur.setStatuts(false);

                transaction.begin();
                entityManager.persist(utilisateur);
                transaction.commit();
                System.out.println("<----------- Statut mis à jour ------->");
                return utilisateur;
            }
            System.out.println("<----------- Utilisateur avec id non trouve ------->");
            return null;

// ---------------------------------------FIN--------------------------------------------------   
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur désactivation Utilisateur \n");
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
                                                Connecter Utilisateur
--------------------------------------------------------------------------------------------------------------------------
     */
    @Override
    public Utilisateur connectUtilisateur(Utilisateur utilisateur) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

// ------------------------------------------------------------------------------------------- 
      
            Utilisateur nUtilisateur = findUtilisateurByNom(utilisateur);

                    nUtilisateur.setDateModif(dtf.format(now));
                    nUtilisateur.setStatuts(true);

                    transaction.begin();
                    entityManager.merge(nUtilisateur);
                    transaction.commit();
                    System.out.println("<----------- Statut mis à jour ------->");
                    return nUtilisateur;

// ---------------------------------------FIN-------------------------------------------------- 
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
       EntityManager entityManager = null;
        Utilisateur utilisateur = new Utilisateur();
        entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery("select util from Recette util where id=:id");
        query.setParameter("id", id);
        if (query.getResultList().isEmpty()) {
            System.out.println("Cet id utilisateur n'existe pas");
            return null;
        }
        utilisateur = (Utilisateur) query.getResultList().get(0);
        utilisateur.toString();
        return utilisateur;
    }
}
