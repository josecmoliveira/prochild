/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.direito;

import com.mycompany.prochild.backend.models.Direitos;
import java.util.List;

/**
 *
 * @author jcmol
 */
public class DireitoServices {
    private DireitoRepository direito = new DireitoRepository();
    
    public List<Direitos> findAllDireitos() {
        return direito.findAllDireitos();
    }
}