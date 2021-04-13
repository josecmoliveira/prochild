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
public class Video extends AssistenteSocial{
    private int videoId;
    private String nome;
    private String link;
    private String descricao;
    private String disponivel;
    

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        object.put("videoId", getVideoId());
        object.put("nome", getNome());
        object.put("link", getLink());
        object.put("descricao", getDescricao());
        object.put("disponivel", getDisponivel());
        
        return object;
        
    }
}
