
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
public class Noticia extends AssistenteSocial{
    private int noticiaId;
    private String nome;
    private String link;
    private String descricao;

    public int getNoticiaId() {
        return noticiaId;
    }

    public void setNoticiaId(int noticiaId) {
        this.noticiaId = noticiaId;
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
    
    public JSONObject toJSON() {
        
        JSONObject object = new JSONObject();
        
        object.put("assistenteId", getAssistenteId());
        object.put("noticiaId", getNoticiaId());
        object.put("nome", getNome());
        object.put("link", getLink());
        object.put("descricao", getDescricao());
        
        return object;
    }
}
