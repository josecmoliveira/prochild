/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.cliente;

import com.mycompany.prochild.backend.models.Cliente;
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
public class ClienteRepository {
    
    public List<Cliente> findAllClientes() {
        List<Cliente> clientes = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        final String sql = "Select * from clientes";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setUserId(rs.getInt("clientes_userId"));
                cliente.setClienteId(rs.getInt("clienteId"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setTipo(rs.getString("tipo"));
                clientes.add(cliente);
            }

        } catch (Exception e) {
            
            System.out.println("Erro findAllClients " + e.getMessage());
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
        
        return clientes;
    }
    
    public int insertCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        String sql = "INSERT INTO clientes (nome, email, genero, tipo, clientes_userId) VALUES (?,?,?,?,?)";
        
        try {
            conn = DataBaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getGenero());
            pstmt.setString(4, cliente.getTipo());
            pstmt.setInt(5, cliente.getUserId());
            
            result = pstmt.executeUpdate();

        } catch (Exception e) {
            result = -1;
            System.out.println("Erro insertCliente " + e.getMessage());
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
