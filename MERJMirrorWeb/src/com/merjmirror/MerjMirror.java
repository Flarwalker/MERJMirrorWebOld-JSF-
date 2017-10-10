/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: MerjMirror
 *  Last Edited by: Ryan
 *  Last Edited: 9-27-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.merjmirror.classes.DataPref;
import com.merjmirror.database.JdbcDaoFactory;
import com.merjmirror.database.interfaces.IMerjDao;
import com.merjmirror.util.config.AppConfig;

/**
 * Class to hold the mirror setting and conversions.
 * @author Ryan
 */
public class MerjMirror implements Serializable {
    private static final long serialVersionUID = 3L;

    private static final Logger LOGGER = Logger.getLogger(MerjMirror.class.getName());
    
    private ArrayList<String> users;
    private ArrayList<String> data;
    private ArrayList<DataPref> userPrefs;

    private IMerjDao merjDao;
    
    private AppConfig config;
    
    private int activeUser;

    /**
     * Public Constructor.
     * @param config Config file
     */
    public MerjMirror (AppConfig config) {
        this.config = config;
        
        try {
            JdbcDaoFactory fact = new JdbcDaoFactory(this.config);
            merjDao = fact.getMerjDao();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        users = merjDao.selectUser();
        data = merjDao.selectData();
        activeUser = 0;
    }

    /**
     * Adds a user to the table and the list.
     * @param userName Name of the user
     */
    public void addUser (String userName) {
        users.add(userName);
        merjDao.insertUser(users.indexOf(userName), userName);
    }

    /**
     * Adds a Preference table 
     * @param prefName Name of the preference to the table and list.
     * @param dataDisplay Data sets to show
     * @param active Is the preference the active
     * @param userId Owners Id
     */
    public void addUserPref (String prefName, ArrayList<String> dataDisplay, boolean active, int userId) {
        String data = "";
        data = data + "1," + dataDisplay.get(1);
        for (int i = 1; i < dataDisplay.size(); i++) {
            data = data + ":" + i + "," + dataDisplay.get(i);
        }
        
        DataPref pref = new DataPref(active, 0, userId, prefName, data);
        userPrefs.add(pref);
        int index = userPrefs.indexOf(pref);
        userPrefs.get(index).setPrefId(index);
        
        String act;
        if (active) {
            act = "1";
        } else {
            act = "0";
        }
        
        merjDao.insertPref(index, userId, prefName, data, act);
    }

    /**
     * Add a data to the table and the list.
     * 
     * @param dataName Name of the data to set
     */
    public void addData (String dataName) {
        data.add(dataName);
        merjDao.insertData(data.indexOf(dataName), dataName);
    }
    
    /**
     * Returns the location of the active user.
     * 
     * @return activeUser
     */
    public int getActiveUser() {
        return activeUser;
    }

    /**
     * Returns the list of data.
     * 
     * @return data
     */
    public ArrayList<String> getData () {
        return data;
    }

    /**
     * Get data at set Index.
     * 
     * @param index Location of data to get
     * @return data
     */
    public String getData (int index) {
        return data.get(index);
    }

    /**
     * Returns the list of Users.
     * 
     * @return users
     */
    public ArrayList<String> getUsers () {
        return users;
    }

    /**
     * Returns a user name at the indexed location.
     * 
     * @param index Location to get from
     * @return user
     */
    public String getUsers (int index) {
        return users.get(index);
    }

    /**
     * Returns the indexed preferences list.
     * 
     * @param index Location to get value from
     * @return userPref
     */
    public DataPref getUserPrefs (int index) {
        return userPrefs.get(index);
    }

    /**
     * Returns a list of the User Preferences.
     * 
     * @return userPrefs
     */
    public ArrayList<DataPref> getUserPrefs () {
        return userPrefs;
    }

    /**
     * Gets the database object.
     * 
     * @return merjDao
     */
    public IMerjDao getMerjDao () {
        return merjDao;
    }
    
    public void readUserPref () {
        userPrefs = merjDao.selectPref(activeUser);
    }
    
    /**
     * Sets the active user location to the pasted value.
     * 
     * @param activeUser location to save
     */
    public void setActiveUser (int activeUser) {
        this.activeUser = activeUser;
    }

    /**
     * Sets the data List to the pasted list.
     * 
     * @param data list to set.
     */
    public void setData (ArrayList<String> data) {
        this.data = data;
    }

    /**
     * Sets the users list to the pasted list.
     * 
     * @param users List to set
     */
    public void setUsers (ArrayList<String> users) {
        this.users = users;
    }

    /**
     * Sets the user preference list to the pasted list.
     * 
     * @param userPref List to set
     */
    public void setUserPref (ArrayList<DataPref> userPref) {
        this.userPrefs = userPref;
    }

    /**
     * Sets the database to the pasted value.
     * 
     * @param merjDao Database object
     */
    public void setMerjDao (IMerjDao merjDao) {
        this.merjDao = merjDao;
    }
    
    public void updateActive (int prefID, boolean active) {
        String act;
        if (active) {
            act = "1";
        } else {
            act = "0";
        }
        merjDao.updateActive(prefID, activeUser, act);
    }
    
    public void deletePref (DataPref pref, int index) {
        userPrefs.remove(index);
        merjDao.deletePref(pref.getPrefId(), activeUser);
    }

}

