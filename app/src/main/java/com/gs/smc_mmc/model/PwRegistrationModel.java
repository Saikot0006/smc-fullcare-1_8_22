
package com.gs.smc_mmc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class PwRegistrationModel {



    @SerializedName("pwAddEdit")
    @Expose
    private List<PwAddEdit> pwAddEdit = null;

    public List<PwAddEdit> getPwAddEdit() {
        return pwAddEdit;
    }

    public void setPwAddEdit(List<PwAddEdit> pwAddEdit) {
        this.pwAddEdit = pwAddEdit;
    }


    public class PwAddEdit {

        @SerializedName("status")
        @Expose
        private  Integer status;
        @SerializedName("po_maping_thana")
        @Expose
        private List<PoMapingThana> poMapingThana = null;
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

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public List<PoMapingThana> getPoMapingThana() {
            return poMapingThana;
        }

        public void setPoMapingThana(List<PoMapingThana> poMapingThana) {
            this.poMapingThana = poMapingThana;
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

    public class PoMapingThana {

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


