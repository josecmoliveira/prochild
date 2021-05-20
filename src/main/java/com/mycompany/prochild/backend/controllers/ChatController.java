/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.controllers;

import com.mycompany.prochild.backend.models.Conversa;
import com.mycompany.prochild.backend.models.Mensagem;
import com.mycompany.prochild.backend.modules.chat.ChatServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ruibraga
 */
@WebServlet(name = "ChatController", urlPatterns = {"/ChatController"})
public class ChatController extends HttpServlet{
    
    private ChatServices chatServices = new ChatServices();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
              
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");

        String aux_what = (String) request.getParameter("pwhat");  
        
        switch(aux_what){
            case "findAllConversasForUser":
               findAllConversasForUser(request, response);
                break;
            case "findAllMensagensInConversa":
               findAllMensagensInConversa(request, response);
                break;
            case "startConversa":
                startConversa(request, response);
                break;
            case "sendMensagem":
                sendMessagem(request, response);
        }        
    }
    
    
    private void findAllConversasForUser(HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        PrintWriter pw = null;
        
        String userIdString = request.getParameter("userId");
        
        try {
           int userId = Integer.parseInt(userIdString);
            
            object.put("result", "KO");
            
            List<Conversa> conversas = chatServices.findAllConversasAndParticipants(userId);
            
            for(Conversa conversa: conversas) {
                array.put(conversa.toJSON());
            }
            
            object.put("result", "OK");
            object.put("conversas", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            System.out.println("ChatController.findAllConversasForUser " + ex);
            ex.printStackTrace();
        } finally {
            pw.close();
        }
    }
    
    private void findAllMensagensInConversa(HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        PrintWriter pw = null;
        
        String conversIdString = request.getParameter("conversaId");
        
        try {
           int conversaId = Integer.parseInt(conversIdString);
            
            object.put("result", "KO");
            
            List<Mensagem> mensagens = chatServices.findAllMensagensInConversa(conversaId);
            
            for(Mensagem mensagem: mensagens) {
                array.put(mensagem.toJSON());
            }
            
            object.put("result", "OK");
            object.put("conversas", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            System.out.println("ChatController.findAllConversasForUser " + ex);
            ex.printStackTrace();
        } finally {
            pw.close();
        }
    }
    
    private void startConversa(HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String currentUserIdString = request.getParameter("current_userId");
        String otherUserIdString = request.getParameter("other_userId");
        
        try {
            int currentUserId = Integer.parseInt(currentUserIdString);
            int otherUserId = Integer.parseInt(otherUserIdString);
            
            object.put("result", chatServices.startConversa(currentUserId, otherUserId));
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            System.out.println("ChatController.findAllConversasForUser " + ex);
            ex.printStackTrace();
        } finally {
            pw.close();
        }
    }
    
    private void sendMessagem(HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String userIdString = request.getParameter("userId");
        String conversaIdString = request.getParameter("conversaId");
        String message = request.getParameter("message");
        
        try {
            int userId = Integer.parseInt(userIdString);
            int conversaId = Integer.parseInt(conversaIdString);
            
            object.put("result", "KO");
            
            Mensagem mensagem = chatServices.sendMensagem(userId, message, conversaId);
            
            if(mensagem.getId() > 0) {
                object.put("result", "OK");
                object.put("mensagem", mensagem.toJSON());
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            System.out.println("ChatController.sendMessage " + ex);
            ex.printStackTrace();
        } finally {
            pw.close();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
}
