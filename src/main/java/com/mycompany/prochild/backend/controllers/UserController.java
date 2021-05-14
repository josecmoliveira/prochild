/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.controllers;

import com.mycompany.prochild.backend.models.AssistenteSocial;
import com.mycompany.prochild.backend.models.Cliente;
import com.mycompany.prochild.backend.models.User;
import com.mycompany.prochild.backend.modules.user.UserServices;
import com.mycompany.prochild.backend.modules.assistentesocial.AssistenteSocialServices;
import com.mycompany.prochild.backend.modules.cliente.ClienteServices;
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
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet{
    
    private UserServices userservice = new UserServices();
    private AssistenteSocialServices assistenteservice = new AssistenteSocialServices();
    private ClienteServices clienteservice = new ClienteServices();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
              
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");

        String aux_what = (String) request.getParameter("pwhat");  
        
        switch(aux_what){
            case "findAllUsers":
                findAllUsers(request, response);
                break;
            case "insertUser":
                insertUser(request, response);
                break;
            case "updateUser":
                updateUser(request, response);
                break;
            case "userLogin":
                userLogin(request, response);
                break;
            case "userLoginC":
                userLoginC(request, response);
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
    
    private void findAllUsers(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        
        PrintWriter pw = null;
        try {
            object.put("result", "KO");
            
            List<User> users = userservice.findAllUsers();
            
            for(User user: users) {
                array.put(user.toJSON());
            }
            
            object.put("result", "OK");
            object.put("users", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String userId = request.getParameter("userId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            object.put("result", "KO");
                    
            if(!userId.equals("")) {
                User user = userservice.findUserById(Integer.parseInt(userId));
                
                if(user != null) {
                    user.setUsername(username);
                    user.setPassword(password);
                    
                    int result = userservice.updateUser(user);
            
                    if(result == 1) {
                        object.put("result", "OK");
                    }
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void insertUser(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            object.put("result", "KO");
                    
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);              
                
            int result = userservice.insertUser(user);
            
            if(result == 1) {
                object.put("result", "OK");
            }
                        
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void userLogin(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        PrintWriter pw = null;
        try {           
            
            User user = userservice.findUserByName(username);
            boolean isValid = user.getPassword().equals(password);   
            
                                      
            object.put("result", isValid);           
            if (isValid){
                object.put("userId",user.getUserId());
                AssistenteSocial assistente = assistenteservice.findAssistenteById(user.getUserId());
                object.put("isAssistenteSocial",assistente != null);
                
            }
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void userLoginC(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        PrintWriter pw = null;
        try {           
            
            User user = userservice.findUserByName(username);
            boolean isValid = user.getPassword().equals(password);   
            
                                      
            object.put("result", isValid);           
            if (isValid){
                object.put("userId",user.getUserId());
                Cliente cliente = clienteservice.findClienteById(user.getUserId());
                object.put("isCliente",cliente != null);
                
            }
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
}
