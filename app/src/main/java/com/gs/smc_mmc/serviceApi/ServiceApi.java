package com.gs.smc_mmc.serviceApi;

import com.google.gson.JsonObject;
import com.gs.smc_mmc.model.AllDataModelClass;
import com.gs.smc_mmc.model.CityPouroshovaUnion;
import com.gs.smc_mmc.model.DashboardModel;
import com.gs.smc_mmc.model.DistrictNameModel;
import com.gs.smc_mmc.model.LoginModel;
import com.gs.smc_mmc.model.PWEditModel;
import com.gs.smc_mmc.model.PWViewModel;
import com.gs.smc_mmc.model.PatientModel;
import com.gs.smc_mmc.model.PoProviderModel;
import com.gs.smc_mmc.model.PoWorkLocationSaveModel;
import com.gs.smc_mmc.model.PwRegistrationModel;
import com.gs.smc_mmc.model.StockAddProviderModel;
import com.gs.smc_mmc.model.StockListModel;
import com.gs.smc_mmc.model.StockPoMappingListModel;
import com.gs.smc_mmc.model.StockProviderEditModel;
import com.gs.smc_mmc.model.StockProviderListModel;
import com.gs.smc_mmc.model.StockProviderViewModel;
import com.gs.smc_mmc.model.ThanaModel;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {

///..........................PW GET...............................................////
    @POST("pw_registration_list.json")
    Call<PatientModel>setAllPatientWomen(@Body JsonObject jsonObject);

  /*  @GET("pw_registration_list.json")
    Call<PW_RecordModel>getAllPatientWomen2();*/

    @GET("pw_registration.json")
    Call<PwRegistrationModel> getAllDivisionApi();

    @POST("pw_registration.json")
    Call<PwRegistrationModel> getAllProviderApi(@Body JsonObject jsonObject);

    @POST("get_po_provider_list.json")
    Call<PoProviderModel> getAllPo_ProviderApi(@Body JsonObject jsonObject);

    @POST("get_city_poroshova_union_list.json")
    Call<CityPouroshovaUnion> get_city_poroshova_union_list(@Body JsonObject jsonObject);

    @GET("pw_registration.json")
    Call<PwRegistrationModel> getHealthCondition();

    @GET("pw_registration.json")
    Call<PwRegistrationModel> getAllBuyer();

    @GET("pw_registration_list.json")
    Call<PatientModel>setAllPatientWomenList(@Body JsonObject jsonObject);

    ///...............................x.......................................................///


   /* @GET("provider_stock_add_edit.json")
    Call<StockModel> getAllProviders();*/

    ///..........................PW POST...............................................////

    @POST("pw_registration.json")
    Call<PwRegistrationModel> pushPwRegistration(@Body JsonObject jsonObject);

    @POST("pw_view_edit.json")
    Call<PWViewModel> setPWInfo(@Body JsonObject jsonObject);

    @POST("pw_view_edit.json")
    Call<PWEditModel> setPWEdit(@Body JsonObject jsonObject);

    ///.......................................x...........................................///

    ///................................LoginModel......................................///

    @POST("user_login.json")
    Call<LoginModel> userServiceLoginApi(@Body JsonObject jsonObject);

    ///....................................x..........................................///

   ///.................................Dashboard.........................................///

    @POST("dashboard.json")
    Call<DashboardModel> postDashBoard(@Body JsonObject jsonObject);

    ///..................................x................................................///

    ///..................................District--Thana.....................................///

    @POST("get_districts.json")
    Call<DistrictNameModel> setAllDistrictApi(@Body JsonObject jsonObject);

    @POST("get_thana.json")
    Call<ThanaModel> getAllThana(@Body JsonObject jsonObject);

    ///..............................................x...........................................///

    ///...................................GET_____Stock.............................................///

    @POST("provider_stock_add_edit.json")
    Call<StockProviderListModel> getAllStockProvider(@Body JsonObject jsonObject);

    @POST("provider_stock_add_edit.json")
    Call<StockPoMappingListModel> getAllStockPoMapping(@Body JsonObject jsonObject);


    ///..............................................................................................///


    ///...................................Post_____Stock.............................................///

    @POST("provider_current_stock_list.json")
    Call<StockListModel> getAllStockList(@Body JsonObject jsonObject);

    @POST("provider_stock_add_edit.json")
    Call<StockAddProviderModel> addAllStockProvider(@Body JsonObject jsonObject);


    @POST("provider_current_stock_view.json")
    Call<StockProviderViewModel> viewStockProvider(@Body JsonObject jsonObject);

    @POST("provider_current_stock_edit.json")
    Call<StockProviderEditModel> editStockProvider(@Body JsonObject jsonObject);

    //@POST("provider_current_stock_list.json")
    //Call<StockModel> setAllStock(@Body JsonObject jsonObject);

    ///..............................................................................................///

   /* @POST("provider_stock_add_edit.json")
    Call<StockUpdateModel> stockUpdate(@Body JsonObject jsonObject);*/



//    @POST("pw_registration.json")
//    Call<DistrictModel> setAllDistrictApi(@Body JsonObject jsonObject);



 //   @POST("pw_registration.json")
//    Call<PwRegistrationModel> setAll(@Body JsonObject jsonObject);







   /* @POST("provider_current_stock_view_edit.json")
    Call<SViewModel> setStockInfo(@Body JsonObject jsonObject);*/


    /*@POST("provider_current_stock_view_edit.json")
    Call<StockEditModel> setStockEdit(@Body JsonObject jsonObject);*/





   /* @GET("provider_stock_add_edit.json")
    Call<StockProviderModel> getAllStockProvider();*/


//...................................................................................................................................................//
//...................................................................................................................................................//

    @POST("get_data_web_to_apps.json")
    Call<AllDataModelClass> getDataWebToApps(@Body JsonObject jsonObject);


    @POST("po_work_location_save.json")
    Call<PoWorkLocationSaveModel> poWorkLocationSave(@Body JsonObject jsonObject);



}
