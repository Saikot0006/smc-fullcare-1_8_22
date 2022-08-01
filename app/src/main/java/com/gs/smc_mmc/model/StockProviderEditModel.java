
package com.gs.smc_mmc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class StockProviderEditModel {

    @SerializedName("pcsEdit")
    @Expose
    private PcsEdit pcsEdit;

    public PcsEdit getPcsEdit() {
        return pcsEdit;
    }

    public void setPcsEdit(PcsEdit pcsEdit) {
        this.pcsEdit = pcsEdit;
    }

    public class PcsEdit {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("info")
        @Expose
        private Info info;
        @SerializedName("provider_list")
        @Expose
        private List<Provider> providerList = null;
        @SerializedName("po_mapping_thana")
        @Expose
        private List<Object> poMappingThana = null;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public List<Provider> getProviderList() {
            return providerList;
        }

        public void setProviderList(List<Provider> providerList) {
            this.providerList = providerList;
        }

        public List<Object> getPoMappingThana() {
            return poMappingThana;
        }

        public void setPoMappingThana(List<Object> poMappingThana) {
            this.poMappingThana = poMappingThana;
        }
    }

    public class Info {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("qty")
        @Expose
        private String qty;
        @SerializedName("provider_name")
        @Expose
        private String providerName;
        @SerializedName("provider_id")
        @Expose
        private String providerId;
        @SerializedName("provider_mapping_thana_id")
        @Expose
        private String providerMappingThanaId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }

        public String getProviderId() {
            return providerId;
        }

        public void setProviderId(String providerId) {
            this.providerId = providerId;
        }

        public String getProviderMappingThanaId() {
            return providerMappingThanaId;
        }

        public void setProviderMappingThanaId(String providerMappingThanaId) {
            this.providerMappingThanaId = providerMappingThanaId;
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



}
