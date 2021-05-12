/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.chat;

import com.mycompany.prochild.backend.models.Conversa;
import com.mycompany.prochild.backend.models.User;
import com.mycompany.prochild.sql_connection.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ruibraga
 */
public class ConversaRepository {

    public List<Conversa> findAllConversasOfUser(int userId) {
        
        List<Conversa> conversas = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "SELECT conversa.conversaId  as conversaId, data_inicio, data_ultima_mensagem from conversa "
                         + "INNER JOIN conversa_users ON conversa_users.conversa_users_conversaId = conversa.conversaId "
                         + "WHERE conversa_users_userId = ? "
                         + "ORDER BY data_ultima_mensagem DESC;";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, userId);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Conversa conversa = new Conversa();
                
                conversa.setId(rs.getInt("conversaId"));
                conversa.setDataInicio(rs.getTimestamp("data_inicio"));
                conversa.setDataUltimaMensagem(rs.getTimestamp("data_ultima_mensagem"));
                
                conversas.add(conversa);
            }

        } catch (Exception e) {
            
            System.out.println("Erro findAllDireitos " + e.getMessage());
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
        
        return conversas;
        
    }
    
    public User findConversaParticipant(int conversaId, int currentUserId) {
        User participant = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "SELECT userId, username, nome from conversa_users "
                + "INNER JOIN users ON users.userId = conversa_users.conversa_users_userId "
                + "WHERE conversa_users_conversaId = ? and conversa_users.conversa_users_userId != ?;";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, conversaId);
            pstmt.setInt(2, currentUserId);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                participant = new User();
                participant.setUserId(rs.getInt("userId"));
                participant.setUsername(rs.getString("username"));
                participant.setNome(rs.getString("nome"));
            }

        } catch (Exception e) {
            
            System.out.println("Erro findConversaParticipant " + e.getMessage());
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
        
        return participant;
    }
    
    public int insertConversa() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "INSERT INTO conversa (data_inicio) VALUES (CURRENT_TIMESTAMP);";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            result = pstmt.executeUpdate();
            
            if(result > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                
                rs.next();

                result = rs.getInt(1);
            }
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro insertConversa " + e.getMessage());
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
    
    public int insertConversaParticipants(User user, int conversaId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "INSERT INTO conversa_users (conversa_users_userId, conversa_users_conversaId) VALUES (?, ?);";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());
            pstmt.setInt(2, conversaId);
            
            result = pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro insertConversaParticipants " + e.getMessage());
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
    
    public int updateConversaLastMessage() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "UPDATE conversa set data_ultima_mensagem = CURRENT_TIMESTAMP;";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro updateConversaLastMessage " + e.getMessage());
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
