/**
 * -----------------------------------------------------------------------------------------------------------
 *  Project: MERJMirror
 *  Interface: IMerjDao
 *  Last Edited by: Ryan
 *  Last Edited: 9-19-17
 * ----------------------------------------------------------------------------------------------------------- 
 */
package com.merjmirror.database.interfaces;

import java.util.ArrayList;

import com.merjmirror.classes.DataPref;

public interface IMerjDao {
    
    // Delete Commands
    public void deleteData (int dataId);
    
    public void deletePref (int prefId, int userId);
    
    public void deleteUser (int userId);
    
    //Insert Into commands
    public void insertData (int dataId, String dataName);
    
    public void insertPref (int prefId, int userId, String prefName, String dataDisplay, String active);
    
    public void insertUser (int userId, String userName);
    
    //Select Commands
    public ArrayList<String> selectData ();
    
    public ArrayList<DataPref> selectPref (int userId);
    
    public ArrayList<String> selectUser ();
    
    //Update Commands
    public void updateActive (int prefId, int userId, String active);
    
    public void updateData (int dataIdOld, int dataId, String dataName);
    
    public void updatePref (int prefIdOld, int userId, int prefId, String prefName, String dataDisplay, String active);
    
    public void updateUser (int userIdOld, int userId, String userName);
    
}
