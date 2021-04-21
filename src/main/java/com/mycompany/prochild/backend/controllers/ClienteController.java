/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.controllers;

import com.mycompany.prochild.backend.models.Cliente;
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
 * @author jcmol
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet{
    
    private ClienteServices clienteservice = new ClienteServices();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
              
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");

        String aux_what = (String) request.getParameter("pwhat");  
        
        switch(aux_what){
            case "findAllClientes":
               findAllClientes(request, response);
                break;
            case "insertCliente":
                insertCliente(request, response);
                break;
            case "updateCliente":
                updateCliente(request, response);
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
    
    private void findAllClientes(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        
        PrintWriter pw = null;
        try {
            object.put("result", "KO");
            
            List<Cliente> clientes = clienteservice.findAllClientes();
            
            for(Cliente cliente: clientes) {
                array.put(cliente.toJSON());
            }
            
            object.put("result", "OK");
            object.put("cliente", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void insertCliente(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String genero = request.getParameter("genero");
        String tipo = request.getParameter("tipo");
        String userIdString = request.getParameter("clientes_userId");
        
        try {
            object.put("result", "KO");
                    
            if(!userIdString.equals("")) {
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setGenero(genero);
                cliente.setTipo(tipo);
                cliente.setUserId(Integer.parseInt(userIdString));
                
                int result = clienteservice.insertCliente(cliente);
            
                if(result == 1) {
                    object.put("result", "OK");
                }
            }
                        
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void updateCliente(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String clienteId = request.getParameter("assistenteId");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
                
        try {
            object.put("result", "KO");
                    
            if(!clienteId.equals("")) {
                Cliente cliente = clienteservice.findClienteById(Integer.parseInt(clienteId));
                
                if(cliente != null) {
                    cliente.setNome(nome);
                    cliente.setEmail(email);
                    
                    int result = clienteservice.updateCliente(cliente);
            
                    if(result == 1) {
                        object.put("result", "OK");
                    }
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
}