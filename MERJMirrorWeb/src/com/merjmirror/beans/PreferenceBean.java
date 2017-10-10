/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: Preference Bean
 *  Last Edited by: Ryan
 *  Last Edited: 9-27-17
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
    
    private MerjMirror mirror;
    
    private ArrayList<DataPref> prefList;
    private ArrayList<DataPref> filterList;
    private DataPref selectedPref;
    
    private boolean hasActive;
    private int activePrefID;

    public PreferenceBean () {
        
    }
    
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
    
    public ArrayList<DataPref> getFilterList () {
        return filterList;
    }
    
    public ArrayList<DataPref> getPrefList () {
        return prefList;
    }
    
    public DataPref getSelectedPref () {
        return selectedPref;
    }
    
    public void setFilterList (ArrayList<DataPref> filterList) {
        this.filterList = filterList;
    }
    
    public void setPrefList (ArrayList<DataPref> prefList) {
        this.prefList = prefList;
    }
    
    public void setSelectedPref (DataPref pref) {
        this.selectedPref = pref;
    }
    
    public void updateActive () {
        if (hasActive) {
            mirror.updateActive(activePrefID, false);
            activePrefID = selectedPref.getPrefId();
            mirror.updateActive(activePrefID, true);
        } else {
            mirror.updateActive(activePrefID, true);
        }
    }
    
    public void deletePref () {
        int index = prefList.indexOf(selectedPref);
        prefList.remove(index);
        mirror.deletePref(selectedPref, index);
    }
}
