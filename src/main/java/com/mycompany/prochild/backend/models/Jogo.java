/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.models;

import org.json.JSONObject;

/**
 *
 * @author Raquel
 */
public class Jogo extends AssistenteSocial{
    private int jogoId;
    private String nome;
    private String descricao;
    private String disponivel;
    

    public int getJogoId() {
        return jogoId;
    }

    public void setJogoId(int jogoId) {
        this.jogoId = jogoId;
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
    
    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public JSONObject toJSON() {
        
        JSONObject object = new JSONObject();
        
        object.put("assistenteId", getAssistenteId());
        object.put("videoId", getJogoId());
        object.put("nome", getNome());
        object.put("descricao", getDescricao());
        object.put("disponivel", getDisponivel());
        
        return object;
        
    }
}
