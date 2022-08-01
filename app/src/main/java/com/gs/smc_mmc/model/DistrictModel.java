
package com.gs.smc_mmc.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DistrictModel {

    @SerializedName("division_id")
    @Expose
    private Integer divisionId;

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

}
