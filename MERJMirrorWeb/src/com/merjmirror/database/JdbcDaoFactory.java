/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: JdbcDaoFactory
 *  Last Edited by: Ryan
 *  Last Edited: 9-19-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.database;

import java.io.Serializable;

import com.merjmirror.database.interfaces.IMerjDao;
import com.merjmirror.util.config.AppConfig;

/**
 * This class represents the JDBC implementation of a DaoFactory.
 * @author Ryan
 */
public class JdbcDaoFactory implements Serializable {
    private static final long serialVersionUID = 2L;
    
    private AppConfig config;
    
    /**
     * Performs initialization.
     * 
     * @param config App Config Settings
     * @throws Exception Catch all
     */
    public JdbcDaoFactory (AppConfig config) throws Exception {
        this.config = config;
        JdbcDatabase.getJdbcDatabase(this.config);
    }
    
    /**
     * Gets the workflow Dao interface.
     * @return JdbcMerjDao
     * @throws Exception Catch All
     */
    public IMerjDao getMerjDao () throws Exception {
        return new JdbcMerjDao(this.config);
    }
    
}
