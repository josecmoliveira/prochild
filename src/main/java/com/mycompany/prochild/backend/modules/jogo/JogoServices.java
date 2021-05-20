/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.jogo;

import com.mycompany.prochild.backend.models.Jogo;
import java.util.List;

/**
 *
 * @author jcmol
 */
public class JogoServices {
    private JogoRepository jogo = new JogoRepository();
    
    public List<Jogo> findAllJogos() {
        return jogo.findAllJogos();
    }
    
    public int insertJogo(Jogo dir) {
        return jogo.insertJogo(dir);
    }
    
    public int removeJogo(int jogoId) {
        return jogo.removeJogo(jogoId);
    }
}
