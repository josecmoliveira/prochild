/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.user;

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
public class UserRepository {
    
    public List<User> findAllUsers() {
        List<User> users = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "Select * from users";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                
                users.add(user);
            }

        } catch (Exception e) {
            
            System.out.println("Erro findAllUsers " + e.getMessage());
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
        
        return users;
    }
    
}
