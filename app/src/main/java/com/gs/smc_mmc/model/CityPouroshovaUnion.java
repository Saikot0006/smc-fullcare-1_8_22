package com.gs.smc_mmc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityPouroshovaUnion {

    @SerializedName("cpu_list")
    @Expose
    private CpuList cpuList;

    public CpuList getCpuList() {
        return cpuList;
    }

    public void setCpuList(CpuList cpuList) {
        this.cpuList = cpuList;
    }

    public class Cpu {

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

    public class CpuList {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("cpu_list")
        @Expose
        private List<Cpu> cpuList = null;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public List<Cpu> getCpuList() {
            return cpuList;
        }

        public void setCpuList(List<Cpu> cpuList) {
            this.cpuList = cpuList;
        }

    }
}
