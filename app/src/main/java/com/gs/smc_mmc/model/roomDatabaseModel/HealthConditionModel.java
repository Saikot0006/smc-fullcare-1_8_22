package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "HealthCondition")
public class HealthConditionModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "healthConditionID")
    private int healthConditionID;
    @ColumnInfo(name = "name")
    private String healthConditionName;

    public HealthConditionModel(int healthConditionID, String healthConditionName) {
        this.healthConditionID = healthConditionID;
        this.healthConditionName = healthConditionName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHealthConditionID() {
        return healthConditionID;
    }

    public void setHealthConditionID(int healthConditionID) {
        this.healthConditionID = healthConditionID;
    }

    public String getHealthConditionName() {
        return healthConditionName;
    }

    public void setHealthConditionName(String healthConditionName) {
        this.healthConditionName = healthConditionName;
    }
}
