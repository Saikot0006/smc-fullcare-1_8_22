package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "poMappingProvider")
public class PoMappingProviderModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "poMappingProviderID")
    private String poMappingProviderID;
    @ColumnInfo(name = "name")
    private String poMappingProviderName;
    @ColumnInfo(name = "userID")
    private String poMappingProviderUserID;
    @ColumnInfo(name = "thanaID")
    private String poMappingProviderThanaID;

    public PoMappingProviderModel(String poMappingProviderID, String poMappingProviderName, String poMappingProviderUserID, String poMappingProviderThanaID) {
        this.poMappingProviderID = poMappingProviderID;
        this.poMappingProviderName = poMappingProviderName;
        this.poMappingProviderUserID = poMappingProviderUserID;
        this.poMappingProviderThanaID = poMappingProviderThanaID;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoMappingProviderID() {
        return poMappingProviderID;
    }

    public void setPoMappingProviderID(String poMappingProviderID) {
        this.poMappingProviderID = poMappingProviderID;
    }

    public String getPoMappingProviderName() {
        return poMappingProviderName;
    }

    public void setPoMappingProviderName(String poMappingProviderName) {
        this.poMappingProviderName = poMappingProviderName;
    }

    public String getPoMappingProviderUserID() {
        return poMappingProviderUserID;
    }

    public void setPoMappingProviderUserID(String poMappingProviderUserID) {
        this.poMappingProviderUserID = poMappingProviderUserID;
    }

    public String getPoMappingProviderThanaID() {
        return poMappingProviderThanaID;
    }

    public void setPoMappingProviderThanaID(String poMappingProviderThanaID) {
        this.poMappingProviderThanaID = poMappingProviderThanaID;
    }
}
