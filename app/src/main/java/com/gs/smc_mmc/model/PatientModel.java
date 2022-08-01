
package com.gs.smc_mmc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/*public class PatientModel {

    @SerializedName("pwList")
    @Expose
    private List<Pw> pwList = null;

    public List<Pw> getPwList() {
        return pwList;
    }

    public void setPwList(List<Pw> pwList) {
        this.pwList = pwList;
    }

    public class Pw {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;

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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

    }


}*/

public class PatientModel {

    @SerializedName("pwList")
    @Expose
    private List<Pw> pwList = null;

    public List<Pw> getPwList() {
        return pwList;
    }

    public void setPwList(List<Pw> pwList) {
        this.pwList = pwList;
    }

    public class Pw {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("registration_date")
        @Expose
        private String registrationDate;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;

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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

    }


}

