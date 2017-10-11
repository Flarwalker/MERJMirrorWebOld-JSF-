/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: Preference Bean
 *  Last Edited by: Ryan
 *  Last Edited: 10-10-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.merjmirror.MerjMirror;
import com.merjmirror.classes.DataPref;

/**
 * Class to interact with the Preference list page.
 * @author Ryan
 */
@ManagedBean(name = "preferenceBean")
@SessionScoped
public class PreferenceBean implements Serializable {
    private static final long serialVersionUID = 4L;

    private ArrayList<DataPref> filterList;
    private ArrayList<DataPref> prefList;
    
    private boolean hasActive;

    private DataPref selectedPref;

    private int activePrefID;

    private MerjMirror mirror;

    /**
     * Public Constructor.
     */
    public PreferenceBean () {
        
    }
    
    /**
     * Deletes the Selected Preference Set.
     */
    public void deletePref () {
        int index = prefList.indexOf(selectedPref);
        prefList.remove(index);
        mirror.deletePref(selectedPref, index);
        selectedPref = null;
    }

    /**
     * Gets the Filer List.
     * @return filterList
     */
    public ArrayList<DataPref> getFilterList () {
        return filterList;
    }

    /**
     * Gets the List of saved Preferences.
     * @return prefList
     */
    public ArrayList<DataPref> getPrefList () {
        return prefList;
    }

    /**
     * Gets the Selected Preference.
     * @return selectdPref
     */
    public DataPref getSelectedPref () {
        return selectedPref;
    }

    /**
     * Function to run at the creation of the Bean.
     */
    @PostConstruct
    public  void init () {
        mirror = ApplicationBean.getMirror();
        mirror.readUserPref();
        prefList = mirror.getUserPrefs();
        
        for (DataPref pf : prefList) {
            if (pf.isActive()) {
                hasActive = true;
                activePrefID = pf.getPrefId();
            }
        }
    }
    
    /**
     * Sets the Filtered LIst to the Pasted List.
     * @param filterList List to set
     */
    public void setFilterList (ArrayList<DataPref> filterList) {
        this.filterList = filterList;
    }
    
    /**
     * Sets the Preference List to the Pasted List.
     * @param prefList List to set
     */
    public void setPrefList (ArrayList<DataPref> prefList) {
        this.prefList = prefList;
    }
    
    /**
     * Sets the Selected Preference to the pasted Preference.
     * @param pref Preference to set
     */
    public void setSelectedPref (DataPref pref) {
        this.selectedPref = pref;
    }
    
    /**
     * Updates the Active Preference Set.
     */
    public void updateActive () {
        if (hasActive) {
            mirror.updateActive(activePrefID, false);
            activePrefID = selectedPref.getPrefId();
            mirror.updateActive(activePrefID, true);
        } else {
            mirror.updateActive(activePrefID, true);
        }
    }
    
}
