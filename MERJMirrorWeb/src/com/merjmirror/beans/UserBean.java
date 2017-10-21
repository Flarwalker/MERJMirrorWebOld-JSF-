/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: UserBean
 *  Last Edited by: Ryan
 *  Last Edited: 10-20-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.merjmirror.MerjMirror;
import com.merjmirror.beans.ApplicationBean;

/**
 * Class to interact with the user selection pages.
 * @author Ryan
 */
@SessionScoped
@ManagedBean(name = "userBean")
public class UserBean implements Serializable {
    private static final long serialVersionUID = 2L;

    private ArrayList<String> users;

    private MerjMirror mirror;

    private String activeUser;
    private String selectedUser;
    private String tempName;
    private String tempName1;

    /**
     * Empty Constructor.
     */
    public UserBean () {
    
    }

    /**
     * Changed the Name of the Selected User.
     */
    public void changeUsername () {
        int index = users.indexOf(selectedUser);
    
        if (index != -1) {
            users.set(index, tempName1);
            mirror.updateUserName(index, selectedUser, tempName1);
            selectedUser = tempName1;
            tempName1 = "";
            selectedUser = "";
        }
    }

    /**
     * Creates a New User and adds it to the lists.
     */
    public void createUser () {
        selectedUser = null;
        if (!tempName.equalsIgnoreCase("")) {
            for (int i = 0; i < users.size(); i ++) {
                if (users.get(i).equalsIgnoreCase("")) {
                    users.set(i, tempName);
                    mirror.addUser(i, tempName);
                    tempName = "";
                    return;
                }
            }
        
            users.add(tempName);
            mirror.addUser(users.indexOf(tempName), tempName);
            tempName = "";
        }
    }

    /**
     * Removes a User from the List.
     */
    public void deleteUser () {
        int index = users.indexOf(selectedUser);
    
        if (index != -1) {
            if (index == users.size()) {
                users.remove(index);
                mirror.deleteUser(index);
                selectedUser = "";
            } else {
                users.set(index, "");
                mirror.deleteUser(index);
                selectedUser = "";
            }
        }
    }

    /**
     * Returns the active users location.
     * @return activeUser
     */
    public String getActiveUser () {
        return activeUser;
    }

    /**
     * Returns the mirror object.
     * @return mirror
     */
    public MerjMirror getMirror () {
        return mirror;
    }

    /**
     * Returns the Selected User.
     * @return selectedUser
     */
    public String getSelectedUser () {
        return selectedUser;
    }

    /**
     * Returns the Temporary UserName.
     * @return tempName
     */
    public String getTempName () {
        return tempName;
    }

    /**
     * Returns the Temporary UserName 1.
     * @return tempname1
     */
    public String getTempName1 () {
        return tempName1;
    }

    /**
     * Gets the User List.
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
        tempName = "";
    }

    /**
     * Sets the active User.
     * @param activeUser Users Name to set
     */
    public void setActiveUser (String activeUser) {
        this.activeUser = activeUser;
        updateActiveUser();
    }

    /**
     * Sets the mirror object.
     * @param mirror Object to set
     */
    public void setMirror (MerjMirror mirror) {
        this.mirror = mirror;
    }

    /**
     * Resets the active user.
     */
    public void setNewActiveUser () {
        this.activeUser = selectedUser;
        updateActiveUser();
        selectedUser = "";
    
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/prefList.jsf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sets the selected User equal to the active User.
     */
    public void setSelected () {
        selectedUser = activeUser;
    }

    /**
     * Sets the Selected User to the Pasted Value.
     * @param user User to set
     */
    public void setSelectedUser (String user) {
        this.selectedUser = user;
    }

    /**
     * Sets the Users list.
     * @param users list to set
     */
    public void setUsers (ArrayList<String> users) {
        this.users = users;
    }

    /**
     * Sets the Temporary UserName to the passed Name.
     * @param tempName Name to set
     */
    public void setTempName (String tempName) {
        this.tempName = tempName;
    }

    /**
     * Sets the Temporary UserName 1 to the passed name.
     * @param tempName1 Name to set
     */
    public void setTempName1 (String tempName1) {
        this.tempName1 = tempName1;
    }

    /**
     * Changes the active User in the mirror object.
     */
    public void updateActiveUser () {
        mirror.setActiveUser(users.indexOf(activeUser));
    }

}
