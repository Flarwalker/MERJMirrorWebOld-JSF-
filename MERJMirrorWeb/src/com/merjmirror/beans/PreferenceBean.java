/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Class: Preference Bean
 *  Last Edited by: Ryan
 *  Last Edited: 10-10-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.merjmirror.MerjMirror;
import com.merjmirror.classes.DataPref;

/**
 * Class to interact with the Preference list page.
 * @author Ryan
 */
@ManagedBean(name = "preferenceBean")
@ViewScoped
public class PreferenceBean implements Serializable {
    private static final long serialVersionUID = 4L;

    private ArrayList<DataPref> filterList;
    private ArrayList<DataPref> prefList;
    
    private boolean hasActive;

    private DataPref selectedPref;

    private int activePrefID;

    private MerjMirror mirror;
    
    private String spot1;
    private String spot2;
    private String spot3;
    private String spot4;
    private String spot6;
    private String spot7;
    private String spot8;
    private String spot9;
    
    private String zip;
    private String stock;
    private String sign;
    private String title;
    
    private boolean sides;

    public boolean isSides () {
        return sides;
    }

    public void setSides (boolean sides) {
        this.sides = sides;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getSign () {
        return sign;
    }

    public void setSign (String sign) {
        this.sign = sign;
    }

    public String getStock () {
        return stock;
    }

    public void setStock (String stock) {
        this.stock = stock;
    }

    public String getZip () {
        return zip;
    }

    public void setZip (String zip) {
        this.zip = zip;
    }

    /**
     * Public Constructor.
     */
    public PreferenceBean () {
        
    }
    
    /**
     * Creates a new preference and sends it to the database.
     */
    public void createPref () {
        ArrayList<String> data = new ArrayList<String> ();
        
        if (spot1 == null) {
            data.add("");
        } else {
            data.add(spot1);
        }
        
        if (spot2 == null) {
            data.add("");
        } else {
            data.add(spot2);
        }
        
        if (spot3 == null) {
            data.add("");
        } else {
            data.add(spot3);
        }
        
        if (spot4 == null) {
            data.add("");
        } else {
            data.add(spot4);
        }
        
        data.add("");
        
        if (spot6 == null) {
            data.add("");
        } else {
            data.add(spot6);
        }
        
        if (spot7 == null) {
            data.add("");
        } else {
            data.add(spot7);
        }
        
        if (spot8 == null) {
            data.add("");
        } else {
            data.add(spot8);
        }
        
        if (spot9 == null) {
            data.add("");
        } else {
            data.add(spot9);
        }
        
        mirror.createPreference(title, data, zip, stock, sign);
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/prefList.jsf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Deletes the Selected Preference Set.
     */
    public void deletePref () {
        int index = mirror.getPrefs().indexOf(selectedPref);
        mirror.deletePref(selectedPref, index);
        prefList = mirror.readUserPref();
        selectedPref = null;
    }
    
    public void createEdit () {
        mirror.setEdit(selectedPref);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/editPref.jsf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void editPref () {
        ArrayList<String> data = new ArrayList<String> ();
        
        if (spot1 == null) {
            data.add("");
        } else {
            data.add(spot1);
        }
        
        if (spot2 == null) {
            data.add("");
        } else {
            data.add(spot2);
        }
        
        if (spot3 == null) {
            data.add("");
        } else {
            data.add(spot3);
        }
        
        if (spot4 == null) {
            data.add("");
        } else {
            data.add(spot4);
        }
        
        data.add("");
        
        if (spot6 == null) {
            data.add("");
        } else {
            data.add(spot6);
        }
        
        if (spot7 == null) {
            data.add("");
        } else {
            data.add(spot7);
        }
        
        if (spot8 == null) {
            data.add("");
        } else {
            data.add(spot8);
        }
        
        if (spot9 == null) {
            data.add("");
        } else {
            data.add(spot9);
        }
        
        mirror.editPref(selectedPref, title, data, zip, stock, sign);
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/prefList.jsf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<String> getDataDisplay () {
        return mirror.getData();
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
     * Returns the Preference for spot 1.
     * @return spot1
     */
    public String getSpot1 () {
        return spot1;
    }
    
    /**
     * Returns the Preference for spot 2.
     * @return spot2
     */
    public String getSpot2 () {
        return spot2;
    }
    
    /**
     * Return the Preference for spot 3.
     * @return spot3
     */
    public String getSpot3 () {
        return spot3;
    }
    
    /**
     * Return the Preference for spot 4.
     * @return spot4
     */
    public String getSpot4 () {
        return spot4;
    }
    
    /**
     * Return the Preference for spot 6.
     * @return spot6
     */
    public String getSpot6 () {
        return spot6;
    }
    
    /**
     * Return the Preference for spot 7.
     * @return spot7
     */
    public String getSpot7 () {
        return spot7;
    }
    
    /**
     * Return the Preference for spot 8.
     * @return spot8
     */
    public String getSpot8 () {
        return spot8;
    }
    
    /**
     * Return the Preference for spot 9.
     * @return spot9
     */
    public String getSpot9 () {
        return spot9;
    }

    /**
     * Function to run at the creation of the Bean.
     */
    @PostConstruct
    public  void init () {
        mirror = ApplicationBean.getMirror();
        prefList = mirror.readUserPref();
        
        for (DataPref pf : mirror.getPrefs()) {
            if (pf.isActive()) {
                hasActive = true;
                activePrefID = pf.getPrefId();
            }
        }
        spot1 = "";
        spot2 = "";
        spot3 = "";
        spot4 = "";
        spot6 = "";
        spot7 = "";
        spot8 = "";
        spot9 = "";
    }
    
    public void onEdit () {
        selectedPref = mirror.getEdit();
        
        title = selectedPref.getPrefName();
        String data = selectedPref.getDataDisplay();
        String [] sets = data.split(":");
        int index;
        String type;
        
        for (String set: sets) {
            String [] dat = set.split(",");
            if (dat[1].contains("(")) {
                String [] da = dat[1].split("\\(");
                index = Integer.parseInt(da[0]);
                if (index > 0) {
                    type = mirror.getData(index - 1);
                    switch (type) {
                        case "Clock" :     break;
                        case "Weather" :   zip = da[1];
                                           break;
                        case "Stock" :     stock = da[1];
                                           break;
                        case "Horoscope" : sign = da[1]; 
                                           break;
                        default : break;
                    }
                    
                    switch (dat[0]){
                        case "1" : spot1 = type;
                                   break;
                        case "2" : spot2 = type;
                                   break;
                        case "3" : spot3 = type;
                                   break;
                        case "4" : spot4 = type;
                                   break;
                        case "5" : break;
                        case "6" : spot6 = type;
                                   break;
                        case "7" : spot7 = type;
                                   break;
                        case "8" : spot8 = type;
                                   break;
                        case "9" : spot9 = type;
                                   break;
                        default  : break;
                    }
                } else {
                    switch (dat[0]){
                        case "1" : spot1 = "";
                                   break;
                        case "2" : spot2 = "";
                                   break;
                        case "3" : spot3 = "";
                                   break;
                        case "4" : spot4 = "";
                                   break;
                        case "5" : break;
                        case "6" : spot6 = "";
                                   break;
                        case "7" : spot7 = "";
                                   break;
                        case "8" : spot8 = "";
                                   break;
                        case "9" : spot9 = "";
                                   break;
                        default  : break;
                    }
                }
            } else {
                index = Integer.parseInt(dat[1]);
                if (index > 0) {
                    switch (dat[0]){
                        case "1" : spot1 = mirror.getData(index - 1);
                                   break;
                        case "2" : spot2 = mirror.getData(index - 1);
                                   break;
                        case "3" : spot3 = mirror.getData(index - 1);
                                   break;
                        case "4" : spot4 = mirror.getData(index - 1);
                                   break;
                        case "5" : break;
                        case "6" : spot6 = mirror.getData(index - 1);
                                   break;
                        case "7" : spot7 = mirror.getData(index - 1);
                                   break;
                        case "8" : spot8 = mirror.getData(index - 1);
                                   break;
                        case "9" : spot9 = mirror.getData(index - 1);
                                   break;
                        default  : break;
                    }
                } else {
                    switch (dat[0]){
                        case "1" : spot1 = "";
                                   break;
                        case "2" : spot2 = "";
                                   break;
                        case "3" : spot3 = "";
                                   break;
                        case "4" : spot4 = "";
                                   break;
                        case "5" : break;
                        case "6" : spot6 = "";
                                   break;
                        case "7" : spot7 = "";
                                   break;
                        case "8" : spot8 = "";
                                   break;
                        case "9" : spot9 = "";
                                   break;
                        default  : break;
                    }
                }
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
     * Sets the Spot 1 value.
     * @param spot1 value to set
     */
    public void setSpot1 (String spot1) {
        this.spot1 = spot1;
    }
    
    /**
     * Sets the Spot 2 value.
     * @param spot2 value to set
     */
    public void setSpot2 (String spot2) {
        this.spot2 = spot2;
    }
    
    /**
     * Sets the Spot 3 value.
     * @param spot3 value to set
     */
    public void setSpot3 (String spot3) {
        this.spot3 = spot3;
    }
    
    /**
     * Sets the Spot 4 value.
     * @param spot4 value to set
     */
    public void setSpot4 (String spot4) {
        this.spot4 = spot4;
    }
    
    /**
     * Sets the Spot 6 value.
     * @param spot6 value to set
     */
    public void setSpot6 (String spot6) {
        this.spot6 = spot6;
    }
    
    /**
     * Sets the Spot 7 value.
     * @param spot7 value to set
     */
    public void setSpot7 (String spot7) {
        this.spot7 = spot7;
    }
    
    /**
     * Sets the Spot 8 value.
     * @param spot8 value to set
     */
    public void setSpot8 (String spot8) {
        this.spot8 = spot8;
    }
    
    /**
     * Sets the Spot 9 value.
     * @param spot9 value to set
     */
    public void setSpot9 (String spot9) {
        this.spot9 = spot9;
    }
    
    /**
     * Updates the Active Preference Set.
     */
    public void updateActive () {
        if (hasActive) {
            mirror.updateActive(activePrefID, false);
            activePrefID = selectedPref.getPrefId();
            mirror.updateActive(activePrefID, true);
            prefList = mirror.readUserPref();
        } else {
            mirror.updateActive(activePrefID, true);
            prefList = mirror.readUserPref();
        }
    }
    
}
