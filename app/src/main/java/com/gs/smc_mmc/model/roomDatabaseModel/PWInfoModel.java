package com.gs.smc_mmc.model.roomDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "PwInfo")
public class PWInfoModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "userId")
    private int userId;
    @ColumnInfo(name = "providerMappingThanaId")
    private String providerMappingThanaId;
    @ColumnInfo(name = "providerMappingThanaName")
    private String providerMappingThanaName;
    @ColumnInfo(name = "providerId")
    private String providerId;
    @ColumnInfo(name = "providerName")
    private String providerName;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "age")
    private String age;
    @ColumnInfo(name = "mobileNo")
    private String mobileNo;
    @ColumnInfo(name = "divisionId")
    private String divisionId;
    @ColumnInfo(name = "divisionName")
    private String divisionName;
    @ColumnInfo(name = "districtId")
    private String districtId;
    @ColumnInfo(name = "districtName")
    private String districtName;
    @ColumnInfo(name = "thanaId")
    private String thanaId;
    @ColumnInfo(name = "thanaName")
    private String thanaName;
    @ColumnInfo(name = "cityPouroshobha")
    private String cityPouroshobha;
    @ColumnInfo(name = "cityPouroshobhaName")
    private String cityPouroshobhaName;
    @ColumnInfo(name = "cityPouroshobhaId")
    private String cityPouroshobhaId;
    @ColumnInfo(name = "latitude")
    private String latitude;
    @ColumnInfo(name = "longitude")
    private String longitude;
    @ColumnInfo(name = "numberOfPregnancy")
    private String numberOfPregnancy;
    @ColumnInfo(name = "buyerId")
    private String buyerId;
    @ColumnInfo(name = "buyerName")
    private String buyerName;
    @ColumnInfo(name = "lmp")
    private String lmp;
    @ColumnInfo(name = "edd")
    private String edd;
    @ColumnInfo(name = "healthConditionId")
    private String healthConditionId;
    @ColumnInfo(name = "healthConditionName")
    private String healthConditionName;
    @ColumnInfo(name = "registrationDate")
    private String registrationDate;
    @ColumnInfo(name = "purchaseQuantity")
    private String purchaseQuantity;
    @ColumnInfo(name = "flag")
    private String flag;
    @ColumnInfo(name = "updateID")
    private String updateID;

/*
    public PWInfoModel(String providerMappingThanaId, String providerMappingThanaName, String providerId, String providerName, String name, String age, String mobileNo, String divisionId, String divisionName, String districtId, String districtName, String thanaId, String thanaName, String cityPouroshobha, String cityPouroshobhaName, String cityPouroshobhaId, String latitude, String longitude, String numberOfPregnancy, String buyerId, String buyerName, String lmp, String edd, String healthConditionId, String healthConditionName, String registrationDate, String purchaseQuantity,String flag,String updateID) {
        this.providerMappingThanaId = providerMappingThanaId;
        this.providerMappingThanaName = providerMappingThanaName;
        this.providerId = providerId;
        this.providerName = providerName;
        this.name = name;
        this.age = age;
        this.mobileNo = mobileNo;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.thanaId = thanaId;
        this.thanaName = thanaName;
        this.cityPouroshobha = cityPouroshobha;
        this.cityPouroshobhaName = cityPouroshobhaName;
        this.cityPouroshobhaId = cityPouroshobhaId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numberOfPregnancy = numberOfPregnancy;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.lmp = lmp;
        this.edd = edd;
        this.healthConditionId = healthConditionId;
        this.healthConditionName = healthConditionName;
        this.registrationDate = registrationDate;
        this.purchaseQuantity = purchaseQuantity;
        this.flag = flag;
        this.updateID = updateID;

    }
*/

    public PWInfoModel(int userId, String providerMappingThanaId, String providerMappingThanaName, String providerId, String providerName, String name, String age, String mobileNo, String divisionId, String divisionName, String districtId, String districtName, String thanaId, String thanaName, String cityPouroshobha, String cityPouroshobhaName, String cityPouroshobhaId, String latitude, String longitude, String numberOfPregnancy, String buyerId, String buyerName, String lmp, String edd, String healthConditionId, String healthConditionName, String registrationDate, String purchaseQuantity, String flag, String updateID) {
        this.id = id;
        this.userId = userId;
        this.providerMappingThanaId = providerMappingThanaId;
        this.providerMappingThanaName = providerMappingThanaName;
        this.providerId = providerId;
        this.providerName = providerName;
        this.name = name;
        this.age = age;
        this.mobileNo = mobileNo;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.thanaId = thanaId;
        this.thanaName = thanaName;
        this.cityPouroshobha = cityPouroshobha;
        this.cityPouroshobhaName = cityPouroshobhaName;
        this.cityPouroshobhaId = cityPouroshobhaId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numberOfPregnancy = numberOfPregnancy;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.lmp = lmp;
        this.edd = edd;
        this.healthConditionId = healthConditionId;
        this.healthConditionName = healthConditionName;
        this.registrationDate = registrationDate;
        this.purchaseQuantity = purchaseQuantity;
        this.flag = flag;
        this.updateID = updateID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProviderMappingThanaId() {
        return providerMappingThanaId;
    }

    public void setProviderMappingThanaId(String providerMappingThanaId) {
        this.providerMappingThanaId = providerMappingThanaId;
    }

    public String getProviderMappingThanaName() {
        return providerMappingThanaName;
    }

    public void setProviderMappingThanaName(String providerMappingThanaName) {
        this.providerMappingThanaName = providerMappingThanaName;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getThanaId() {
        return thanaId;
    }

    public void setThanaId(String thanaId) {
        this.thanaId = thanaId;
    }

    public String getThanaName() {
        return thanaName;
    }

    public void setThanaName(String thanaName) {
        this.thanaName = thanaName;
    }

    public String getCityPouroshobha() {
        return cityPouroshobha;
    }

    public void setCityPouroshobha(String cityPouroshobha) {
        this.cityPouroshobha = cityPouroshobha;
    }

    public String getCityPouroshobhaName() {
        return cityPouroshobhaName;
    }

    public void setCityPouroshobhaName(String cityPouroshobhaName) {
        this.cityPouroshobhaName = cityPouroshobhaName;
    }

    public String getCityPouroshobhaId() {
        return cityPouroshobhaId;
    }

    public void setCityPouroshobhaId(String cityPouroshobhaId) {
        this.cityPouroshobhaId = cityPouroshobhaId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNumberOfPregnancy() {
        return numberOfPregnancy;
    }

    public void setNumberOfPregnancy(String numberOfPregnancy) {
        this.numberOfPregnancy = numberOfPregnancy;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getLmp() {
        return lmp;
    }

    public void setLmp(String lmp) {
        this.lmp = lmp;
    }

    public String getEdd() {
        return edd;
    }

    public void setEdd(String edd) {
        this.edd = edd;
    }

    public String getHealthConditionId() {
        return healthConditionId;
    }

    public void setHealthConditionId(String healthConditionId) {
        this.healthConditionId = healthConditionId;
    }

    public String getHealthConditionName() {
        return healthConditionName;
    }

    public void setHealthConditionName(String healthConditionName) {
        this.healthConditionName = healthConditionName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(String purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUpdateID() {
        return updateID;
    }

    public void setUpdateID(String updateID) {
        this.updateID = updateID;
    }

    @Override
    public String toString() {
        return "PWInfoModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", providerMappingThanaId='" + providerMappingThanaId + '\'' +
                ", providerMappingThanaName='" + providerMappingThanaName + '\'' +
                ", providerId='" + providerId + '\'' +
                ", providerName='" + providerName + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", divisionId='" + divisionId + '\'' +
                ", divisionName='" + divisionName + '\'' +
                ", districtId='" + districtId + '\'' +
                ", districtName='" + districtName + '\'' +
                ", thanaId='" + thanaId + '\'' +
                ", thanaName='" + thanaName + '\'' +
                ", cityPouroshobha='" + cityPouroshobha + '\'' +
                ", cityPouroshobhaName='" + cityPouroshobhaName + '\'' +
                ", cityPouroshobhaId='" + cityPouroshobhaId + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", numberOfPregnancy='" + numberOfPregnancy + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", lmp='" + lmp + '\'' +
                ", edd='" + edd + '\'' +
                ", healthConditionId='" + healthConditionId + '\'' +
                ", healthConditionName='" + healthConditionName + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", purchaseQuantity='" + purchaseQuantity + '\'' +
                ", flag='" + flag + '\'' +
                ", updateID='" + updateID + '\'' +
                '}';
    }
}
