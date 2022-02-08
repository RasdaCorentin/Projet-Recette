/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.doranco;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.IngredientDaoInterface;
import com.doranco.dao.interfaces.RecetteDaoInterface;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
import com.doranco.entities.Ingredient;
import com.doranco.entities.Recette;
import com.doranco.entities.RoleUtilisateur;
import com.doranco.entities.Utilisateur;
import com.doranco.handlercallback.HandlerCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import org.json.JSONObject;

/**
 *
 * @author samha
 */
public class Main {

    public enum Action {
        action1, action2, action3, logout
    }

    public static void main(String[] args) {

//          Test file contents
//        JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
//            System.out.println(jo.toString());
        /**
         * ***************************TEST CREATION INGREDIENT RECETTE UTILISATEUR***********************
         */
//        DaoFactory daoFactory = new DaoFactory();
//
//        UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();
//
//        RecetteDaoInterface recetteDaoInterface = daoFactory.getRecetteDaoInterface();
//
//        IngredientDaoInterface ingredientDaoInterface = daoFactory.getIngredientDaoInterface();
//
//        Ingredient ingredient1 = new Ingredient("Sucre", 250);
//        Ingredient ingredient2 = new Ingredient("Beurre", 125);
//        Ingredient ingredient3 = new Ingredient("Farine", 450);
//        Ingredient ingredient4 = new Ingredient("Cacao", 137);
//        Ingredient ingredient5 = new Ingredient("Mascarpone", 150);
//        Ingredient ingredient6 = new Ingredient("Biscuit", 120);
////
//        Recette recette1 = new Recette("Tiramisu", "Une recette sucrée, une référence de la cuisine italienne", "src/img/tiramisu.jpg");
//        Recette recette2 = new Recette("Kouign Amann", "Une recette sucrée, emblématique de la vuisine bretonne", "src/img/kouign_amann.jpg");        
//
//        Utilisateur user1 = new Utilisateur("LePtitChef", "123", "leptitchef@test.org", RoleUtilisateur.ADMIN);
//        user1 = utilisateurDaoInterface.create(user1);
////        
//        recette1.setUtilisateur(user1);
//        recette2.setUtilisateur(user1);
//
//        recette1 = recetteDaoInterface.createRecette(recette1);
//        recette2 = recetteDaoInterface.createRecette(recette2);
//
//        ingredient1.setRecette(recette1);
//        ingredient2.setRecette(recette2);
//        ingredient3.setRecette(recette2);
//        ingredient4.setRecette(recette1);
//        ingredient5.setRecette(recette1);
//        ingredient6.setRecette(recette1);
////        
////        
//        ingredient1 = ingredientDaoInterface.createIngredient(ingredient1);
//        ingredient2 = ingredientDaoInterface.createIngredient(ingredient2);
//        ingredient3 = ingredientDaoInterface.createIngredient(ingredient3);
//        ingredient4 = ingredientDaoInterface.createIngredient(ingredient4);
//        ingredient5 = ingredientDaoInterface.createIngredient(ingredient5);
//        ingredient6 = ingredientDaoInterface.createIngredient(ingredient6);
////        
//
//        daoFactory.closeEntityManagerFactory();

        try {
            System.out.println("<--- BIENVENUE SUR L'APPLICATION RECETTE SERVICE WEB 1.0 ! --->");

            //Pour charger le fichier jaastp.config
            System.setProperty("java.security.auth.login.config", "recetteserviceweb.config");

            //Création d'une variable de contexte au moment du login
            LoginContext loginContext = new LoginContext("tp", new HandlerCallback());
            loginContext.login();

            //Simuler la possibilité d'effectuer des actions après le login
            //et donner la possibilité de logout
            boolean action = true;
            //Tant que action est vrai (seul le logout la ré initialise à faux et met 
            //fin à l'exécution de l'appli
            while (action) {
                action = effectuerAction(loginContext);
            }
            //Ici, on gère les exceptions (login, input/output
        } catch (LoginException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean effectuerAction(LoginContext context) throws IOException, LoginException {
        System.out.println("Choisir une action à effectuer : Tapez 'action1', 'action2', 'action3' ou 'logout' pour quitter.");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean result = true;

        switch (Action.valueOf(bufferedReader.readLine())) {
            case action1:
                System.out.println("Gotta knock a little harder ! Action 1 effectuée avec succès !");
                break;
            case action2:
                System.out.println("Too good too bad ! Action 2 effectuée avec succès !");
                break;
            case action3:
                System.out.println("You gonna carry that weight... Action 3 effectuée avec succès !");
                break;
            case logout:
                context.logout();
                result = false;
                System.out.println("See ya, space cowboy ! LogOut effectué avec succès;");
                break;
        }

        return result;
    }

}
