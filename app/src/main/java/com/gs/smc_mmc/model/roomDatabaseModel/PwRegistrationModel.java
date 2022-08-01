package com.gs.smc_mmc.model.roomDatabaseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PwRegistrationModel {

    @SerializedName("po_mapping_thana_id")
    @Expose
    public Integer poMappingThanaId;
    @SerializedName("po_mapping_thana_name")
    @Expose
    public Integer poMappingThanaName;
    @SerializedName("provider_id")
    @Expose
    public String providerId;
    @SerializedName("provider_name")
    @Expose
    public String providerName;
    @SerializedName("pw_id")
    @Expose
    public String pwId;
    @SerializedName("pw_name")
    @Expose
    public String pwName;
    @SerializedName("age")
    @Expose
    public String age;
    @SerializedName("mobile_no")
    @Expose
    public String mobileNo;
    @SerializedName("number_of_pregnancy")
    @Expose
    public Object numberOfPregnancy;
    @SerializedName("lmp")
    @Expose
    public String lmp;
    @SerializedName("edd")
    @Expose
    public String edd;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("purchase_quantity")
    @Expose
    public Object purchaseQuantity;
    @SerializedName("registration_date")
    @Expose
    public String registrationDate;
    @SerializedName("division_id")
    @Expose
    public String divisionId;
    @SerializedName("division_name")
    @Expose
    public String divisionName;
    @SerializedName("district_id")
    @Expose
    public String districtId;
    @SerializedName("district_name")
    @Expose
    public String districtName;
    @SerializedName("thana_id")
    @Expose
    public Object thanaId;
    @SerializedName("thana_name")
    @Expose
    public Object thanaName;
    @SerializedName("buyer_id")
    @Expose
    public String buyerId;
    @SerializedName("buyer_name")
    @Expose
    public String buyerName;
    @SerializedName("health_condition_id")
    @Expose
    public Object healthConditionId;
    @SerializedName("health_condition_name")
    @Expose
    public Object healthConditionName;
    @SerializedName("city_pouroshobha")
    @Expose
    public String cityPouroshobha;
    @SerializedName("city_pouroshobha_name")
    @Expose
    public Integer cityPouroshobhaName;
    @SerializedName("city_pouroshobha_id")
    @Expose
    public String cityPouroshobhaId;

    public Integer getPoMappingThanaId() {
        return poMappingThanaId;
    }

    public void setPoMappingThanaId(Integer poMappingThanaId) {
        this.poMappingThanaId = poMappingThanaId;
    }

    public Integer getPoMappingThanaName() {
        return poMappingThanaName;
    }

    public void setPoMappingThanaName(Integer poMappingThanaName) {
        this.poMappingThanaName = poMappingThanaName;
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

    public String getPwId() {
        return pwId;
    }

    public void setPwId(String pwId) {
        this.pwId = pwId;
    }

    public String getPwName() {
        return pwName;
    }

    public void setPwName(String pwName) {
        this.pwName = pwName;
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

    public Object getNumberOfPregnancy() {
        return numberOfPregnancy;
    }

    public void setNumberOfPregnancy(Object numberOfPregnancy) {
        this.numberOfPregnancy = numberOfPregnancy;
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

    public Object getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Object purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
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

    public Object getThanaId() {
        return thanaId;
    }

    public void setThanaId(Object thanaId) {
        this.thanaId = thanaId;
    }

    public Object getThanaName() {
        return thanaName;
    }

    public void setThanaName(Object thanaName) {
        this.thanaName = thanaName;
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

    public Object getHealthConditionId() {
        return healthConditionId;
    }

    public void setHealthConditionId(Object healthConditionId) {
        this.healthConditionId = healthConditionId;
    }

    public Object getHealthConditionName() {
        return healthConditionName;
    }

    public void setHealthConditionName(Object healthConditionName) {
        this.healthConditionName = healthConditionName;
    }

    public String getCityPouroshobha() {
        return cityPouroshobha;
    }

    public void setCityPouroshobha(String cityPouroshobha) {
        this.cityPouroshobha = cityPouroshobha;
    }

    public Integer getCityPouroshobhaName() {
        return cityPouroshobhaName;
    }

    public void setCityPouroshobhaName(Integer cityPouroshobhaName) {
        this.cityPouroshobhaName = cityPouroshobhaName;
    }

    public String getCityPouroshobhaId() {
        return cityPouroshobhaId;
    }

    public void setCityPouroshobhaId(String cityPouroshobhaId) {
        this.cityPouroshobhaId = cityPouroshobhaId;
    }
}
