/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.video;

import com.mycompany.prochild.backend.models.Video;
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
public class VideoRepository {

    public List<Video> findAllVideos() {
        List<Video> videos = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "Select * from videos";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Video video = new Video();
                
                video.setAssistenteId(rs.getInt("videos_assistenteId"));
                video.setVideoId(rs.getInt("videoId"));
                video.setNome(rs.getString("nome"));
                video.setLink(rs.getString("link"));
                video.setDescricao(rs.getString("descricao"));
                videos.add(video);
            }

        } catch (Exception e) {
            
            System.out.println("Erro findAllVideos " + e.getMessage());
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
        
        return videos;
    }
    
    public Video findVideoById(int videoId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Video video = null;
        
        final String sql = "Select * from videos where videoId = ?";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, videoId);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                video = new Video();
                video.setAssistenteId(rs.getInt("videos_assistenteId"));
                video.setVideoId(rs.getInt("videoId"));
                video.setNome(rs.getString("nome"));
                video.setLink(rs.getString("link"));
                video.setDescricao(rs.getString("descricao"));
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
        
        return video;
    }
    
    public int insertVideo(Video video) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        
        String sql = "INSERT INTO videos (nome, descricao, link, videos_assistenteId) VALUES (?,?,?);";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, video.getNome());
            pstmt.setString(2, video.getDescricao());
            pstmt.setString(3, video.getLink());
            pstmt.setInt(4, video.getAssistenteId());
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro insertVideo " + e.getMessage());
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
    
    public int updateVideo(Video video) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "UPDATE videos SET nome = ?, descricao = ?, link = ? "
                   + " WHERE videoId = ?";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, video.getNome());
            pstmt.setString(2, video.getDescricao());
            pstmt.setString(3, video.getLink());
            pstmt.setInt(4, video.getVideoId());
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            result = -1;
            System.out.println("Erro updateVideo " + e.getMessage());
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
