/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.models;

import org.json.JSONObject;

/**
 *
 * @author ruibraga
 */
public class User {
    
    private int userId;
    private String username;
    private String password;
    private String nome;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
    public JSONObject toJSON() {
        
        JSONObject object = new JSONObject();
        
        object.put("user_id", getUserId());
        object.put("username", getUsername());
        object.put("password", getPassword());
        object.put("nome", getNome());
        
        return object;
        
    }
}
