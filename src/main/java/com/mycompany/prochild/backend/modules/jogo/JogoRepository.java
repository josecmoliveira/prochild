/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.jogo;

import com.mycompany.prochild.backend.models.Jogo;
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
public class JogoRepository {

    public List<Jogo> findAllJogos() {
        List<Jogo> jogos = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "Select * from jogos";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Jogo jogo = new Jogo();
                
                jogo.setAssistenteId(rs.getInt("jogos_assistenteId"));
                jogo.setJogoId(rs.getInt("jogoId"));
                jogo.setNome(rs.getString("nome"));
                jogo.setDescricao(rs.getString("descricao"));
                jogos.add(jogo);
            }

        } catch (Exception e) {
            
            System.out.println("Erro findAllJogos " + e.getMessage());
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
        
        return jogos;
    }
    
    public int insertJogo(Jogo jogo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        
        String sql = "INSERT INTO jogos (nome, descricao, jogos_assistenteId) VALUES (?,?,?);";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, jogo.getNome());
            pstmt.setString(2, jogo.getDescricao());
            pstmt.setInt(3, jogo.getAssistenteId());
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro insertJogo " + e.getMessage());
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
    
    public int removeJogo(int jogoId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "DELETE FROM jogos WHERE jogoId = ?";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, jogoId);
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro removeJogo " + e.getMessage());
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
