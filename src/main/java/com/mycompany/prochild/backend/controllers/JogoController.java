/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.controllers;

import com.mycompany.prochild.backend.models.Jogo;
import com.mycompany.prochild.backend.modules.jogo.JogoServices;
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


@WebServlet(name = "JogoController", urlPatterns = {"/JogoController"})
public class JogoController extends HttpServlet{
    
    private JogoServices jogoservice = new JogoServices();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
              
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");

        String aux_what = (String) request.getParameter("pwhat");  
        
        switch(aux_what){
            case "findAllJogos":
               findAllJogos(request, response);
                break;
            case "insertJogoo":
                insertJogo(request, response);
                break;
            case "removeJogo":
                removeJogo(request, response);
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
    
    private void findAllJogos(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        
        PrintWriter pw = null;
        try {
            object.put("result", "KO");
            
            List<Jogo> jogos = jogoservice.findAllJogos();
            
            for(Jogo jogo: jogos) {
                array.put(jogo.toJSON());
            }
            
            object.put("result", "OK");
            object.put("jogo", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(JogoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void insertJogo(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String disponivel = request.getParameter("disponivel");
        String assistenteIdString = request.getParameter("assistente_id");
        
        try {
            object.put("result", "KO");
                    
            if(!assistenteIdString.equals("")) {
                Jogo jogo = new Jogo();
                jogo.setNome(nome);
                jogo.setDescricao(descricao);
                jogo.setDisponivel(disponivel);
                jogo.setAssistenteId(Integer.parseInt(assistenteIdString));
                
                int result = jogoservice.insertJogo(jogo);
            
                if(result == 1) {
                    object.put("result", "OK");
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(JogoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void removeJogo(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String jogoId = request.getParameter("jogo_id");
        
        try {
            object.put("result", "KO");
                    
            if(!jogoId.equals("")) {
                
                int result = jogoservice.removeJogo(Integer.parseInt(jogoId));
            
                if(result == 1) {
                    object.put("result", "OK");
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(JogoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
}