/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.handlercallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 *
 * @author samha
 */
public class HandlerCallback implements CallbackHandler{

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
       
        NameCallback usernameCallback = (NameCallback) callbacks[0];
        
        System.out.println(usernameCallback.getPrompt());
        
        InputStreamReader inputStreamReaderUsername = new InputStreamReader(System.in);
        
        BufferedReader bufferedReaderUsername = new BufferedReader(inputStreamReaderUsername);
        
        usernameCallback.setName(bufferedReaderUsername.readLine());
        
        
        /**
         * Password utilisateur
         */
        PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
        
        System.out.println(passwordCallback.getPrompt());
        
        InputStreamReader inputStreamReaderPassword = new InputStreamReader(System.in);
        
        BufferedReader bufferedReaderPassword = new BufferedReader(inputStreamReaderPassword);
        
        passwordCallback.setPassword(bufferedReaderPassword.readLine().toCharArray());
        
    }
    
}
