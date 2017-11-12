/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class:   AppConfig
 *  Last Edited by: Ryan
 *  Last Edited: 10-20-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.util.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to load and hold the preferences for the Web APP.
 * @author Ryan
 */
public class AppConfig implements Serializable {
    private static final long serialVersionUID = 6L;

    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());

    private ArrayList<String> keys;

    private Properties props;

    /**
     * Default Constructor.
     */
    public AppConfig () {
        reload();
    }

    /**
     * Returns a List of the Config File Keys and Values.
     * @return list
     */
    public String getList() {
        String list = "";
    
        for (String key : keys) {
            list = list + key + " : " + getProperty(key) + "\n";
        }
    
        return list;
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

    /**
     * Reload the Config Settings.
     */
    @SuppressWarnings ("unchecked")
    public void reload () {
        LOGGER.log(Level.INFO, "Entering AppConfig.reload");
        String configPath = "C:/Users/rfcro/Documents/Codding/git/MERJMirrorWeb/src/resources/config.properties";
    
        try {
            File appFile = new File(configPath);
            FileInputStream in = new FileInputStream(appFile);
            props = new Properties();
        
            props.load(in);
            in.close();
         
            keys = (ArrayList<String>) Collections.list(props.propertyNames());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
