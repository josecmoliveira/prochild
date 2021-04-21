/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.controllers;

import com.mycompany.prochild.backend.models.Direito;
import com.mycompany.prochild.backend.modules.direito.DireitoServices;
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


@WebServlet(name = "DireitoController", urlPatterns = {"/DireitoController"})
public class DireitoController extends HttpServlet{
    
    private DireitoServices direitoservice = new DireitoServices();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
              
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");

        String aux_what = (String) request.getParameter("pwhat");  
        
        switch(aux_what){
            case "findAllDireitos":
               findAllDireitos(request, response);
                break;
            case "insertDireito":
                insertDireito(request, response);
                break;
            case "updateDireito":
                updateDescricao(request, response);
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
    
    private void findAllDireitos(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        
        PrintWriter pw = null;
        try {
            object.put("result", "KO");
            
            List<Direito> direitos = direitoservice.findAllDireitos();
            
            for(Direito direito: direitos) {
                array.put(direito.toJSON());
            }
            
            object.put("result", "OK");
            object.put("direito", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(DireitoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void insertDireito(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String assistenteIdString = request.getParameter("assistente_id");
        
        try {
            object.put("result", "KO");
                    
            if(!assistenteIdString.equals("")) {
                Direito direito = new Direito();
                direito.setNome(nome);
                direito.setDescricao(descricao);
                direito.setAssistenteId(Integer.parseInt(assistenteIdString));
                
                int result = direitoservice.insertDireito(direito);
            
                if(result == 1) {
                    object.put("result", "OK");
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(DireitoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void updateDescricao(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String direitoIdString = request.getParameter("direitoId");
        String descricao = request.getParameter("descricao");
        
        try {
            object.put("result", "KO");
                    
            if(!direitoIdString.equals("")) {
                Direito direito = new Direito();
                direito.setDescricao(descricao);
                
                int result = direitoservice.updateDescricao(direito);
            
                if(result == 1) {
                    object.put("result", "OK");
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(DireitoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
}