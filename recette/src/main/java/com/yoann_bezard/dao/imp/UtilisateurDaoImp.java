package com.yoann_bezard.dao.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.yoann_bezard.dao.DaoFactory;
import com.yoann_bezard.dao.entiites.Utilisateur;
import com.yoann_bezard.dao.interfaces.UtilisateurInterface;

import org.mindrot.jbcrypt.BCrypt;

public class UtilisateurDaoImp implements UtilisateurInterface {
    
    private DaoFactory daoFactory;
    
    public UtilisateurDaoImp( DaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        List<Utilisateur> listUtilisateurs = new ArrayList<>();

        try {
            entityManager = daoFactory.getEntityManager();
            Query query = entityManager.createQuery( "SELECT user FROM Utilisateur user" );
            listUtilisateurs = query.getResultList();
        } catch ( Exception ex ) {
            System.out.println( "Il y a eu une erreur lors de la récupération des utilisateurs :\n" + ex );
            return null;
        }

        return listUtilisateurs;
    }

    /**
     * Création d'un nouvel utilisateur.
     * @param user
     * @return 
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        //: Je crypte le mot de passe.
        String salt = BCrypt.gensalt();
        String passwordHash = BCrypt.hashpw( user.getMdp(), salt );
        //: J'enregistre le mot de passe crypté.
        user.setMdp( passwordHash );
        //: Et j'enregistre sa salt afin de le décrypter plus tard.
        user.setSalt( salt );
        
        //£ J'ajoute la date en même temps que le mot de passe.
        user.setDateCreation( new Date() );
        user.setDateModification( new Date() );
        
        //£ J'indique que l'utilisateur est actif dès sa création.
        user.setActif( true );

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();
            
            transaction.begin();
            entityManager.persist( user );
            transaction.commit();
            System.out.println( "<----------La création de l'utilisateur à été terminer avec succès.---------->" );
            System.out.println( user );
            return user;

        } catch( Exception ex ) {
            transaction.rollback();
            System.out.println( "Il y a eu une erreur lors de la création de l'utilisateur.\n" + ex );
            ex.printStackTrace();
        } finally {
            if ( entityManager != null ) entityManager.close();
        }

        return null;
    }

    /**
     * Se connecter.
     * @param user
     * @return 
     */
    @Override
    public Utilisateur loginUtilisateur(Utilisateur user) {
        EntityManager entityManager = null;
        entityManager = daoFactory.getEntityManager();

        String passwordTemp = user.getMdp();
        user = findUserByEmail( user );

        if( user == null ) return null;

        if( comparerPassword( passwordTemp, user ) ) return user;

        return null;
    }

    /**
     * Trouver un utilisateur en utilisant son email.
     * @param user
     * @return 
     */
    @Override
    public Utilisateur findUserByEmail(Utilisateur user) {
        EntityManager entityManager = daoFactory.getEntityManager();

        //§ Je prépare la requête pour chercher l'adresse e-mail de l'utilisateur qui essaye de se connecter dans la base de donnée. 
        Query query = entityManager.createQuery( "SELECT user FROM Utilisateur user WHERE email=:email" );
        query.setParameter( "email", user.getEmail() );

        //) Si ma requête revient vide :
        if( query.getResultList().isEmpty() ) {
            //) Alors j'indique à l'utilisateur que son adresse e-mail n'existe pas dans notre base de donnée.
            System.out.println( "Votre adresse e-mail n'a pas été trouvé dans notre base de donnée." );
            return null;
        }
        //. Si j'ai un résultat, alors je le récupère à la première position de ma liste ( il n'y en aura qu'un seul car les e-mails sont uniques dans cette base de donnée ), l'affiche puis le renvoie à ma fonction login.
        user = ( Utilisateur ) query.getResultList().get( 0 );
        System.out.println( "Voici l'utilisateur associé à cette adresse e-mail :\n" + user );
        return user;
    }

    /**
     * Comparer le mot de passe donné par l'utilisateur et celui disponible en base de donnée.
     * @param passwordTemp
     * @param user
     * @return 
     */
    @Override
    public boolean comparerPassword(String passwordTemp, Utilisateur user) {
        //: Je fait appel à une fonction de BCrypt afin de décrypter le mot de passe.
        String passwordHash = BCrypt.hashpw( passwordTemp, user.getSalt() );
        //: Puis je compare le mot de passe de la base de donnée et celui qui vient d'être fournie.
        return passwordHash.compareTo( user.getMdp() ) == 0;
    }

    /**
     * Mettre à jour un utilisateur.
     * @param user
     * @param id
     * @return 
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user, int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();

            Utilisateur utilisateurAModifier = entityManager.find( Utilisateur.class, id );

            if( utilisateurAModifier != null ) {
                transaction = entityManager.getTransaction();

                utilisateurAModifier.setDateModification( new Date() );
                utilisateurAModifier.setEmail( user.getEmail() );
                utilisateurAModifier.setNom( user.getNom() );
                utilisateurAModifier.setRoleUtilisateur( user.getRoleUtilisateur() );

                //: Je dois crypter le nouveau mot de passe avant de l'envoyer.
                String salt = BCrypt.gensalt();
                String passwordHash = BCrypt.hashpw( user.getMdp(), salt );

                utilisateurAModifier.setSalt( salt );
                utilisateurAModifier.setMdp( passwordHash);

                transaction.begin();
                entityManager.persist( utilisateurAModifier );
                transaction.commit();
                System.out.println( "<----------La mise à jour de l'utilisateur à été effectuer avec succès.---------->" );
                return user;
            }
            System.out.println( "<----------Aucun utilisateur n'a été trouvé pour la modification.---------->" );
            return user;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println( "<----------Il y a eu une erreur lors de la mise à jour d'un utilisateur.\n");
            ex.printStackTrace();
        } finally {
            if ( entityManager != null ) entityManager.close();
        }

        return user;
    }

    @Override
    public Utilisateur deactivateUtilisateur(String email) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery( "SELECT user FROM Utilisateur user WHERE email=:email" );
            query.setParameter( "email", email );
            Utilisateur utilisateur = (Utilisateur) query.getResultList().get(0);

            Utilisateur utilisateurADesactiver = entityManager.find( Utilisateur.class, utilisateur.getId() );

            if( utilisateurADesactiver != null ) {
                transaction = entityManager.getTransaction();

                utilisateurADesactiver.setDateModification( new Date() );
                utilisateurADesactiver.setActif( false );

                transaction.begin();
                entityManager.persist( utilisateurADesactiver );
                transaction.commit();
                System.out.println( "<----------La désactivation de l'utilisateur à été effectuer avec succès.---------->" );
                return utilisateurADesactiver;
            }
            System.out.println( "<----------Aucun utilisateur n'a été trouvé, la désactivation à été impossible.---------->" );
            return utilisateurADesactiver;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println( "<----------Il y a eu une erreur lors de la désactivation de l'utilisateur.---------->\n" );
            ex.printStackTrace();
        } finally {
            if( entityManager != null ) entityManager.close();
        }

        return null;
    }

    @Override
    public Utilisateur reactivateUtilisateur(String email) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery( "SELECT user FROM Utilisateur user WHERE email=:email" );
            query.setParameter( "email", email );
            Utilisateur utilisateur = (Utilisateur) query.getResultList().get(0);

            Utilisateur utilisateurAActiver = entityManager.find( Utilisateur.class, utilisateur.getId() );

            if( utilisateurAActiver != null ) {
                transaction = entityManager.getTransaction();

                utilisateurAActiver.setDateModification( new Date() );
                utilisateurAActiver.setActif( true );

                transaction.begin();
                entityManager.persist( utilisateurAActiver );
                transaction.commit();
                System.out.println( "<----------L'activation de l'utilisateur à été effectuer avec succès.---------->" );
                return utilisateurAActiver;
            }
            System.out.println( "<----------Aucun utilisateur n'a été trouvé, l'activation à été impossible.---------->" );
            return utilisateurAActiver;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println( "<----------Il y a eu une erreur lors de l'activation de l'utilisateur.---------->\n" );
            ex.printStackTrace();
        } finally {
            if( entityManager != null ) entityManager.close();
        }

        return null;
    }

    /**
     * Supprimer un utilisateur.
     * @param id
     * @return 
     */
    @Override
    public Utilisateur deleteUtilisateur( int id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        Utilisateur user = new Utilisateur();

        try {
            entityManager = daoFactory.getEntityManager();

            Utilisateur utilisateurASupprimer = entityManager.find( Utilisateur.class, id );

            if( utilisateurASupprimer != null ) {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.remove( utilisateurASupprimer );
                transaction.commit();
                System.out.println( "<----------L'utilisateur à été supprimé avec succès.---------->" );
                return utilisateurASupprimer;
            }
            System.out.println( "<----------Aucun utilisateur n'a pus être trouvé pour la suppression.---------->" );
            return utilisateurASupprimer;
        }catch( Exception ex ) {
            transaction.rollback();
            System.out.println( "<----------Il y a eu une erreur lors de la suppression de l'utilisateur.---------->\n" );
            ex.printStackTrace();
        } finally {
            if ( entityManager != null ) entityManager.close();
        }

        return user;
    }

}