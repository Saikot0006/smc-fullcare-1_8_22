package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "district")
public class DistrictModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "districtID")
    private String districtID;
    @ColumnInfo(name = "name")
    private String districtName;
    @ColumnInfo(name = "divisionID")
    private String divisionID;

    public DistrictModel(String districtID, String districtName, String divisionID) {
        this.districtID = districtID;
        this.districtName = districtName;
        this.divisionID = divisionID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(String divisionID) {
        this.divisionID = divisionID;
    }
}
