/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.direito;

import com.mycompany.prochild.backend.models.Direito;
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
public class DireitoRepository {

    public List<Direito> findAllDireitos() {
        List<Direito> direitos = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "Select * from direitos";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Direito direito = new Direito();
                
                direito.setAssistenteId(rs.getInt("direitos_assistenteId"));
                direito.setDireitoId(rs.getInt("direitoId"));
                direito.setNome(rs.getString("nome"));
                direito.setDescricao(rs.getString("descricao"));
                direitos.add(direito);
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
        
        return direitos;
    }
    
    public int insertDireito(Direito direito) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "INSERT INTO direitos (nome, descricao, direitos_assistenteId) VALUES (?,?,?);";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, direito.getNome());
            pstmt.setString(2, direito.getDescricao());
            pstmt.setInt(3, direito.getAssistenteId());
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro insertDireito " + e.getMessage());
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
    
    public Direito findDireitoById(int direitoId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Direito direito = null;
        
        final String sql = "Select * from direitos where direitoId = ?";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, direitoId);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                direito = new Direito();
                direito.setDireitoId(rs.getInt("direitoId"));
                direito.setAssistenteId(rs.getInt("direitos_assistenteId"));
                direito.setNome(rs.getString("nome"));
                direito.setDescricao(rs.getString("descricao"));
            }

        } catch (Exception e) {
            
            System.out.println("Erro findDireitoById " + e.getMessage());
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
        
        return direito;
    }
    
    public int updateDescricao(Direito direito) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        int dirId = direito.getDireitoId();
        String sql = "UPDATE direitos SET descricao = ? where direitoId = ?";
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, direito.getDescricao());
            pstmt.setInt(2, dirId);
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro updateDescricao" + e.getMessage());
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
    
    public int removeDireito(int direitoId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "DELETE FROM direitos WHERE direitoId = ?";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, direitoId);
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro removeDireito " + e.getMessage());
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
