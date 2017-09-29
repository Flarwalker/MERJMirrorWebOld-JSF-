/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: JdbcDatabase
 *  Last Edited by: Ryan
 *  Last Edited: 9-19-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.merjmirror.util.config.AppConfig;

/**
 * JDBC Connection Pool helper.
 * @author Ryan
 */
public class JdbcDatabase {
    private static JdbcDatabase singletonRef; // A Reference to the singleton
    
    private DataSource dataSource;
    
    private JdbcDatabase (AppConfig config) throws NamingException {
        InitialContext ic = new InitialContext();
        dataSource = (DataSource) ic.lookup(config.getProperty("jndi.name"));
    }
    
    /**
     * Gets the singleton Reference.
     * @param config App Config properties
     * @return singletonRef
     * @throws NamingException Exception for the Context
     */
    public static synchronized JdbcDatabase getJdbcDatabase (AppConfig config) throws NamingException {
        if (singletonRef == null) {
            singletonRef = new JdbcDatabase(config);
        }
        return singletonRef;
    }
    
    /**
     * Get the Connection to the Database.
     * 
     * @return connection
     * @throws SQLException SQL Exception
     */
    public Connection getConnection () throws SQLException {
        Connection conn;
        
        conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        
        setTimeZone(conn);
        
        return conn;
    }
    
    private void setTimeZone (Connection conn) throws SQLException {
        String qry = "alter session set time_zone='EST'";
        
        Statement stmt = conn.createStatement();
        stmt.execute(qry);
        stmt.close();
    }
    
}
