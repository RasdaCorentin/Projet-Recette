package com.yoann_bezard.controllers;

import java.io.IOException;
import java.util.Base64;

import com.yoann_bezard.dao.DaoFactory;
import com.yoann_bezard.dao.entiites.Utilisateur;
import com.yoann_bezard.dao.interfaces.UtilisateurInterface;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println( "<----------Exécution de ContainerRequestFilter.---------->" );

        //) Je récupère l'url de la page.
        String urlPath = requestContext.getUriInfo().getPath();

        if ( urlPath.contains("guest") ) {
            return;
        } else {
            //. Je récupère la valeur de l'en-tête Authorization qui contient les données à traiter.
            String basicAuth = requestContext.getHeaderString( HttpHeaders.AUTHORIZATION );

            //: Ensuite j'isole l'e-mail et le mot de passe qui sont encodé en base 64.
            basicAuth = basicAuth.replace( "Basic ", "" );

            //£ Je vais maintenant les décoder afin de les rendre lisible.
            String authDecode = new String( Base64.getDecoder().decode( basicAuth ) );

            //$ Puis je sépare l'e-mail et le mot de passe en enlevant les : qui les sépare, afin de les stocker dans un tableau.
            String[] credentials = authDecode.split( ":" );

            //. Puis enfin je les stocke dans deux variables différentes.
            String email = credentials[0];
            String mdp = credentials[1];

            //+ Je configure ma variable utilisateur en y intégrant l'email et le mot de passe que je viens de récupérer afin de me préparer à vérifier ces informations en base de donnée.
            Utilisateur user = new Utilisateur( email, mdp );

            DaoFactory daoFactory = new DaoFactory();
            UtilisateurInterface utilisateurInterface = daoFactory.getUtilisateurInterface();

            //) Je récupère les identifiants qui me sont fournie.
            user = utilisateurInterface.loginUtilisateur( user );
            
            if( user != null ) { //= Si mon utilisateur est bien définit.

                //= Je commence par vérifier que le compte est bien actif.
                if( user.isActif() ) {

                    //) Ensuite je vérifie si l'url contient le mot "admin".
                    if( urlPath.contains("admin") ) {
                        //) Si elle le contient alors je vérifie que l'utilisateur à bien le droit de voir cette page.
                        if( user.isAdmin() ) {
                            //) S'il est bien admin alors je le laisse chargé la page.
                            return;
                        //: Sinon je le prévient qu'il n'a pas le droit d'accéder à la page.
                        } else {
                            Response response = Response
                                .status( Response.Status.FORBIDDEN )
                                .entity( "Vous n'avez pas les droits nécessaire pour consulter cette page." )
                                .build();
                            requestContext.abortWith( response );
                        }
                    }
                    //§ Si le mot "admin" n'est pas présent dans l'url alors je ne prend pas la peine de vérifier le statue de l'utilisateur et lui affiche directement la page.
                    return;
                }

                //, Si l'utilisateur est bien actif et que l'url de la page ne contient pas le mot "admin", alors je le laisse voir la page dans tout les cas.
                return;
            }

            ///§ Si l'e-mail et/ou le mot de passe ne correspondent à rien dans ma base de donnée alors j'empêche la connexion.
            Response response = Response
                .status( Response.Status.FORBIDDEN )
                .entity( "La connexion a échoué, vérifier que votre e-mail et votre mot de passe sont corrects." )
                .build();

            requestContext.abortWith( response );
        }

    }
    
}