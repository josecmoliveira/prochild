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
public class Conversa {
    
    private int id;
    private User currentUser; //se for preciso
    private User otherUser;
    
    private Date dataInicio;
    private Date dataUltimaMensagem;

    public Conversa() {
        id = 0;
        currentUser = new User();
        otherUser = new User();
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataUltimaMensagem() {
        return dataUltimaMensagem;
    }

    public void setDataUltimaMensagem(Date dataUltimaMensagem) {
        this.dataUltimaMensagem = dataUltimaMensagem;
    }


    public JSONObject toJSON() {
        
        JSONObject object = new JSONObject();
        
        object.put("conversa_id", getId());
        object.put("current_user", getCurrentUser().toJSON());
        object.put("other_user", getOtherUser().toJSON());
        object.put("data_inicio", getDataInicio());
        object.put("data_ultima_mensagem", getDataUltimaMensagem() == null ? "" : getDataUltimaMensagem());
        
        return object;
        
    }
    
    
}
