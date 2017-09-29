/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: ApplicationBean
 *  Last Edited by: Ryan
 *  Last Edited: 9-27-17
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
    private AppConfig config;
    private MerjMirror mirror;
    
    /**
     * Public Constructor.
     */
    public ApplicationBean () {
        
    }

    /**
     * Function to run start up on the fist web page.
     */
    public void onload() {
        config = new AppConfig();
        mirror = new MerjMirror(config);
    }

    /**
     * @return config.
     */
    public AppConfig getConfig () {
        return config;
    }

    /**
     * @return mirror.
     */
    public MerjMirror getMirror() {
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
     * Sets the config to the pasted config file.
     *
     * @param config Config file to set
     */
    public void setConfig (AppConfig config) {
        this.config = config;
    }

    /**
     * Sets the Mirror to the pasted mirror file.
     *
     * @param mirror Mirror settings to set
     */
    public void setMirror (MerjMirror mirror) {
        this.mirror = mirror;
    }

}
