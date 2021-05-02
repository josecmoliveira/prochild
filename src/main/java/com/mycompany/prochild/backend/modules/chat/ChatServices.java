/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.chat;

import com.mycompany.prochild.backend.models.Conversa;
import com.mycompany.prochild.backend.models.Mensagem;
import com.mycompany.prochild.backend.models.User;
import com.mycompany.prochild.backend.modules.user.UserServices;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ruibraga
 */
public class ChatServices {
    
    private UserServices userServices = new UserServices();
    private ConversaRepository conversaRepository = new ConversaRepository();
    private MensagemRepository mensagemRepository = new MensagemRepository();
    
    public List<Conversa> findAllConversasAndParticipants(int userId) {
        List<Conversa> conversas = new ArrayList();
        
        try {
            User currentUser = userServices.findUserById(userId);

            conversas = conversaRepository.findAllConversasOfUser(userId);

            for(Conversa conversa : conversas) {
                User participant = conversaRepository.findConversaParticipant(conversa.getId(), userId);
                
                conversa.setCurrentUser(currentUser);
                conversa.setOtherUser(participant);
            }
        } catch(Exception e) {
            System.out.println("ConversaServices.findAllConversasAndParticipants " + e);
            e.printStackTrace();
        }
        
        return conversas;
    }
    
    public List<Mensagem> findAllMensagensInConversa(int conversaId) {
        List<Mensagem> mensagens = new ArrayList();
        
        try {
            mensagens = mensagemRepository.findAllMensagensInConversa(conversaId);
        } catch(Exception e) {
            System.out.println("ConversaServices.findAllMensagensInConversa " + e);
            e.printStackTrace();
        }
        return mensagens;
    }
    
}
