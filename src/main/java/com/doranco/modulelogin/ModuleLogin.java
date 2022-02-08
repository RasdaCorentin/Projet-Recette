/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.modulelogin;

import com.doranco.dao.DaoFactory;
import com.doranco.dao.interfaces.UtilisateurDaoInterface;
import com.doranco.entities.Utilisateur;
import com.doranco.principal.RecettePrincipal;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author samha
 */
public class ModuleLogin implements LoginModule {

    private Subject subject;

    private CallbackHandler callbackHandler;

    private RecettePrincipal recettePrincipal;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        
        this.subject = subject;
        
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {

        boolean login = false;

        Callback[] callbacks = new Callback[2];

        callbacks[0] = new NameCallback("Username : ");

        callbacks[1] = new PasswordCallback("Mot de passe : ", false);

        try {

            this.callbackHandler.handle(callbacks);

            String username = ((NameCallback) callbacks[0]).getName();
      
            String password = new String(((PasswordCallback) callbacks[1]).getPassword());

            Utilisateur utilisateur = new Utilisateur(username, password);
            
            DaoFactory daoFactory = new DaoFactory();
            
            UtilisateurDaoInterface utilisateurDaoInterface = daoFactory.getUtilisateurDaoInterface();

            utilisateur = utilisateurDaoInterface.login(utilisateur);

            if (utilisateur != null) {
                recettePrincipal = new RecettePrincipal(utilisateur.getUsername());
                login = true;
            }

        } catch (Exception ex) {
            System.out.println("Erreur " + ex);
        }

        return login;

    }

    @Override
    public boolean commit() throws LoginException {
        
        boolean result = false;

        if (this.subject != null && !this.subject.getPrincipals().contains(this.recettePrincipal)) {

            this.subject.getPrincipals().add(this.recettePrincipal);

            result = true;
        }

        return result;
    }

    @Override
    public boolean abort() throws LoginException {
        
        return false;
        
    }

    @Override
    public boolean logout() throws LoginException {
        
        subject.getPrincipals().remove(recettePrincipal);
        
        subject = null;
        
        return true;
    }

}
