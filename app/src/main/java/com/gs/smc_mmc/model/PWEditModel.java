
package com.gs.smc_mmc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PWEditModel {

    @SerializedName("pwViewEdit")
    @Expose
    private List<PwViewEdit> pwViewEdit = null;

    public List<PwViewEdit> getPwViewEdit() {
        return pwViewEdit;
    }

    public void setPwViewEdit(List<PwViewEdit> pwViewEdit) {
        this.pwViewEdit = pwViewEdit;
    }

   /* public class PwViewEdit {

        @SerializedName("info")
        @Expose
        private Info info;
        @SerializedName("divisions")
        @Expose
        private List<Division> divisions = null;
        @SerializedName("provider_list")
        @Expose
        private List<Provider> providerList = null;
        @SerializedName("buyers")
        @Expose
        private List<Buyer> buyers = null;
        @SerializedName("healthConditions")
        @Expose
        private List<HealthCondition> healthConditions = null;
        @SerializedName("districts")
        @Expose
        private List<District> districts = null;
        @SerializedName("thanas")
        @Expose
        private List<Thana> thanas = null;

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public List<Division> getDivisions() {
            return divisions;
        }

        public void setDivisions(List<Division> divisions) {
            this.divisions = divisions;
        }

        public List<Provider> getProviderList() {
            return providerList;
        }

        public void setProviderList(List<Provider> providerList) {
            this.providerList = providerList;
        }

        public List<Buyer> getBuyers() {
            return buyers;
        }

        public void setBuyers(List<Buyer> buyers) {
            this.buyers = buyers;
        }

        public List<HealthCondition> getHealthConditions() {
            return healthConditions;
        }

        public void setHealthConditions(List<HealthCondition> healthConditions) {
            this.healthConditions = healthConditions;
        }

        public List<District> getDistricts() {
            return districts;
        }

        public void setDistricts(List<District> districts) {
            this.districts = districts;
        }

        public List<Thana> getThanas() {
            return thanas;
        }

        public void setThanas(List<Thana> thanas) {
            this.thanas = thanas;
        }

    }*/

    public class PwViewEdit {

        @SerializedName("info")
        @Expose
        private Info info;
        @SerializedName("divisions")
        @Expose
        private List<Division> divisions = null;
        @SerializedName("po_mapping_thana")
        @Expose
        private List<PoMappingThana> poMappingThana = null;
        @SerializedName("provider_list")
        @Expose
        private List<Provider> providerList = null;
        @SerializedName("buyers")
        @Expose
        private List<Buyer> buyers = null;
        @SerializedName("healthConditions")
        @Expose
        private List<HealthCondition> healthConditions = null;
        @SerializedName("city_pouro_union_list")
        @Expose
        private List<Object> cityPouroUnionList = null;
        @SerializedName("districts")
        @Expose
        private List<District> districts = null;
        @SerializedName("thanas")
        @Expose
        private List<Thana> thanas = null;

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public List<Division> getDivisions() {
            return divisions;
        }

        public void setDivisions(List<Division> divisions) {
            this.divisions = divisions;
        }

        public List<PoMappingThana> getPoMappingThana() {
            return poMappingThana;
        }

        public void setPoMappingThana(List<PoMappingThana> poMappingThana) {
            this.poMappingThana = poMappingThana;
        }

        public List<Provider> getProviderList() {
            return providerList;
        }

        public void setProviderList(List<Provider> providerList) {
            this.providerList = providerList;
        }

        public List<Buyer> getBuyers() {
            return buyers;
        }

        public void setBuyers(List<Buyer> buyers) {
            this.buyers = buyers;
        }

        public List<HealthCondition> getHealthConditions() {
            return healthConditions;
        }

        public void setHealthConditions(List<HealthCondition> healthConditions) {
            this.healthConditions = healthConditions;
        }

        public List<Object> getCityPouroUnionList() {
            return cityPouroUnionList;
        }

        public void setCityPouroUnionList(List<Object> cityPouroUnionList) {
            this.cityPouroUnionList = cityPouroUnionList;
        }

        public List<District> getDistricts() {
            return districts;
        }

        public void setDistricts(List<District> districts) {
            this.districts = districts;
        }

        public List<Thana> getThanas() {
            return thanas;
        }

        public void setThanas(List<Thana> thanas) {
            this.thanas = thanas;
        }

    }

    /*public class Info {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("provider_id")
        @Expose
        private String providerId;
        @SerializedName("provider_name")
        @Expose
        private String providerName;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("division_id")
        @Expose
        private String divisionId;
        @SerializedName("division_name")
        @Expose
        private String divisionName;
        @SerializedName("district_id")
        @Expose
        private String districtId;
        @SerializedName("district_name")
        @Expose
        private String districtName;
        @SerializedName("thana_id")
        @Expose
        private String thanaId;
        @SerializedName("thana_name")
        @Expose
        private String thanaName;
        @SerializedName("city_pouroshobha")
        @Expose
        private String cityPouroshobha;
        @SerializedName("city_pouroshobha_name")
        @Expose
        private String cityPouroshobhaName;
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
        @SerializedName("buyer_id")
        @Expose
        private String buyerId;
        @SerializedName("buyer_name")
        @Expose
        private String buyerName;
        @SerializedName("lmp")
        @Expose
        private String lmp;
        @SerializedName("edd")
        @Expose
        private String edd;
        @SerializedName("health_condition_id")
        @Expose
        private String healthConditionId;
        @SerializedName("health_condition_name")
        @Expose
        private String healthConditionName;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("registration_date")
        @Expose
        private String registrationDate;
        @SerializedName("purchase_quantity")
        @Expose
        private String purchaseQuantity;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPurchaseQuantity() {
            return purchaseQuantity;
        }

        public void setPurchaseQuantity(String purchaseQuantity) {
            this.purchaseQuantity = purchaseQuantity;
        }

    }*/

    public class Info {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("provider_id")
        @Expose
        private String providerId;
        @SerializedName("provider_name")
        @Expose
        private String providerName;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("division_id")
        @Expose
        private String divisionId;
        @SerializedName("division_name")
        @Expose
        private String divisionName;
        @SerializedName("district_id")
        @Expose
        private String districtId;
        @SerializedName("district_name")
        @Expose
        private String districtName;
        @SerializedName("thana_id")
        @Expose
        private String thanaId;
        @SerializedName("thana_name")
        @Expose
        private String thanaName;
        @SerializedName("city_pouroshobha")
        @Expose
        private String cityPouroshobha;
        @SerializedName("city_pouroshobha_name")
        @Expose
        private String cityPouroshobhaName;
        @SerializedName("city_pouroshobha_id")
        @Expose
        private String cityPouroshobhaId;
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
        @SerializedName("buyer_id")
        @Expose
        private String buyerId;
        @SerializedName("buyer_name")
        @Expose
        private String buyerName;
        @SerializedName("lmp")
        @Expose
        private String lmp;
        @SerializedName("edd")
        @Expose
        private String edd;
        @SerializedName("health_condition_id")
        @Expose
        private String healthConditionId;
        @SerializedName("health_condition_name")
        @Expose
        private String healthConditionName;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("registration_date")
        @Expose
        private String registrationDate;
        @SerializedName("purchase_quantity")
        @Expose
        private String purchaseQuantity;
        @SerializedName("provider_mapping_thana_id")
        @Expose
        private String providerMappingThanaId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPurchaseQuantity() {
            return purchaseQuantity;
        }

        public void setPurchaseQuantity(String purchaseQuantity) {
            this.purchaseQuantity = purchaseQuantity;
        }

        public String getProviderMappingThanaId() {
            return providerMappingThanaId;
        }

        public void setProviderMappingThanaId(String providerMappingThanaId) {
            this.providerMappingThanaId = providerMappingThanaId;
        }
    }

    public class Division {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class District {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Thana {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Provider {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Buyer {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class HealthCondition {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class PoMappingThana {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }






}
