/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.controllers;

import com.mycompany.prochild.backend.models.AssistenteSocial;
import com.mycompany.prochild.backend.models.User;
import com.mycompany.prochild.backend.modules.assistentesocial.AssistenteSocialServices;
import com.mycompany.prochild.backend.modules.user.UserServices;
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
 * @author jcmol
 */
@WebServlet(name = "AssistenteSocialController", urlPatterns = {"/AssistenteSocialController"})
public class AssistenteSocialController extends HttpServlet{
    
    private AssistenteSocialServices assistenteservice = new AssistenteSocialServices();
    private UserServices userservice = new UserServices();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
              
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");

        String aux_what = (String) request.getParameter("pwhat");  
        
        switch(aux_what){
            case "findAllAssistentes":
               findAllAssistentes(request, response);
                break;
            case "insertAssistente":
                insertAssistente(request, response);
                break;
            case "updateAssistente":
                updateAssistente(request, response);
                break; 
            case "findAssistenteById":
                findAssistenteById(request, response);
                break; 
            case "registarAssistente":
                registarAssistente(request, response);
                break;            
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
    
    private void findAllAssistentes(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        
        PrintWriter pw = null;
        try {
            object.put("result", "KO");
            
            List<AssistenteSocial> assistentes = assistenteservice.findAllAssistentes();
            
            for(AssistenteSocial assistente: assistentes) {
                array.put(assistente.toJSON());
            }
            
            object.put("result", "OK");
            object.put("assistente", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(AssistenteSocialController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void insertAssistente(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String nifString = request.getParameter("nif");
        String userIdString = request.getParameter("assistentesocial_userId");
        
        try {
            object.put("result", "KO");
                    
            if(!userIdString.equals("")) {
                AssistenteSocial assistente = new AssistenteSocial();
                assistente.setNome(nome);
                assistente.setEmail(email);
                assistente.setNif(Integer.parseInt(nifString));
                assistente.setUserId(Integer.parseInt(userIdString));
                
                int result = assistenteservice.insertAssistente(assistente);
            
                if(result == 1) {
                    object.put("result", "OK");
                }
            }
                        
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(AssistenteSocialController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void updateAssistente(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String assistenteId = request.getParameter("assistenteId");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String nifString = request.getParameter("nif");        
        
        try {
            object.put("result", "KO");
                    
            if(!assistenteId.equals("")) {
                AssistenteSocial assistente = assistenteservice.findAssistenteById(Integer.parseInt(assistenteId));
                
                if(assistente != null) {
                    assistente.setNome(nome);
                    assistente.setEmail(email);
                    assistente.setNif(Integer.parseInt(nifString));
                    
                    int result = assistenteservice.updateAssistente(assistente);
            
                    if(result == 1) {
                        object.put("result", "OK");
                    }
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(AssistenteSocialController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void findAssistenteById(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        String assistentesocial_userId = request.getParameter("assistentesocial_userId");
        
        PrintWriter pw = null;
        try {
            object.put("result", "KO");
            
           AssistenteSocial assistente = assistenteservice.findAssistenteById(Integer.parseInt(assistentesocial_userId));
            
                array.put(assistente.toJSON());
            
            object.put("result", "OK");
            object.put("assistente", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(AssistenteSocialController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void registarAssistente(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String nifString = request.getParameter("nif");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
                    
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);              
            user.setNome(nome);
            
            int result = userservice.insertUser(user);            
            if(!nome.equals("") && result>-1) {
                AssistenteSocial assistente = new AssistenteSocial();
                assistente.setNome(nome);
                assistente.setEmail(email);
                assistente.setNif(Integer.parseInt(nifString));
                assistente.setUserId(result);
                
                int result2 = assistenteservice.insertAssistente(assistente);
                object.put("result", result2 == 1);                   
            }else{
                object.put("result", false);
            }
                        
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(AssistenteSocialController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
}