/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.models;

import org.json.JSONObject;

/**
 *
 * @author jcmol
 */
public class AssistenteSocial extends User{
    private int assistenteId;

    public int getAssistenteId() {
        return assistenteId;
    }

    public void setAssistenteId(int assistenteId) {
        this.assistenteId = assistenteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }
    private String nome;
    private String email;
    private int nif;
    
    public JSONObject toJSON() {
        
        JSONObject object = new JSONObject();
        
        object.put("user_id", getUserId());
        object.put("assistenteId", getAssistenteId());
        object.put("nome", getNome());
        object.put("email", getEmail());
        object.put("nif", getNif());
        
        return object;
        
    }
}
