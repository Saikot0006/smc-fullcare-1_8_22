
package com.gs.smc_mmc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PWViewModel {

    @SerializedName("pwViewEdit")
    @Expose
    private List<PwViewEdit> pwViewEdit = null;

    public List<PwViewEdit> getPwViewEdit() {
        return pwViewEdit;
    }

    public void setPwViewEdit(List<PwViewEdit> pwViewEdit) {
        this.pwViewEdit = pwViewEdit;
    }

    public class PwViewEdit {

        @SerializedName("info")
        @Expose
        private Info info;

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

    }

    public class Info {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("provider_name")
        @Expose
        private String providerName;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("division_name")
        @Expose
        private String divisionName;
        @SerializedName("district_name")
        @Expose
        private String districtName;
        @SerializedName("thana_name")
        @Expose
        private String thanaName;
        @SerializedName("is_urban")
        @Expose
        private String isUrban;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;
        @SerializedName("is_mob_personal")
        @Expose
        private String isMobPersonal;
        @SerializedName("number_of_pregnancy")
        @Expose
        private String numberOfPregnancy;
        @SerializedName("buyer_name")
        @Expose
        private String buyerName;
        @SerializedName("lmp")
        @Expose
        private String lmp;
        @SerializedName("edd")
        @Expose
        private String edd;
        @SerializedName("health_condition_name")
        @Expose
        private String healthConditionName;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("registration_date")
        @Expose
        private String registrationDate;
        @SerializedName("city_pouroshobha")
        @Expose
        private String city_pouroshobha;
        @SerializedName("city_pouroshobha_name")
        @Expose
        private String city_pouroshobha_name;

        public String getCity_pouroshobha() {
            return city_pouroshobha;
        }

        public void setCity_pouroshobha(String city_pouroshobha) {
            this.city_pouroshobha = city_pouroshobha;
        }

        public String getCity_pouroshobha_name() {
            return city_pouroshobha_name;
        }

        public void setCity_pouroshobha_name(String city_pouroshobha_name) {
            this.city_pouroshobha_name = city_pouroshobha_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getDivisionName() {
            return divisionName;
        }

        public void setDivisionName(String divisionName) {
            this.divisionName = divisionName;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getThanaName() {
            return thanaName;
        }

        public void setThanaName(String thanaName) {
            this.thanaName = thanaName;
        }

        public String getIsUrban() {
            return isUrban;
        }

        public void setIsUrban(String isUrban) {
            this.isUrban = isUrban;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getIsMobPersonal() {
            return isMobPersonal;
        }

        public void setIsMobPersonal(String isMobPersonal) {
            this.isMobPersonal = isMobPersonal;
        }

        public String getNumberOfPregnancy() {
            return numberOfPregnancy;
        }

        public void setNumberOfPregnancy(String numberOfPregnancy) {
            this.numberOfPregnancy = numberOfPregnancy;
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

        public String getHealthConditionName() {
            return healthConditionName;
        }

        public void setHealthConditionName(String healthConditionName) {
            this.healthConditionName = healthConditionName;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
        }

    }



}
