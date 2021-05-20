/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.noticia;

import com.mycompany.prochild.backend.models.Noticia;
import java.util.List;

/**
 *
 * @author jcmol
 */
public class NoticiaServices {
    private NoticiaRepository noticia = new NoticiaRepository();
    
    public List<Noticia> findAllNoticias(){
        return noticia.findAllNoticias();
    }
    
    public Noticia findNoticiaById(int noticiaId) {
        return noticia.findNoticiaById(noticiaId);
    }
    
    public int insertNoticia(Noticia ntc){
        return noticia.insertNoticia(ntc);
    }
    
    public int updateNoticia(Noticia ntc){
        return noticia.updateNoticia(ntc);
    }
    
    public int removeNoticia(int noticiaId) {
        return noticia.removeNoticia(noticiaId);
    }
}
