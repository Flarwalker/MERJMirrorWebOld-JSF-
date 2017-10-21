/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: JdbcDatabase
 *  Last Edited by: Ryan
 *  Last Edited: 10-20-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.merjmirror.util.config.AppConfig;

/**
 * JDBC Connection Pool helper.
 * @author Ryan
 */
public class JdbcDatabase {
    // A Reference to the singleton
    private static JdbcDatabase singletonRef;

    private DataSource dataSource;

    /**
     * Creates the data Source.
     * 
     * @param config Configuration File
     * @throws NamingException DataBase Name Exception catch
     */
    private JdbcDatabase (AppConfig config) throws NamingException {
        InitialContext ic = new InitialContext();
        dataSource = (DataSource) ic.lookup(config.getProperty("jndi.name"));
    }

    /**
     * Get the Connection to the Database.
     * 
     * @return connection Connection to the Database
     * @throws SQLException SQL Exception catch
     */
    public Connection getConnection () throws SQLException {
        Connection conn;
    
        conn = dataSource.getConnection();
        conn.setAutoCommit(false);
    
        return conn;
    }

    /**
     * Gets the singleton Reference.
     * 
     * @param config App Config properties
     * @return singletonRef Static reference to the database
     * @throws NamingException Exception for the Context
     */
    public static synchronized JdbcDatabase getJdbcDatabase (AppConfig config) throws NamingException {
        if (singletonRef == null) {
            singletonRef = new JdbcDatabase(config);
        }
    
        return singletonRef;
    }

}
