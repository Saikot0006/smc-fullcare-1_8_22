
package com.gs.smc_mmc.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardModel {

    @SerializedName("dashb")
    @Expose
    private Dashb dashb;

    public Dashb getDashb() {
        return dashb;
    }

    public void setDashb(Dashb dashb) {
        this.dashb = dashb;
    }

    public class Dashb {

        @SerializedName("currentQuarter")
        @Expose
        private CurrentQuarter currentQuarter;
        @SerializedName("lastQuarter")
        @Expose
        private LastQuarter lastQuarter;
        @SerializedName("currentMonth")
        @Expose
        private CurrentMonth currentMonth;
        @SerializedName("preMonth")
        @Expose
        private PreMonth preMonth;

        public CurrentQuarter getCurrentQuarter() {
            return currentQuarter;
        }

        public void setCurrentQuarter(CurrentQuarter currentQuarter) {
            this.currentQuarter = currentQuarter;
        }

        public LastQuarter getLastQuarter() {
            return lastQuarter;
        }

        public void setLastQuarter(LastQuarter lastQuarter) {
            this.lastQuarter = lastQuarter;
        }

        public CurrentMonth getCurrentMonth() {
            return currentMonth;
        }

        public void setCurrentMonth(CurrentMonth currentMonth) {
            this.currentMonth = currentMonth;
        }

        public PreMonth getPreMonth() {
            return preMonth;
        }

        public void setPreMonth(PreMonth preMonth) {
            this.preMonth = preMonth;
        }

    }

    public class CurrentQuarter {

        @SerializedName("totalVisit")
        @Expose
        private Integer totalVisit;
        @SerializedName("totalPWRegistration")
        @Expose
        private Integer totalPWRegistration;

        public Integer getTotalVisit() {
            return totalVisit;
        }

        public void setTotalVisit(Integer totalVisit) {
            this.totalVisit = totalVisit;
        }

        public Integer getTotalPWRegistration() {
            return totalPWRegistration;
        }

        public void setTotalPWRegistration(Integer totalPWRegistration) {
            this.totalPWRegistration = totalPWRegistration;
        }

    }

    public class LastQuarter {

        @SerializedName("totalVisit")
        @Expose
        private Integer totalVisit;
        @SerializedName("totalPWRegistration")
        @Expose
        private Integer totalPWRegistration;

        public Integer getTotalVisit() {
            return totalVisit;
        }

        public void setTotalVisit(Integer totalVisit) {
            this.totalVisit = totalVisit;
        }

        public Integer getTotalPWRegistration() {
            return totalPWRegistration;
        }

        public void setTotalPWRegistration(Integer totalPWRegistration) {
            this.totalPWRegistration = totalPWRegistration;
        }

    }

    public class CurrentMonth {

        @SerializedName("totalVisit")
        @Expose
        private Integer totalVisit;
        @SerializedName("totalPWRegistration")
        @Expose
        private Integer totalPWRegistration;

        public Integer getTotalVisit() {
            return totalVisit;
        }

        public void setTotalVisit(Integer totalVisit) {
            this.totalVisit = totalVisit;
        }

        public Integer getTotalPWRegistration() {
            return totalPWRegistration;
        }

        public void setTotalPWRegistration(Integer totalPWRegistration) {
            this.totalPWRegistration = totalPWRegistration;
        }

    }

    public class PreMonth {

        @SerializedName("totalVisit")
        @Expose
        private Integer totalVisit;
        @SerializedName("totalPWRegistration")
        @Expose
        private Integer totalPWRegistration;

        public Integer getTotalVisit() {
            return totalVisit;
        }

        public void setTotalVisit(Integer totalVisit) {
            this.totalVisit = totalVisit;
        }

        public Integer getTotalPWRegistration() {
            return totalPWRegistration;
        }

        public void setTotalPWRegistration(Integer totalPWRegistration) {
            this.totalPWRegistration = totalPWRegistration;
        }

    }






}
