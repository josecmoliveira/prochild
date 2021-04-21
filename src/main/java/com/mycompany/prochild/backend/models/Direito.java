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
public class Direito extends AssistenteSocial{
    private int direitoId;
    private String nome;
    private String descricao;
    

    public int getDireitoId() {
        return direitoId;
    }

    public void setDireitoId(int direitoId) {
        this.direitoId = direitoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public JSONObject toJSON() {
        
        JSONObject object = new JSONObject();
        
        object.put("assistenteId", getAssistenteId());
        object.put("direitoId", getDireitoId());
        object.put("nome", getNome());
        object.put("descricao", getDescricao());
        
        return object;
        
    }
}
