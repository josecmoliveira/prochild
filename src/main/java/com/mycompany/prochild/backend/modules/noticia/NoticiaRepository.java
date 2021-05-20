/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.noticia;

import com.mycompany.prochild.backend.models.Noticia;
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
public class NoticiaRepository {
    public List<Noticia> findAllNoticias() {
        List<Noticia> noticias = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "Select * from noticias";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Noticia noticia = new Noticia();
                
                noticia.setAssistenteId(rs.getInt("noticias_assistenteId"));
                noticia.setNoticiaId(rs.getInt("noticiaId"));
                noticia.setNome(rs.getString("nome"));
                noticia.setLink(rs.getString("link"));
                noticia.setDescricao(rs.getString("descricao"));
                noticias.add(noticia);
            }

        } catch (Exception e) {
            
            System.out.println("Erro findAllNoticias " + e.getMessage());
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
        
        return noticias;
    }
    
    public Noticia findNoticiaById(int noticiaId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Noticia noticia = null;
        
        final String sql = "Select * from noticias where noticiaId = ?";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noticiaId);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                noticia = new Noticia();
                noticia.setAssistenteId(rs.getInt("noticias_assistenteId"));
                noticia.setNoticiaId(rs.getInt("noticiaId"));
                noticia.setNome(rs.getString("nome"));
                noticia.setLink(rs.getString("link"));
                noticia.setDescricao(rs.getString("descricao"));
            }

        } catch (Exception e) {
            
            System.out.println("Erro findVideoById " + e.getMessage());
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
        
        return noticia;
    }
    
    public int insertNoticia(Noticia noticia) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        
        String sql = "INSERT INTO noticias (nome, link, descricao, noticias_assistenteId) VALUES (?,?,?,?);";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, noticia.getNome());
            pstmt.setString(2, noticia.getLink());
            pstmt.setString(3, noticia.getDescricao());            
            pstmt.setInt(4, noticia.getAssistenteId());
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro insertNoticia " + e.getMessage());
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
    
    public int updateNoticia(Noticia noticia) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "UPDATE noticias SET nome = ?, link = ?, descricao = ? "
                   + " WHERE noticiaId = ?";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, noticia.getNome());
            pstmt.setString(2, noticia.getLink());
            pstmt.setString(3, noticia.getDescricao());
            pstmt.setInt(4, noticia.getNoticiaId());
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro updateNoticia " + e.getMessage());
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
    
    public int removeNoticia(int noticiaId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "DELETE FROM noticias WHERE noticiaId = ?";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, noticiaId);
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro removeNoticia " + e.getMessage());
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
