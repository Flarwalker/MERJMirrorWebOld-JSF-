/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class:   AppConfig
 *  Last Edited by: Ryan
 *  Last Edited: 10-10-17
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
     * Returns a List of the Config File Keys and Values.
     * @return List of values and keys
     */
    public String getList() {
        return props.toString();
    }

    /**
     * Gets the Value from the Key Value.
     * 
     * @param key Key Value to look up
     * @return value Value associated with the key
     */
    public String getProperty (String key) {
        return props.getProperty(key);
    }

    /**
     * Reload the Config Settings.
     */
    public void reload () {
        LOGGER.log(Level.INFO, "Entering AppConfig.reload");
        String configPath = "C:/Users/Flarwalker/Documents/Codding/git/MERJMirrorWeb/src/resources/config.properties";
    
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

}
