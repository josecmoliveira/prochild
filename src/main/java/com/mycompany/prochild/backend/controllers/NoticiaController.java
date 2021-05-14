/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.controllers;

import com.mycompany.prochild.backend.models.Noticia;
import com.mycompany.prochild.backend.modules.noticia.NoticiaServices;
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
@WebServlet(name = "NoticiaController", urlPatterns = {"/NoticiaController"})
public class NoticiaController extends HttpServlet{
    
    private NoticiaServices noticiaservice = new NoticiaServices();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
              
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");

        String aux_what = (String) request.getParameter("pwhat");  
        
        switch(aux_what){
            case "findAllNoticias":
               findAllNoticias(request, response);
                break;
            case "insertNoticia":
                insertNoticia(request, response);
                break;
            case "updateNoticia":
                updateNoticia(request, response);
                break;
            case "findNoticiaById":
                findNoticiaById(request, response);
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
    
    private void findAllNoticias(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        
        PrintWriter pw = null;
        try {
            object.put("result", "KO");
            
            List<Noticia> noticias = noticiaservice.findAllNoticias();
            
            for(Noticia noticia: noticias) {
                array.put(noticia.toJSON());
            }
            
            object.put("result", "OK");
            object.put("noticia", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(NoticiaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    private void insertNoticia(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String nome = request.getParameter("nome");
        String link = request.getParameter("link");
        String descricao = request.getParameter("descricao");
        String assistenteIdString = request.getParameter("assistente_id");
        
        try {
            object.put("result", "KO");
                    
            if(!assistenteIdString.equals("")) {
                Noticia noticia = new Noticia();
                noticia.setNome(nome);
                noticia.setLink(link);
                noticia.setDescricao(descricao);
                noticia.setAssistenteId(Integer.parseInt(assistenteIdString));
                
                int result = noticiaservice.insertNoticia(noticia);
            
                if(result == 1) {
                    object.put("result", "OK");
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(NoticiaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    
    private void updateNoticia(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        PrintWriter pw = null;
        
        String noticiaId = request.getParameter("noticia_id");
        String nome = request.getParameter("nome");
        String link = request.getParameter("link");
        String descricao = request.getParameter("descricao");
        String disponivel = request.getParameter("disponivel");
        
        
        try {
            object.put("result", "KO");
                    
            if(!noticiaId.equals("")) {
                Noticia noticia = noticiaservice.findNoticiaById(Integer.parseInt(noticiaId));
                
                if(noticia != null) {
                    noticia.setNome(nome);
                    noticia.setLink(link);
                    noticia.setDescricao(descricao);
                    noticia.setDisponivel(disponivel);
                    
                    int result = noticiaservice.updateNoticia(noticia);
            
                    if(result == 1) {
                        object.put("result", "OK");
                    }
                }
            }
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(NoticiaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
     private void findNoticiaById(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        String noticiaId = request.getParameter("noticiaId");
        
        PrintWriter pw = null;
        try {
            object.put("result", "KO");
            
           Noticia noticia = noticiaservice.findNoticiaById(Integer.parseInt(noticiaId));
            
                array.put(noticia.toJSON());
            
            object.put("result", "OK");
            object.put("noticia", array);
            
            pw = response.getWriter();
            pw.write(object.toString());
        } catch (IOException ex) {
            Logger.getLogger(NoticiaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
   

}