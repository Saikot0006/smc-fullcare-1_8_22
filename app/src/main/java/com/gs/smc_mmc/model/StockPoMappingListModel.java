package com.gs.smc_mmc.model;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockPoMappingListModel {

        @SerializedName("pcsAddEdit")
        @Expose
        private PcsAddEdit pcsAddEdit;

        public PcsAddEdit getPcsAddEdit() {
            return pcsAddEdit;
        }

        public void setPcsAddEdit(PcsAddEdit pcsAddEdit) {
            this.pcsAddEdit = pcsAddEdit;
        }

        public class PoMappingThana {

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

        public class PcsAddEdit {

            @SerializedName("po_mapping_thana")
            @Expose
            private List<PoMappingThana> poMappingThana = null;

            public List<PoMappingThana> getPoMappingThana() {
                return poMappingThana;
            }

            public void setPoMappingThana(List<PoMappingThana> poMappingThana) {
                this.poMappingThana = poMappingThana;
            }

        }

    }



