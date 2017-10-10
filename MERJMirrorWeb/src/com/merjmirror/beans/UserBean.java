/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: UserBean
 *  Last Edited by: Ryan
 *  Last Edited: 9-30-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.merjmirror.MerjMirror;
import com.merjmirror.beans.ApplicationBean;

/**
 * Class to interact with the user selection pages.
 * 
 * @author Ryan
 */
@SessionScoped
@ManagedBean(name = "userBean")
public class UserBean implements Serializable {
    private static final long serialVersionUID = 2L;
    
    private MerjMirror mirror;
    private ArrayList<String> users;
    private String activeUser;
    
    /**
     * Empty Constructor.
     */
    public UserBean () {
        
    }

    /**
     * Returns the active users location.
     * 
     * @return activeUser
     */
    public String getActiveUser () {
        return activeUser;
    }

    /**
     * Returns the mirror object.
     * 
     * @return mirror
     */
    public MerjMirror getMirror () {
        return mirror;
    }

    /**
     * Gets the User List.
     * 
     * @return users
     */
    public ArrayList<String> getUsers () {
        return users;
    }

    /**
     * Run when the page is done loading.
     */
    @PostConstruct
    public void init () {
        ApplicationBean.onload();
        mirror = ApplicationBean.getMirror();
        users = mirror.getUsers();
    }

    /**
     * Sets the mirror object.
     * 
     * @param mirror Object to set
     */
    public void setMirror (MerjMirror mirror) {
        this.mirror = mirror;
    }

    /**
     * Sets the Users list.
     * 
     * @param users list to set
     */
    public void setUsers (ArrayList<String> users) {
        this.users = users;
    }

    /**
     * Sets the active User.
     * 
     * @param activeUser Users Name to set
     */
    public void setActiveUser (String activeUser) {
        this.activeUser = activeUser;
        updateActiveUser();
    }
    
    /**
     * Changes the active User in the mirror object.
     */
    public void updateActiveUser () {
        mirror.setActiveUser(users.indexOf(activeUser));
    }
    
}
