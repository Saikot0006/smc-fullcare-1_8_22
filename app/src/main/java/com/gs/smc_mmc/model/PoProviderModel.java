package com.gs.smc_mmc.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PoProviderModel {

        @SerializedName("provider")
        @Expose
        private Provider provider;

        public Provider getProvider() {
            return provider;
        }

        public void setProvider(Provider provider) {
            this.provider = provider;
        }

    public class Provider {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("provider_list")
        @Expose
        private List<Provider__1> providerList = null;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public List<Provider__1> getProviderList() {
            return providerList;
        }

        public void setProviderList(List<Provider__1> providerList) {
            this.providerList = providerList;
        }

    }

    public class Provider__1 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

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

    }

    @Override
    public String toString() {
        return "PoProviderModel{" +
                "provider=" + provider +
                '}';
    }
}
