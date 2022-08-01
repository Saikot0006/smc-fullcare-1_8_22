
package com.gs.smc_mmc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ThanaModel {

    @SerializedName("thana")
    @Expose
    private List<Thana> thana = null;

    public List<Thana> getThana() {
        return thana;
    }

    public void setThana(List<Thana> thana) {
        this.thana = thana;
    }

    public class Thana {

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


}
