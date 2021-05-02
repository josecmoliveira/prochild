/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.models;

import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author ruibraga
 */
public class Mensagem {

    private int id;
    private String conteudo;
    private User user;
    private Date dataEnvio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
    
    public JSONObject toJSON() {
        
        JSONObject object = new JSONObject();
        
        object.put("mensagem_id", getId());
        object.put("conteudo", getConteudo());
        object.put("user", getUser().toJSON());
        object.put("data_envio", getDataEnvio());
        
        return object;
        
    }
}
