package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "poMappingThana")
public class PoMappingThanaModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "poMappingThanaID")
    private int poMappingThanaID;
    @ColumnInfo(name = "name")
    private String poMappingThanaName;

    public PoMappingThanaModel(int poMappingThanaID, String poMappingThanaName) {
        this.poMappingThanaID = poMappingThanaID;
        this.poMappingThanaName = poMappingThanaName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPoMappingThanaID() {
        return poMappingThanaID;
    }

    public void setPoMappingThanaID(int poMappingThanaID) {
        this.poMappingThanaID = poMappingThanaID;
    }

    public String getPoMappingThanaName() {
        return poMappingThanaName;
    }

    public void setPoMappingThanaName(String poMappingThanaName) {
        this.poMappingThanaName = poMappingThanaName;
    }

    @Override
    public String toString() {
        return "PoMappingThanaModel{" +
                "id=" + id +
                ", poMappingThanaID=" + poMappingThanaID +
                ", poMappingThanaName='" + poMappingThanaName + '\'' +
                '}';
    }
}
