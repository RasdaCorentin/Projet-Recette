/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.doranco.dao.imp;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.iinterface.UtilisateurDaoInterface;
import com.doranco.entities.Utilisateur;

import org.mindrot.jbcrypt.BCrypt;

/*
 * @author 
*/
public class UtilisateurDaoImp implements UtilisateurDaoInterface {

    private DaoFactory daoFactory;
    public UtilisateurDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Liste Utilisateur avec DAO FACTORY 
:--------------------------------------------------------------------------------------------------------------------------
    */

    /**
     * Utilisation de Jquery afin de récupérer une liste d'utilisateur depuis la base de donnée.
    */
    @Override
    public List<Utilisateur> getListeUtilisateurs() {

        EntityManager entityManager = null;
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();

        try {
//. ------------------------------------------------------------------------------------------- 

            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT e FROM Utilisateur e", Utilisateur.class);
            listeUtilisateurs = query.getResultList();


//. ---------------------------------------FIN--------------------------------------------------            
        } catch (Exception ex) {
            System.out.println("Erreur lors de la recherche de la liste des Utilisateurs. \n" + ex);
        } 
        return listeUtilisateurs;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Création Utilisateur avec DAO FACTORY 
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

//. -------------------------------------------------------------------------------------------
            String salt = BCrypt.gensalt();
            String passwordHash = BCrypt.hashpw(utilisateur.getPassword(), salt);
            utilisateur.setPassword(passwordHash);
            utilisateur.setSalt(salt);

            utilisateur.setDateCrea(new Date());
            utilisateur.setStatuts(false);
            utilisateur.setSeau(100);

            // String urlConnection = BCrypt.gensalt();
            // utilisateur.setUrlConnect(urlConnection);

            transaction.begin();
            entityManager.persist(utilisateur);
            transaction.commit();
            System.out.println("<----------- Création du nouvel utilisateur avec succès. ------->");
            return utilisateur;

//. ---------------------------------------FIN--------------------------------------------------

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la création de l'utilisateur. \n");
            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Outils
:--------------------------------------------------------------------------------------------------------------------------
    */

    //. ----------Trouver un utilisateur grâce à son nom.----------
    @Override
    public Utilisateur findUtilisateurByNom(Utilisateur utilisateur) {
        EntityManager entityManager = null;
        entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery("select util from Utilisateur util where nom=:nom");
        query.setParameter("nom", utilisateur.getNom());
        if (query.getResultList().isEmpty()) {
            System.out.println("---------- Ce nom d'utilisateur n'existe pas. ----------");
            return null;
        }
        utilisateur = (Utilisateur) query.getResultList().get(0);
        return utilisateur;
    }

    //. ----------Trouver un utilisateur grâce à son id.----------
    @Override
    public Utilisateur findUtilisateurById(int id) {
        EntityManager entityManager = null;
        Utilisateur utilisateur = new Utilisateur();
        entityManager = daoFactory.getEntityManager();
        Query query = entityManager.createQuery("select util from Utilisateur util where id=:id");
        query.setParameter("id", id);
        if (query.getResultList().isEmpty()) {

            System.out.println("---------- Cet id d'utilisateur n'existe pas. ----------");

            return null;
        }
        utilisateur = (Utilisateur) query.getResultList().get(0);
        return utilisateur;
    }


    //. ----------Permettre à un utilisateur de se connecter.----------

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

    //. ----------Comparer le mot de passe donné par un utilisateur à celui de la base de donnée.----------

    @Override
    public boolean comparePassword(String passwordTemp, Utilisateur utilisateur) {
        String passwordHash = BCrypt.hashpw(passwordTemp, utilisateur.getSalt());
        if (passwordHash.compareTo(utilisateur.getPassword()) == 0) {
            return true;
        }

        System.out.println("---------- Le mot de passe entré ne correspond pas à celui trouvé dans la base de données. ----------");

        return false;
    }

    //. ----------Utilise un jeton.----------


    @Override
    public Utilisateur useJeton(Utilisateur utilisateur) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();
//. -------------------------------------------------------------------------------------------

            if (utilisateur != null) {
                transaction = entityManager.getTransaction();

                    utilisateur.setDateModif(new Date());
                    utilisateur.setSeau(utilisateur.getSeau() - 1);

                    transaction.begin();
                    entityManager.merge(utilisateur);
                    transaction.commit();
                    System.out.println("<----------- Un jeton vient d'être utilisé. ------->");
                    return utilisateur;
            }

            System.out.println("<----------- Utilisation d'un jeton impossible. ------->");
            return null;

//. ---------------------------------------FIN-------------------------------------------------- 
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Il y a eu une erreur lors de l'utilisation d'un jeton. \n");

            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Delete Utilisateur avec DAO FACTORY 
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public boolean deleteUtilisateur(int id) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

//. -------------------------------------------------------------------------------------------

            Utilisateur utilisateur = entityManager.find(Utilisateur.class, id);
            if (utilisateur != null) {
                transaction = entityManager.getTransaction();

                transaction.begin();
                entityManager.remove(utilisateur);
                transaction.commit();
                System.out.println("<-----------Suppression de l'utilisateur avec succès. ------->");

//. ---------------------------------------FIN-------------------------------------------------- 
                return true;
            }
            System.out.println("<----------- Aucun utilisateur associé à cette id n'a pus être trouvé pour la suppression. ------->");

            return false;

        } catch (Exception ex) {
            transaction.rollback();

            System.out.println("Erreur lors de la tentative de suppression d'un utilisateur. \n");

            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Update Utilisateur 
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();
//. -------------------------------------------------------------------------------------------

            //§ Récupération de l'utilisateur qui va être modifié.
            Utilisateur utilisateurAModifier = findUtilisateurByNom(utilisateur);
            if (utilisateurAModifier != null) {
                transaction = entityManager.getTransaction();

                    utilisateurAModifier.setDateModif(new Date());
                    utilisateurAModifier.setNom(utilisateur.getNewNom());
                    utilisateurAModifier.setEmail(utilisateur.getEmail());

                    String salt = BCrypt.gensalt();
                    String passwordHash2 = BCrypt.hashpw( utilisateur.getNewPassword(), salt );
                    utilisateurAModifier.setPassword(passwordHash2);
                    utilisateurAModifier.setSalt(salt);

                    transaction.begin();
                    entityManager.merge(utilisateurAModifier);
                    transaction.commit();
                    System.out.println("<----------- Mise a jour Utilisateur avec succès. ------->");
                    return utilisateurAModifier;
            }
            System.out.println("<----------- Utilisateur avec Nom non trouvé pour l'update. ------->");
            return null;

//. ---------------------------------------FIN-------------------------------------------------- 
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Il y a eu une erreur lors de l'update de l'utilisateur. \n");

            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Update Utilisateur sans nouvel e-mail
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur updateUtilisateurSansEmail(Utilisateur utilisateur) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();
//. -------------------------------------------------------------------------------------------

            //§ Récupération de l'utilisateur qui va être modifié.
            Utilisateur utilisateurAModifier = findUtilisateurByNom(utilisateur);
            if (utilisateurAModifier != null) {
                transaction = entityManager.getTransaction();

                    utilisateurAModifier.setDateModif(new Date());
                    utilisateurAModifier.setNom(utilisateur.getNewNom());

                    String salt = BCrypt.gensalt();
                    String passwordHash2 = BCrypt.hashpw( utilisateur.getNewPassword(), salt );
                    utilisateurAModifier.setPassword(passwordHash2);
                    utilisateurAModifier.setSalt(salt);

                    transaction.begin();
                    entityManager.merge(utilisateurAModifier);
                    transaction.commit();
                    System.out.println("<----------- Mise a jour Utilisateur avec succès. ------->");
                    return utilisateurAModifier;
            }
            System.out.println("<----------- Utilisateur avec Nom non trouvé pour l'update. ------->");
            return null;

//. ---------------------------------------FIN-------------------------------------------------- 
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Il y a eu une erreur lors de l'update de l'utilisateur. \n");

            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Remplir seau
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur remplirSeau(int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();
//. -------------------------------------------------------------------------------------------

            //§ Récupération de l'utilisateur dont le seau a besoin d'être remplie.
            Utilisateur utilisateur = findUtilisateurById(id);

            if (utilisateur != null) {
                transaction = entityManager.getTransaction();

                    utilisateur.setDateModif(new Date());
                    utilisateur.setSeau(100);

                    transaction.begin();
                    entityManager.merge(utilisateur);
                    transaction.commit();
                    System.out.println("<----------- Seau remplie avec succès. ------->");
                    return utilisateur;
            }
            System.out.println("<----------- Aucun utilisateur ne correspond à cette id. ------->");
            return null;

//. ---------------------------------------FIN-------------------------------------------------- 
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Il y a eu une erreur lors du remplissage du seau. \n");

            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Deactivate Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur deactivateUtilisateur(int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            System.out.println("------------------ DÉBUT DEACTIVATE ---------");
//. -------------------------------------------------------------------------------------------

            Utilisateur utilisateurADesactiver = entityManager.find( Utilisateur.class, id );
            if( utilisateurADesactiver != null ) {
                transaction = entityManager.getTransaction();

                utilisateurADesactiver.setDateModif(new Date());
                utilisateurADesactiver.setStatuts(false);

                transaction.begin();
                entityManager.persist( utilisateurADesactiver );
                transaction.commit();
                System.out.println( "<----------La désactivation de l'utilisateur à été effectuer avec succès.---------->" );
                return utilisateurADesactiver;
            }
            System.out.println( "<----------Aucun utilisateur n'a été trouvé, la désactivation à été impossible.---------->" );
            return null;

//. ---------------------------------------FIN--------------------------------------------------
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println( "Erreur deactivate utilisateur. \n" );
            ex.printStackTrace();
        } finally {
            if( entityManager != null ) entityManager.close();
        }
        return null;
    }

        /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Activate Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur activateUtilisateur(int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            System.out.println("------------------ DÉBUT ACTIVATE ---------");
//. -------------------------------------------------------------------------------------------

            Utilisateur utilisateurAActiver = entityManager.find( Utilisateur.class, id );

            if( utilisateurAActiver != null ) {
                transaction = entityManager.getTransaction();

                utilisateurAActiver.setDateModif(new Date());
                utilisateurAActiver.setStatuts(true);

                transaction.begin();
                entityManager.persist( utilisateurAActiver );
                transaction.commit();
                System.out.println( "<----------L'activation de l'utilisateur à été effectuer avec succès.---------->" );
                return utilisateurAActiver;
            }
            System.out.println( "<----------Aucun utilisateur n'a été trouvé, l'activation à été impossible.---------->" );
            return null;

//. ---------------------------------------FIN--------------------------------------------------
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println( "Erreur activate Utilisateur. \n" );
            ex.printStackTrace();
        } finally {
            if( entityManager != null ) entityManager.close();
        }
        return null;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Vanish Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    /**
     * Permet de faire disparaître un utilisateur, son compte deviendra alors inaccessible mais il restera associé à ses recettes afin de ne pas les supprimer du site.
     */
    @Override
    public Utilisateur vanishUtilisateur(Utilisateur utilisateur, int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = daoFactory.getEntityManager();

            System.out.println("------------------ DÉBUT VANISH ---------");
//. -------------------------------------------------------------------------------------------

            utilisateur = entityManager.find(Utilisateur.class, id);
            if (utilisateur != null) {
                transaction = entityManager.getTransaction();

                String salt = BCrypt.gensalt();
                String passwordHash = BCrypt.hashpw(utilisateur.getPassword(), salt);

                utilisateur.setPassword(passwordHash);
                utilisateur.setDateModif(new Date());
                utilisateur.setStatuts(false);

                transaction.begin();
                entityManager.persist(utilisateur);
                transaction.commit();
                System.out.println("<----------- Réussite du vanish. ------->");
                return utilisateur;
            }
            System.out.println("<----------- Aucun utilisateur n'est associé à cette id, vanish impossible. ------->");
            return null;

//. ---------------------------------------FIN--------------------------------------------------
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur vanish Utilisateur. \n");
            ex.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Connecter Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur connectUtilisateur(Utilisateur utilisateur) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

//. -------------------------------------------------------------------------------------------

            Utilisateur nUtilisateur = findUtilisateurByNom(utilisateur);

                    nUtilisateur.setDateModif(new Date());
                    nUtilisateur.setStatuts(true);
                    // nUtilisateur.setUrlConnect(null);

                    transaction.begin();
                    entityManager.merge(nUtilisateur);
                    transaction.commit();
                    System.out.println("<----------- Le statut à bien été mis à jour. ------->");
                    return nUtilisateur;

//. ---------------------------------------FIN--------------------------------------------------
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la mise à jour du status de l'utilisateur. \n");

            ex.printStackTrace();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

        /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Connecter un Utilisateur au site
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur connecterUtilisateur(Utilisateur utilisateur) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();

//. -------------------------------------------------------------------------------------------

            Utilisateur nUtilisateur = findUtilisateurByNom(utilisateur);

                    nUtilisateur.setDateModif(new Date());

                    transaction.begin();
                    entityManager.merge(nUtilisateur);
                    transaction.commit();
                    System.out.println("<----------- Vous avez été connecté avec succès. ------->");
                    return nUtilisateur;

//. ---------------------------------------FIN--------------------------------------------------
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Erreur lors de la connexion de l'utilisateur. \n");
            ex.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    /*
:--------------------------------------------------------------------------------------------------------------------------
                                                % Read Utilisateur
:--------------------------------------------------------------------------------------------------------------------------
    */

    @Override
    public Utilisateur readUtilisateur(int id) {
        
        EntityManager entityManager = null;
        Utilisateur utilisateur = new Utilisateur();
        entityManager = daoFactory.getEntityManager();
        
        Query query = entityManager.createQuery("select util from Utilisateur util where id=:id");
        query.setParameter("id", id);
        if (query.getResultList().isEmpty()) {
            System.out.println("Il n'existe aucun utilisateur d'associer à cette id.");
            return null;
        }
        utilisateur = (Utilisateur) query.getResultList().get(0);
        utilisateur.toString();
        return utilisateur;
    }

}