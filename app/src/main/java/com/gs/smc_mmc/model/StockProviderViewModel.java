
package com.gs.smc_mmc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StockProviderViewModel {

    @SerializedName("pcsView")
    @Expose
    private PcsView pcsView;

    public PcsView getPcsView() {
        return pcsView;
    }

    public void setPcsView(PcsView pcsView) {
        this.pcsView = pcsView;
    }

    public class PcsView {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("info")
        @Expose
        private Info info;

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

    }



}
