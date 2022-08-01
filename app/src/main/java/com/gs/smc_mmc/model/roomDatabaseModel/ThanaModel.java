package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "thana")
public class ThanaModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "thanaID")
    private String thanaID;
    @ColumnInfo(name = "name")
    private String thanaName;
    @ColumnInfo(name = "divisionID")
    private String divisionID;
    @ColumnInfo(name = "districtID")
    private String districtID;

    public ThanaModel(String thanaID, String thanaName, String divisionID, String districtID) {
        this.thanaID = thanaID;
        this.thanaName = thanaName;
        this.divisionID = divisionID;
        this.districtID = districtID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThanaID() {
        return thanaID;
    }

    public void setThanaID(String thanaID) {
        this.thanaID = thanaID;
    }

    public String getThanaName() {
        return thanaName;
    }

    public void setThanaName(String thanaName) {
        this.thanaName = thanaName;
    }

    public String getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(String divisionID) {
        this.divisionID = divisionID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }
}
