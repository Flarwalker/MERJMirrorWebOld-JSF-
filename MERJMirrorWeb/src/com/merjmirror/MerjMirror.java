/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: MerjMirror
 *  Last Edited by: Ryan
 *  Last Edited: 11-12-17
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
    private static final Logger LOGGER = Logger.getLogger(MerjMirror.class.getName());
    private static final long serialVersionUID = 3L;

    private AppConfig config;

    private ArrayList<String> data;
    private ArrayList<DataPref> prefs;
    private ArrayList<String> users;

    private IMerjDao merjDao;

    private int activeUser;

    private DataPref edit;

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
        prefs = merjDao.selectPref();
        activeUser = 0;
    }

    /**
     * Add a data to the table and the list.
     * @param dataName Name of the data to set
     */
    public void addData (String dataName) {
        data.add(dataName);
        merjDao.insertData(data.indexOf(dataName), dataName);
    }

    /**
     * Adds a user to the table and the list.
     * 
     * @param index Location to put the user Name
     * @param userName Name of the user
     */
    public void addUser (int index, String userName) {
        if (index >= users.size()) {
            users.add(userName);
        } else {
            users.set(index, userName);
        }
    
        merjDao.insertUser(users.indexOf(userName), userName);
    }

    /**
     * Created a new Preference Set.
     * 
     * @param name Name of Preference
     * @param data Data set to save
     * @param zip Zip COnde for weather
     * @param stock Stock code for stocks
     * @param sign Horoscope sign
     */
    public void createPreference (String name, ArrayList<String> data, String city, String state, String source, String sunSign, String[] stocks) {
        String da = "";
        String type;
        int index;
        
        String stockList = stocks[0];
        for (int i = 1; i < stocks.length; i++) {
            stockList = stockList + "|" + stocks[i]; 
        }
    
        da = da + "1,";
        type = data.get(0);
        index = this.data.indexOf(type);
        index++;
    
        da = da + index;
        if (!data.get(0).equalsIgnoreCase("")) {
            switch (data.get(0)) { //TODO Still need to add in the Reminders and comic widgets
                case "Clock" :     break;
                case "Weather" :   da = da + "(" + city + "|" + state;
                                   break;
                case "News" :      da = da + "(" + source;
                                   break;
                case "Horoscope" : da = da + "(" + sunSign; 
                                   break;
                case "Stocks" :     da = da + "(" + stockList;
                                   break;
                default : break;
            }
        }
    
        for (int i = 1; i < 9; i++) {
            da = da + ":" + (i + 1);
            type = data.get(i);
            index = this.data.indexOf(type);
            index++;
        
            da = da + "," + index;
            if (!data.get(i).equalsIgnoreCase("")) {
                switch (data.get(i)) { //TODO Still need to add in the Reminders and comic widgets
                    case "Clock" :     break;
                    case "Weather" :   da = da + "(" + city + "|" + state;
                                       break;
                    case "News" :      da = da + "(" + source;
                                       break;
                    case "Horoscope" : da = da + "(" + sunSign; 
                                       break;
                    case "Stocks" :     da = da + "(" + stockList;
                                       break;
                    default : break;
                }
            }
        }
    
        DataPref temp = new DataPref(false, 0, activeUser + 1, name, da);
        prefs.add(temp);
        int loc = prefs.indexOf(temp);
        prefs.get(loc).setPrefId(loc);
    
        merjDao.insertPref(loc, activeUser, name, da, "0");
    }

    /**
     * Deletes a Preferences from the Users Preference List.
     * 
     * @param pref Preference to be removed
     * @param index location of Preference to be removed
     */
    public void deletePref (DataPref pref, int index) {
        prefs.remove(index);
        merjDao.deletePref(pref.getPrefId(), activeUser);
    }

    /**
     * Deletes a User from the List of Users.
     * 
     * @param index Location of user to delete
     */
    public void deleteUser (int index) {
        if (index == users.size()) {
            users.remove(index);
            merjDao.deleteUser(index);
        } else {
            users.set(index, "");
            merjDao.deleteUser(index);
        }
    }
    
    public void deleteUserPrefs (int userId) {
        merjDao.deleteUserPref(userId);
    }

    /**
     * Edits the selected preference with the pasted value.
     * 
     * @param selectedPref Preference to Edit
     * @param title New Title to set
     * @param data New set of data to set
     * @param zip New Zip Code to set
     * @param stock New Stock Tick Codes to set
     * @param sign New Horoscope Sign to set
     */
    public void editPref (DataPref selectedPref, String title, ArrayList<String> data, String city, String state, String source, String sunSign, String[] stocks) {
        DataPref temp = selectedPref;
        String da = "";
        String type;
        int index;
        
        String stockList = stocks[0];
        for (int i = 1; i < stocks.length; i++) {
            stockList = stockList + "|" + stocks[i]; 
        }
    
        temp.setPrefName(title);
    
        da = da + "1,";
        type = data.get(0);
        index = this.data.indexOf(type);
        index++;
    
        da = da + index;
        if (!data.get(0).equalsIgnoreCase("")) {
            switch (data.get(0)) { //TODO Still need to add in the Reminders and comic widgets
                case "Clock" :     break;
                case "Weather" :   da = da + "(" + city + "|" + state;
                                   break;
                case "News" :      da = da + "(" + source;
                                   break;
                case "Horoscope" : da = da + "(" + sunSign; 
                                   break;
                case "Stocks" :     da = da + "(" + stockList;
                                   break;
                default : break;
            }
        }
    
        for (int i = 1; i < 9; i++) {
            da = da + ":" + (i + 1);
            type = data.get(i);
            index = this.data.indexOf(type);
            index++;
        
            da = da + "," + index;
            switch (data.get(i)) { //TODO Still need to add in the Reminders and comic widgets
                case "Clock" :     break;
                case "Weather" :   da = da + "(" + city + "|" + state;
                                   break;
                case "News" :      da = da + "(" + source;
                                   break;
                case "Horoscope" : da = da + "(" + sunSign; 
                                   break;
                case "Stocks" :     da = da + "(" + stockList;
                                   break;
                default : break;
            }
        }
    
        String act;
        if (temp.isActive()) {
            act = "1";
        } else {
            act = "0";
        }
    
        temp.setDataDisplay(da);
        merjDao.updatePref(selectedPref.getPrefId(), activeUser, temp.getPrefId(), temp.getPrefName(), temp.getDataDisplay(), act);
    
        int pindex = prefs.indexOf(selectedPref);
        prefs.set(pindex, temp);
    }

    /**
     * Returns the location of the active user.
     * @return activeUser
     */
    public int getActiveUser() {
        return activeUser;
    }

    /**
     * Returns the list of data.
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
     * Returns the Edit preference storage.
     * @return edit
     */
    public DataPref getEdit () {
        return edit;
    }

    /**
     * Gets the database object.
     * @return merjDao
     */
    public IMerjDao getMerjDao () {
        return merjDao;
    }

    /**
     * Returns a list of the Preferences.
     * @return prefs
     */
    public ArrayList<DataPref> getPrefs () {
        return prefs;
    }

    /**
     * Returns the indexed preferences list.
     * 
     * @param index Location to get value from
     * @return preference
     */
    public DataPref getPrefs (int index) {
        return prefs.get(index);
    }

    /**
     * Returns the list of Users.
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
     * Reads in the Active User's Preferences.
     * @return list
     */
    public ArrayList<DataPref> readUserPref () {
        ArrayList<DataPref> list = new ArrayList<DataPref> ();
    
        for (DataPref pr : prefs) {
            if (pr.getUserId() == (activeUser + 1)) {
                list.add(pr);
            }
        }
        return list;
    }

    /**
     * Sets the active user location to the pasted value.
     * @param activeUser location of active User
     */
    public void setActiveUser (int activeUser) {
        this.activeUser = activeUser;
    }

    /**
     * Sets the data List to the pasted list.
     * @param data list to set
     */
    public void setData (ArrayList<String> data) {
        this.data = data;
    }

    /**
     * Sets the edit preference storage.
     * @param edit Preference to set
     */
    public void setEdit (DataPref edit) {
        this.edit = edit;
    }

    /**
     * Sets the database to the pasted value.
     * @param merjDao Database object
     */
    public void setMerjDao (IMerjDao merjDao) {
        this.merjDao = merjDao;
    }

    /**
     * Sets the user preference list to the pasted list.
     * @param userPref List to set
     */
    public void setPref (ArrayList<DataPref> userPref) {
        this.prefs = userPref;
    }

    /**
     * Sets the users list to the pasted list.
     * @param users List to set
     */
    public void setUsers (ArrayList<String> users) {
        this.users = users;
    }

    /**
     * Updates the Users Active Preference.
     * 
     * @param prefID Preference ID
     * @param active Active Value to set
     */
    public void updateActive (int prefID, boolean active) {
        String act;
        if (active) {
            act = "1";
        } else {
            act = "0";
        }
        prefs.get(prefID).setActive(active);
        merjDao.updateActive(prefID, activeUser, act);
    }

    /**
     * Updates the Name of a user.
     * 
     * @param index Location of User Name
     * @param oldName original User Name
     * @param newName New User Name
     */
    public void updateUserName (int index, String oldName, String newName) {
        merjDao.updateUser(index, index, oldName, newName);
    }

}
