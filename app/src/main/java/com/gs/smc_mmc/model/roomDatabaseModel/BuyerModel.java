package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Buyer")
public class BuyerModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private long id;
    @ColumnInfo(name = "buyerId")
    private int buyerId;
    @ColumnInfo(name = "name")
    private String buyerName;

    public BuyerModel(int buyerId, String buyerName) {
        this.buyerId = buyerId;
        this.buyerName = buyerName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
