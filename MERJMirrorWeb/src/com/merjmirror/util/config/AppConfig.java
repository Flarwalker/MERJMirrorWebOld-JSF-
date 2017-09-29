/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class:   AppConfig
 *  Last Edited by: Ryan
 *  Last Edited: 9-19-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.util.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to load and hold the preferences for the Web APP.
 * @author Ryan
 */
public class AppConfig {
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());
    private Properties props;
    
    /**
     * Default Constructor.
     */
    public AppConfig () {
        reload();
    }
    
    /**
     * Reload the Config Settings.
     */
    public void reload () {
        LOGGER.log(Level.INFO, "Entering AppConfig.reload");
        String configPath = "src/resources/config/config.properties";
        
        try {
            File appFile = new File(configPath);
            FileInputStream in = new FileInputStream(appFile);
            props = new Properties();
            props.load(in);
            in.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    /**
     * Gets the Value from the Key Value.
     * 
     * @param key Key Value to look up
     * @return value
     */
    public String getProperty (String key) {
        return props.getProperty(key);
    }
    
}
