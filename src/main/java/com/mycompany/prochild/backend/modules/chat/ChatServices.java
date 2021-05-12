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
    
    public String startConversa(int currentUserId, int otherUserId) {
        String result = "KO";
        
        try {
            User currentUser = userServices.findUserById(currentUserId);
            User otherUser = userServices.findUserById(otherUserId);
            
            //Insert conversa
            int conversaIdInserted = conversaRepository.insertConversa();
            
            if(conversaIdInserted <= 0) {
                return result; 
            }
            
            //Insert participante utilizador atual
            int resultParticipant_1 = conversaRepository.insertConversaParticipants(currentUser, conversaIdInserted);
            
            if(resultParticipant_1 <= 0) { 
                return result; 
            }
            
            //Insert participante outro utilizador
            int resultParticipant_2 = conversaRepository.insertConversaParticipants(otherUser, conversaIdInserted);
            
            if(resultParticipant_2 <= 0) { 
             return result; 
            }
            
            result = "OK";
                    
        } catch(Exception e) {
            System.out.println("ConversaServices.startConversa " + e);
            e.printStackTrace();
        }
        
        return result;
    }
    
    public Mensagem sendMensagem(int userId, String conteudo, int conversaId) {
        Mensagem mensagem = new Mensagem();
        
        try {
            User user = userServices.findUserById(userId);
            
            mensagem.setConteudo(conteudo);
            mensagem.setUser(user);
            
            //Insert mensagem
            int mensagemIdInserted = mensagemRepository.insertMensagem(mensagem, conversaId);
            
            if(mensagemIdInserted > 0) {
                mensagem.setId(mensagemIdInserted); 
                conversaRepository.updateConversaLastMessage();
            }
                
        } catch(Exception e) {
            System.out.println("ConversaServices.sendMensagem " + e);
            e.printStackTrace();
        }
        
        return mensagem;
    }
}
