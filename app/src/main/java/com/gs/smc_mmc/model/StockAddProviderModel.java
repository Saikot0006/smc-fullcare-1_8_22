
package com.gs.smc_mmc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StockAddProviderModel {

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

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("ms")
        @Expose
        private String ms;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }

    }


}
