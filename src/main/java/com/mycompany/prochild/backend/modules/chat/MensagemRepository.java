/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.chat;

import com.mycompany.prochild.backend.models.Mensagem;
import com.mycompany.prochild.backend.models.User;
import com.mycompany.prochild.sql_connection.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ruibraga
 */
public class MensagemRepository {
    
    public List<Mensagem> findAllMensagensInConversa(int conversaId) {
        List<Mensagem> mensagens = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "SELECT * from mensagens "
                + "INNER JOIN users ON users.userId = mensagens.mensagens_userId "
                + "WHERE mensagens.mensagens_conversaId = ? "
                + "ORDER BY mensagemId;";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, conversaId);
            
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setId(rs.getInt("mensagemId"));
                mensagem.setConteudo(rs.getString("conteudo"));
                mensagem.setDataEnvio(rs.getTimestamp("data_envio"));
                
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setNome(rs.getString("nome"));
                
                mensagem.setUser(user);
                
                mensagens.add(mensagem);
            }

        } catch (Exception e) {
            
            System.out.println("Erro findAllMensagensInConversa " + e.getMessage());
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
        
        return mensagens;
    }
    
    
}
