package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cityPouroshovaUnion")
public class cityPouroshovaUnionModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "cityPouroshovaUnionID")
    private String cityPouroshovaUnionID;
    @ColumnInfo(name = "name")
    private String cityPouroshovaUnionName;
    @ColumnInfo(name = "thanaID")
    private String cityPouroshovaUnionThana;
    @ColumnInfo(name = "type")
    private String cityPouroshovaUnionType;

    public cityPouroshovaUnionModel(String cityPouroshovaUnionID, String cityPouroshovaUnionName, String cityPouroshovaUnionThana, String cityPouroshovaUnionType) {
        this.cityPouroshovaUnionID = cityPouroshovaUnionID;
        this.cityPouroshovaUnionName = cityPouroshovaUnionName;
        this.cityPouroshovaUnionThana = cityPouroshovaUnionThana;
        this.cityPouroshovaUnionType = cityPouroshovaUnionType;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityPouroshovaUnionID() {
        return cityPouroshovaUnionID;
    }

    public void setCityPouroshovaUnionID(String cityPouroshovaUnionID) {
        this.cityPouroshovaUnionID = cityPouroshovaUnionID;
    }

    public String getCityPouroshovaUnionName() {
        return cityPouroshovaUnionName;
    }

    public void setCityPouroshovaUnionName(String cityPouroshovaUnionName) {
        this.cityPouroshovaUnionName = cityPouroshovaUnionName;
    }

    public String getCityPouroshovaUnionThana() {
        return cityPouroshovaUnionThana;
    }

    public void setCityPouroshovaUnionThana(String cityPouroshovaUnionThana) {
        this.cityPouroshovaUnionThana = cityPouroshovaUnionThana;
    }

    public String getCityPouroshovaUnionType() {
        return cityPouroshovaUnionType;
    }

    public void setCityPouroshovaUnionType(String cityPouroshovaUnionType) {
        this.cityPouroshovaUnionType = cityPouroshovaUnionType;
    }
}
