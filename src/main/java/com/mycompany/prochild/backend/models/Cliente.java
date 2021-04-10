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
public class Cliente extends User {
    
    private int clienteId;
    private String nome;
    private String email;
    private String genero;
    private String tipo;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public JSONObject toJSON() {
        
        JSONObject object = new JSONObject();
        
        object.put("user_id", getUserId());
        object.put("clienteId", getClienteId());
        object.put("nome", getNome());
        object.put("email", getEmail());
        object.put("genero", getGenero());
        object.put("tipo", getTipo());
        
        return object;
        
    }
}
