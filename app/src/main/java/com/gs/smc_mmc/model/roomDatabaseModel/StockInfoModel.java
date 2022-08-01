package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StockInfo")
public class StockInfoModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long stockUpdateId;
    @ColumnInfo(name = "poMappingProviderThanaID")
    private String poMappingProviderThanaID;
    @ColumnInfo(name = "poMappingProviderThanaName")
    private String poMappingProviderThanaName;
    @ColumnInfo(name = "poMappingProviderID")
    private String poMappingProviderID;
    @ColumnInfo(name = "poMappingProviderName")
    private String poMappingProviderName;
    @ColumnInfo(name = "stockQuantity")
    private String stockQuantity;
    @ColumnInfo(name = "lastPurchasedDay")
    private String lastPurchasedDay;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "flag")
    private String flag;
    @ColumnInfo(name = "updateId")
    private String updateId;

    public StockInfoModel(String poMappingProviderThanaID, String poMappingProviderThanaName, String poMappingProviderID, String poMappingProviderName, String stockQuantity, String lastPurchasedDay, String date, String flag,String updateId) {
        this.poMappingProviderThanaID = poMappingProviderThanaID;
        this.poMappingProviderThanaName = poMappingProviderThanaName;
        this.poMappingProviderID = poMappingProviderID;
        this.poMappingProviderName = poMappingProviderName;
        this.stockQuantity = stockQuantity;
        this.lastPurchasedDay = lastPurchasedDay;
        this.date = date;
        this.flag = flag;
        this.updateId = updateId;
    }

    public long getStockUpdateId() {
        return stockUpdateId;
    }

    public void setStockUpdateId(long stockUpdateId) {
        this.stockUpdateId = stockUpdateId;
    }

    public String getPoMappingProviderThanaID() {
        return poMappingProviderThanaID;
    }

    public void setPoMappingProviderThanaID(String poMappingProviderThanaID) {
        this.poMappingProviderThanaID = poMappingProviderThanaID;
    }

    public String getPoMappingProviderThanaName() {
        return poMappingProviderThanaName;
    }

    public void setPoMappingProviderThanaName(String poMappingProviderThanaName) {
        this.poMappingProviderThanaName = poMappingProviderThanaName;
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

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getLastPurchasedDay() {
        return lastPurchasedDay;
    }

    public void setLastPurchasedDay(String lastPurchasedDay) {
        this.lastPurchasedDay = lastPurchasedDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    @Override
    public String toString() {
        return "StockInfoModel{" +
                "stockUpdateId=" + stockUpdateId +
                ", poMappingProviderThanaID='" + poMappingProviderThanaID + '\'' +
                ", poMappingProviderThanaName='" + poMappingProviderThanaName + '\'' +
                ", poMappingProviderID='" + poMappingProviderID + '\'' +
                ", poMappingProviderName='" + poMappingProviderName + '\'' +
                ", stockQuantity='" + stockQuantity + '\'' +
                ", lastPurchasedDay='" + lastPurchasedDay + '\'' +
                ", date='" + date + '\'' +
                ", flag='" + flag + '\'' +
                ", updateId='" + updateId + '\'' +
                '}';
    }
}
