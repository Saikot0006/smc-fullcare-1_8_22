package com.gs.smc_mmc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllDataModelClass {

    @SerializedName("all_data")
    @Expose
    public AllData allData;

    public AllData getAllData() {
        return allData;
    }

    public void setAllData(AllData allData) {
        this.allData = allData;
    }

    public class AllData {

        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("po_mapping_thana_list")
        @Expose
        public List<PoMappingThana> poMappingThanaList = null;
        @SerializedName("po_mapping_provider_list")
        @Expose
        public List<PoMappingProvider> poMappingProviderList = null;
        @SerializedName("division")
        @Expose
        public List<Division> division = null;
        @SerializedName("district")
        @Expose
        public List<District> district = null;
        @SerializedName("thana")
        @Expose
        public List<Thana> thana = null;
        @SerializedName("city_pouroshoba_union_list")
        @Expose
        public List<CityPouroshobaUnion> cityPouroshobaUnionList = null;
        @SerializedName("buyer")
        @Expose
        public List<Buyer> buyer = null;
        @SerializedName("healthCondition")
        @Expose
        public List<HealthCondition> healthCondition = null;
        @SerializedName("pw_registration_list")
        @Expose
        public List<PwRegistration> pwRegistrationList = null;
        @SerializedName("stock_list")
        @Expose
        public List<Stock> stockList = null;



        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public List<PoMappingThana> getPoMappingThanaList() {
            return poMappingThanaList;
        }

        public void setPoMappingThanaList(List<PoMappingThana> poMappingThanaList) {
            this.poMappingThanaList = poMappingThanaList;
        }

        public List<PoMappingProvider> getPoMappingProviderList() {
            return poMappingProviderList;
        }

        public void setPoMappingProviderList(List<PoMappingProvider> poMappingProviderList) {
            this.poMappingProviderList = poMappingProviderList;
        }

        public List<Division> getDivision() {
            return division;
        }

        public void setDivision(List<Division> division) {
            this.division = division;
        }

        public List<District> getDistrict() {
            return district;
        }

        public void setDistrict(List<District> district) {
            this.district = district;
        }

        public List<Thana> getThana() {
            return thana;
        }

        public void setThana(List<Thana> thana) {
            this.thana = thana;
        }

        public List<CityPouroshobaUnion> getCityPouroshobaUnionList() {
            return cityPouroshobaUnionList;
        }

        public void setCityPouroshobaUnionList(List<CityPouroshobaUnion> cityPouroshobaUnionList) {
            this.cityPouroshobaUnionList = cityPouroshobaUnionList;
        }

        public List<Buyer> getBuyer() {
            return buyer;
        }

        public void setBuyer(List<Buyer> buyer) {
            this.buyer = buyer;
        }

        public List<HealthCondition> getHealthCondition() {
            return healthCondition;
        }

        public void setHealthCondition(List<HealthCondition> healthCondition) {
            this.healthCondition = healthCondition;
        }

        public List<PwRegistration> getPwRegistrationList() {
            return pwRegistrationList;
        }

        public void setPwRegistrationList(List<PwRegistration> pwRegistrationList) {
            this.pwRegistrationList = pwRegistrationList;
        }

        public List<Stock> getStockList() {
            return stockList;
        }

        public void setStockList(List<Stock> stockList) {
            this.stockList = stockList;
        }

        public class PoMappingThana {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("name")
            @Expose
            public String name;

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }


        public class PoMappingProvider {

            @SerializedName("id")
            @Expose
            public String id;
            @SerializedName("name")
            @Expose
            public String name;
            @SerializedName("thana_id")
            @Expose
            public String thanaId;

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getThanaId() {
                return thanaId;
            }
        }

        public class Division {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("name")
            @Expose
            public String name;

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }

        public class District {

            @SerializedName("id")
            @Expose
            public String id;
            @SerializedName("name")
            @Expose
            public String name;
            @SerializedName("division_id")
            @Expose
            public String divisionId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDivisionId() {
                return divisionId;
            }

            public void setDivisionId(String divisionId) {
                this.divisionId = divisionId;
            }
        }

        public class Thana {

            @SerializedName("id")
            @Expose
            public String id;
            @SerializedName("name")
            @Expose
            public String name;
            @SerializedName("division_id")
            @Expose
            public String divisionId;
            @SerializedName("district_id")
            @Expose
            public String districtId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDivisionId() {
                return divisionId;
            }

            public void setDivisionId(String divisionId) {
                this.divisionId = divisionId;
            }

            public String getDistrictId() {
                return districtId;
            }

            public void setDistrictId(String districtId) {
                this.districtId = districtId;
            }
        }

        public class Buyer {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("name")
            @Expose
            public String name;

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

        public class CityPouroshobaUnion {

            @SerializedName("id")
            @Expose
            public String id;
            @SerializedName("name")
            @Expose
            public String name;
            @SerializedName("type")
            @Expose
            public String type;
            @SerializedName("thana")
            @Expose
            public String thana;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getThana() {
                return thana;
            }

            public void setThana(String thana) {
                this.thana = thana;
            }
        }

        public class HealthCondition {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("name")
            @Expose
            public String name;

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

        public class PwRegistration {

            @SerializedName("po_mapping_thana_id")
            @Expose
            public String poMappingThanaId;
            @SerializedName("po_mapping_thana_name")
            @Expose
            public String poMappingThanaName;
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
            public String numberOfPregnancy;
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
            public String purchaseQuantity;
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
            public String thanaId;
            @SerializedName("thana_name")
            @Expose
            public String thanaName;
            @SerializedName("buyer_id")
            @Expose
            public String buyerId;
            @SerializedName("buyer_name")
            @Expose
            public String buyerName;
            @SerializedName("health_condition_id")
            @Expose
            public String healthConditionId;
            @SerializedName("health_condition_name")
            @Expose
            public String healthConditionName;
            @SerializedName("city_pouroshobha")
            @Expose
            public String cityPouroshobha;
            @SerializedName("city_pouroshobha_name")
            @Expose
            public String cityPouroshobhaName;
            @SerializedName("city_pouroshobha_id")
            @Expose
            public String cityPouroshobhaId;

            public String getPoMappingThanaId() {
                return poMappingThanaId;
            }

            public void setPoMappingThanaId(String poMappingThanaId) {
                this.poMappingThanaId = poMappingThanaId;
            }

            public String getPoMappingThanaName() {
                return poMappingThanaName;
            }

            public void setPoMappingThanaName(String poMappingThanaName) {
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

            public String getNumberOfPregnancy() {
                return numberOfPregnancy;
            }

            public void setNumberOfPregnancy(String numberOfPregnancy) {
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

            public String getPurchaseQuantity() {
                return purchaseQuantity;
            }

            public void setPurchaseQuantity(String purchaseQuantity) {
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
        }

        public class Stock {
            @SerializedName("po_mapping_thana_id")
            @Expose
            public String poMappingThanaId;
            @SerializedName("po_mapping_thana_name")
            @Expose
            public String poMappingThanaName;
            @SerializedName("provider_id")
            @Expose
            public String providerId;
            @SerializedName("provider_name")
            @Expose
            public String providerName;
            @SerializedName("stock_id")
            @Expose
            public String stockId;
            @SerializedName("purchase_qty")
            @Expose
            public String purchaseQty;
            @SerializedName("purchase_day")
            @Expose
            public String purchaseDay;
            @SerializedName("create_date")
            @Expose
            public String createDate;


            public String getPoMappingThanaId() {
                return poMappingThanaId;
            }

            public void setPoMappingThanaId(String poMappingThanaId) {
                this.poMappingThanaId = poMappingThanaId;
            }

            public String getPoMappingThanaName() {
                return poMappingThanaName;
            }

            public void setPoMappingThanaName(String poMappingThanaName) {
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

            public String getStockId() {
                return stockId;
            }

            public void setStockId(String stockId) {
                this.stockId = stockId;
            }

            public String getPurchaseQty() {
                return purchaseQty;
            }

            public void setPurchaseQty(String purchaseQty) {
                this.purchaseQty = purchaseQty;
            }

            public String getPurchaseDay() {
                return purchaseDay;
            }

            public void setPurchaseDay(String purchaseDay) {
                this.purchaseDay = purchaseDay;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }


    }



}
