/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.sql_connection;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ruibraga
 */
public class DataBaseConnection {

    private DataBaseConnection() {
    }

    private static DataSource dataSource;

    static {
        try {
            System.out.println("INIT DB SOURCE");
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/prochild");
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static Set<String> resultSetColumns(ResultSetMetaData meta) throws SQLException {
        Set<String> columns = new HashSet<>(); 
        
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            String tableName = meta.getTableName(i);
            String columnName = meta.getColumnName(i);
            String allias = meta.getColumnLabel(i);
            columns.add(tableName.concat(".").concat(columnName));
            columns.add(allias);
        }
        
        return columns;
    }
}
