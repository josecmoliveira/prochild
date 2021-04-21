/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.assistentesocial;

import com.mycompany.prochild.backend.models.AssistenteSocial;
import com.mycompany.prochild.sql_connection.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcmol
 */
public class AssistenteSocialRepository {

    public List<AssistenteSocial> findAllAssistentes() {
        List<AssistenteSocial> assistentes = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "Select * from assistentesocial";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AssistenteSocial assistentesocial = new AssistenteSocial();
                
                assistentesocial.setUserId(rs.getInt("assistentesocial_userId"));
                assistentesocial.setAssistenteId(rs.getInt("assistenteId"));
                assistentesocial.setNome(rs.getString("nome"));
                assistentesocial.setEmail(rs.getString("email"));
                assistentesocial.setNif(rs.getInt("nif"));
                assistentes.add(assistentesocial);
            }

        } catch (Exception e) {
            
            System.out.println("Erro findAllAssistentes " + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }

        }
        
        return assistentes;
    }
    
    public int insertAssistente(AssistenteSocial assistente) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "INSERT INTO assistentesocial (nome, email, nif, assistentesocial_userId) VALUES (?,?,?,?)";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, assistente.getNome());
            pstmt.setString(2, assistente.getEmail());
            pstmt.setInt(3, assistente.getNif());
            pstmt.setInt(4, assistente.getUserId());
            
            result = pstmt.executeUpdate();

        } catch (Exception e) {
            result = -1;
            System.out.println("Erro insertAssistente " + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }

        }
        
        return result;
    }
}
