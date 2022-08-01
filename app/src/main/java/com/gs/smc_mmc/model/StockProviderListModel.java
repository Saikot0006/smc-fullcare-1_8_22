
package com.gs.smc_mmc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class StockProviderListModel {

    @SerializedName("pcsAddEdit")
    @Expose
    private PcsAddEdit pcsAddEdit;

    public PcsAddEdit getPcsAddEdit() {
        return pcsAddEdit;
    }

    public void setPcsAddEdit(PcsAddEdit pcsAddEdit) {
        this.pcsAddEdit = pcsAddEdit;
    }

    public class PcsAddEdit {

        @SerializedName("provider_list")
        @Expose
        private List<Provider> providerList = null;

        public List<Provider> getProviderList() {
            return providerList;
        }

        public void setProviderList(List<Provider> providerList) {
            this.providerList = providerList;
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
