
package com.gs.smc_mmc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StockListModel {

    @SerializedName("pcsList")
    @Expose
    private List<Pcs> pcsList = null;

    public List<Pcs> getPcsList() {
        return pcsList;
    }

    public void setPcsList(List<Pcs> pcsList) {
        this.pcsList = pcsList;
    }

    public class Pcs {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("ptype")
        @Expose
        private String ptype;
        @SerializedName("qty")
        @Expose
        private String qty;
        @SerializedName("last_date")
        @Expose
        private String lastDate;

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

        public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getLastDate() {
            return lastDate;
        }

        public void setLastDate(String lastDate) {
            this.lastDate = lastDate;
        }

    }

}
