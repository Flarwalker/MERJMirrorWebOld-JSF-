/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: ApplicationBean
 *  Last Edited by: Ryan
 *  Last Edited: 10-20-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.merjmirror.MerjMirror;
import com.merjmirror.util.config.AppConfig;

/**
 * Class to run the start up of the app.
 * @author Ryan
 */
@ApplicationScoped
@ManagedBean(name = "applicationBean")
public class ApplicationBean {
    private static AppConfig config;
    private static MerjMirror mirror;

    /**
     * Public Constructor.
     */
    public ApplicationBean () {
    }

    /**
     * Returns a copy of the App config object.
     * @return config
     */
    public AppConfig getConfig () {
        return config;
    }

    /**
     * Gets a list of the Config File Settings. 
     * @return List of config settings
     */
    public String getConfigList() {
        return config.getList();
    }

    /**
     * Returns a copy of the Mirror object.
     * @return mirror
     */
    public static MerjMirror getMirror() {
        return mirror;
    }

    /**
     * Returns a key value from the config file.
     * 
     * @param key Value to pull
     * @return value
     */
    public String getProperty (String key) {
        return config.getProperty(key);
    }

    /**
     * Function to run start up on the fist web page.
     */
    public static void onload() {
        config = new AppConfig();
        mirror = new MerjMirror(config);
    }

    /**
     * Sets the config to the pasted config file.
     *
     * @param confi Config file to set
     */
    public static void setConfig (AppConfig confi) {
        config = confi;
    }

    /**
     * Sets the Mirror to the pasted mirror file.
     *
     * @param mirro Mirror settings to set
     */
    public static void setMirror (MerjMirror mirro) {
        mirror = mirro;
    }

}
