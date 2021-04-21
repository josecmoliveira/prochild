/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.assistentesocial;

import com.mycompany.prochild.backend.models.AssistenteSocial;
import java.util.List;

/**
 *
 * @author jcmol
 */
public class AssistenteSocialServices {
    private AssistenteSocialRepository assistente = new AssistenteSocialRepository();
    
    public List<AssistenteSocial> findAllAssistentes() {
        return assistente.findAllAssistentes();
    }
    
    public int insertAssistente(AssistenteSocial assist) {
        return assistente.insertAssistente(assist);
    }
    
    public AssistenteSocial findAssistenteById(int assistenteId) {
        return assistente.findAssistenteById(assistenteId);
    }
    
    public int updateAssistente(AssistenteSocial ass) {
        return assistente.updateAssistente(ass);
    }
}
