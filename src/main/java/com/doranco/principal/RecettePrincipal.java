/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doranco.principal;

import java.security.Principal;

/**
 *
 * @author samha
 */
public class RecettePrincipal implements Principal {

    private String username;

    public RecettePrincipal(String username) {
        this.username = username;
    }
    
   
    @Override
    public String getName() {
        return username;
    }
    
}
