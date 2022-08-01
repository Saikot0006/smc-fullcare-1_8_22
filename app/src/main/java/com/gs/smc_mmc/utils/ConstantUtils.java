package com.gs.smc_mmc.utils;

public class ConstantUtils {

    public  class ServiceBaseUrl{
        //public static final String BASE_URL = "http://182.163.127.104/api_data_retrives/"; //live
       public static final String BASE_URL = "http://182.163.127.104/api_data_offline_retrives/"; //live
       // public static final String BASE_URL = "http://182.163.127.104/fullcare/api_data_retrives/"; //live_test
    //  public static final String BASE_URL = "http://192.168.10.108:8081/smc_fullcare/api_data_ofline_retrives/"; //local
        //public static final String BASE_URL = "http://192.168.10.41:8081/smc_fullcare/"; //local

    }

    public static class Temp {
        public static int id =0;
    }

    public static class AdminPreference{
        public static final String FILE_NAME = "auth_name";
        public static final String STATUS_KEY = "status";
    }

}
