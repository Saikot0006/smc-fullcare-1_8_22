package com.gs.smc_mmc.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.adepter.FragmentAdepter;
import com.gs.smc_mmc.model.AllDataModelClass;
import com.gs.smc_mmc.model.PWViewModel;
import com.gs.smc_mmc.model.PwRegistrationModel;
import com.gs.smc_mmc.model.StockAddProviderModel;
import com.gs.smc_mmc.model.StockProviderEditModel;
import com.gs.smc_mmc.model.roomDatabaseModel.BuyerModel;
import com.gs.smc_mmc.model.roomDatabaseModel.DistrictModel;
import com.gs.smc_mmc.model.roomDatabaseModel.DivisionModel;
import com.gs.smc_mmc.model.roomDatabaseModel.HealthConditionModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PWInfoModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PoMappingProviderModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PoMappingThanaModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;
import com.gs.smc_mmc.model.roomDatabaseModel.ThanaModel;
import com.gs.smc_mmc.model.roomDatabaseModel.cityPouroshovaUnionModel;
import com.gs.smc_mmc.prefs.AuthPreference;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class homeFragment extends Fragment {

    private AuthPreference authPreference;
    private ImageButton  pwBtn,fcBtn,logoutBtn;
    private Button syncDataFragmentHome,pushDataFragmentHome;
    private PoMappingThanaModel poMappingThanaModel;
    private PoMappingProviderModel poMappingProviderModel;
    private DivisionModel divisionModel;
    private DistrictModel districtModel;
    private ThanaModel thanaModel;
    private cityPouroshovaUnionModel cityPouroshovaUnionModel;
    private BuyerModel buyerModel;
    private HealthConditionModel healthConditionModel;
    private ProgressDialog progressDialog;
    int i;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdepter mainAdapter;
    String value = "0";
    int userId = 0;
    Database db;


    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(getActivity())
                        .setTitle("App Close")
                        .setMessage("Do you want to exit app?")
                        .setNegativeButton("CANCEL",null)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        }).create().show();

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pwBtn = view.findViewById(R.id.pragnantWomenBtnID);
        fcBtn = view.findViewById(R.id.fullCareBtnID);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");

        tabLayout = view.findViewById(R.id.tab_layout);
        pager2 = view.findViewById(R.id.view_pager2);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        pushDataFragmentHome = view.findViewById(R.id.pushDataFragmentHome);
        syncDataFragmentHome = view.findViewById(R.id.syncDataFragmentHome);
        authPreference = new AuthPreference(getContext());
        try {
            userId = ConstantUtils.Temp.id;
            Log.e("userId", "onCreateView: "+userId );
        } catch (Exception e) {
            e.printStackTrace();
        }


        value = getArguments().getString("id");

        ConstantUtils.Temp.id = Integer.parseInt(value);
       // ConstantUtils.Temp.id = 3;

        Log.e("login_value", "onCreateView: "+ConstantUtils.Temp.id);
        Log.e("login_value", "onCreateView: "+value);

        FragmentManager fm = getChildFragmentManager();
        mainAdapter = new FragmentAdepter(fm, getLifecycle());
        pager2.setAdapter(mainAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("THIS QUARTER"));
        tabLayout.addTab(tabLayout.newTab().setText("LAST QUARTER"));
        tabLayout.addTab(tabLayout.newTab().setText("THIS MONTH"));
        tabLayout.addTab(tabLayout.newTab().setText("PREVIOUS MONTH"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });




        pwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_PW_RecordFragment);
            }
        });

        fcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_stockRecordsFragment);
            }
        });

        syncDataFragmentHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // deleteAllData();
                getAllDataFromServer();
            }
        });

        try {
            pwDataPush();
            stockDataPush("0");
        }catch (Exception e){}



        pushDataFragmentHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("push", "onClick: push" );

                if(!isInternetOn()){
                    Toast.makeText(getActivity(), "No Internet Connection.", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.show();
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                pwDataPush();
                stockDataPush("1");


            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Logout");
                builder.setMessage("Do You want to Logout?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        authPreference.setLoginStatus(0);
                        authPreference.setLoginStatusValue(false);
                        ConstantUtils.Temp.id = 0;
                        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment);
                        getActivity().finish();

                    }
                });
                builder.setNegativeButton("NO",null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        return view;
    }

    private void deleteAllData() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Database.getInstance(getContext()).getProviderDao().deletePoMappingThana();
                Database.getInstance(getContext()).getProviderDao().deleteProvider();
                Database.getInstance(getContext()).getProviderDao().deleteDivision();
                Database.getInstance(getContext()).getProviderDao().deleteDistrict();
                Database.getInstance(getContext()).getProviderDao().deleteThana();
                Database.getInstance(getContext()).getProviderDao().deleteBuyer();
                Database.getInstance(getContext()).getProviderDao().deleteHealthCondition();
                Database.getInstance(getContext()).getProviderDao().deletePwInfo("1");
                Database.getInstance(getContext()).getProviderDao().deleteStockInfo("1");
                Database.getInstance(getContext()).getProviderDao().deleteCityPouroshovaUnion();
                return null;
            }
        }.execute();
    }

    private void pwDataPush() {


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                List<PWInfoModel> pushPWData = Database.getInstance(getActivity()).getProviderDao().getAllPwInfoByFlag("0");
                Log.e("pushPWData", "pushAllData: "+pushPWData.size());
                try {


                    for(int i=0;i<pushPWData.size();i++){
                        final JsonObject jsonObject = new JsonObject();

                        jsonObject.addProperty("provider_id", pushPWData.get(i).getProviderId());
                        jsonObject.addProperty("name", pushPWData.get(i).getName());
                        Log.e("name", "onClick: "+pushPWData.get(i).getName());
                        jsonObject.addProperty("age", pushPWData.get(i).getAge());
                        jsonObject.addProperty("mobile_no", pushPWData.get(i).getMobileNo());
                        jsonObject.addProperty("division_id", pushPWData.get(i).getDivisionId());
                        jsonObject.addProperty("district_id", pushPWData.get(i).getDistrictId());
                        jsonObject.addProperty("thana_id", pushPWData.get(i).getThanaId());
                        jsonObject.addProperty("city_pouroshobha", pushPWData.get(i).getCityPouroshobha());
                        jsonObject.addProperty("city_pouroshobha_name", pushPWData.get(i).getCityPouroshobhaId());
                        jsonObject.addProperty("latitude", pushPWData.get(i).getLatitude());
                        jsonObject.addProperty("longitude", pushPWData.get(i).getLongitude());
                        jsonObject.addProperty("number_of_pregnancy", pushPWData.get(i).getNumberOfPregnancy());
                        jsonObject.addProperty("buyer_id", pushPWData.get(i).getBuyerId());
                        jsonObject.addProperty("lmp",  pushPWData.get(i).getLmp());
                        jsonObject.addProperty("edd",  pushPWData.get(i).getEdd());
                        jsonObject.addProperty("health_condition_id", pushPWData.get(i).getHealthConditionId());
                        jsonObject.addProperty("registration_date", pushPWData.get(i).getRegistrationDate());
                        jsonObject.addProperty("purchase_quantity", pushPWData.get(i).getPurchaseQuantity());
                        jsonObject.addProperty("is_urban", "urbanPosition");
                        jsonObject.addProperty("is_mob_personal", "25");
                        jsonObject.addProperty("comments", "gfsdg");
                        jsonObject.addProperty("action", "ADD-EDIT");
                        jsonObject.addProperty("user_id", pushPWData.get(i).getUserId());
                        jsonObject.addProperty("id", pushPWData.get(i).getUpdateID());

                        Log.e("pwJsonObject", "doInBackground: "+jsonObject.toString() );

                        long id = pushPWData.get(i).getId();

                        ServiceApi serviceApi1 = RetrofitInstance.retrofit.create(ServiceApi.class);
                        
                        serviceApi1.pushPwRegistration(jsonObject).enqueue(new Callback<PwRegistrationModel>() {
                            @Override
                            public void onResponse(Call<PwRegistrationModel> call, Response<PwRegistrationModel> response) {
                                Log.e("helloIsSuccessful", "onResponse: "+response.isSuccessful() );
                                if(response.isSuccessful()){
                                    Log.e("updatePwValue", "onResponse: "+id );
                                    updatePwValue(id);
                                }
                            }

                            @Override
                            public void onFailure(Call<PwRegistrationModel> call, Throwable t) {
                                //Log.e("pwPushError", "onFailure: "+t.getLocalizedMessage());
                                if(!isInternetOn()){
                                    Toast.makeText(getActivity(), "No Internet Connection.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        

                        // updateFlage(id);
                    }
                }catch (Exception e){}
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                //progressDialog.show();
            }
        }.execute();

    }

    private void updatePwValue(long id) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return Database.getInstance(getContext()).getProviderDao().updateAllPushByFlag("1",id);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
            }
        }.execute();
    }


    private void stockDataPush(String flag) {

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<StockInfoModel> pushStockData = Database.getInstance(getActivity()).getProviderDao().getAllStockInfoByFlag("0");
                    Log.e("pushStockData", "pushAllData: "+pushStockData.size());
                    try {



                        for(int i=0;i<pushStockData.size();i++){
                            final JsonObject jsonObject1 = new JsonObject();
                            jsonObject1.addProperty("action","ADD-EDIT");
                            jsonObject1.addProperty("provider_id",pushStockData.get(i).getPoMappingProviderID());
                            jsonObject1.addProperty("purchase_date",pushStockData.get(i).getLastPurchasedDay());
                            jsonObject1.addProperty("purchase_qty",pushStockData.get(i).getStockQuantity());
                            jsonObject1.addProperty("user_id",userId);
                            jsonObject1.addProperty("id",pushStockData.get(i).getUpdateId());

                            long id = pushStockData.get(i).getStockUpdateId();

                            Log.e("jsonObject1", "pushDataInServer: "+jsonObject1.toString() );

                            ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

                            serviceApi.addAllStockProvider(jsonObject1).enqueue(new Callback<StockAddProviderModel>() {
                                @Override
                                public void onResponse(Call<StockAddProviderModel> call, Response<StockAddProviderModel> response) {
                                    if(response.isSuccessful()){
                                        Log.e("msg", "onResponse: "+response.body().getPcsAddEdit().getMs() );
                                        updateFlage(id);
                                    }
                                }

                                @Override
                                public void onFailure(Call<StockAddProviderModel> call, Throwable t) {
                                    Log.e(":errorMsg", "onFailure: "+t.getLocalizedMessage() );
                                    if(!isInternetOn()){
                                        Toast.makeText(getActivity(), "No Internet Connection.", Toast.LENGTH_SHORT).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });

                           // updateFlage(id);
                        }
                    }catch (Exception e){}

                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);
                    if(flag.equals("1")){

                        Toast.makeText(getActivity(), "Push Data Successfully Done.", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }


                }
            }.execute();
        }catch (Exception e){}

    }


    private void updateFlage(long id) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return Database.getInstance(getContext()).getProviderDao().updateAllStockInfoByFlag("1",id);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
            }
        }.execute();
    }

    private void getAllDataFromServer() {

        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id",value);

        Log.e("reqObj", "onCreateView: "+jsonObject.toString() );

        serviceApi.getDataWebToApps(jsonObject).enqueue(new Callback<AllDataModelClass>() {
            @Override
            public void onResponse(Call<AllDataModelClass> call, Response<AllDataModelClass> response) {

                Log.e("poMappingSize", "onResponse: 0"+response.body().toString() );
                try {
                    if(response.isSuccessful()){
                        deleteAllData();

                      //  Toast.makeText(getContext(), "Hell", Toast.LENGTH_SHORT).show();

                        ///poMappingThana
                        for(int i= 0;i<response.body().getAllData().getPoMappingThanaList().size();i++){
                            int poMappingThanaModelID  = response.body().getAllData().getPoMappingThanaList().get(i).getId();
                            String poMappingThanaModelName  = response.body().getAllData().getPoMappingThanaList().get(i).getName();
                            poMappingThanaModel = new PoMappingThanaModel(poMappingThanaModelID,poMappingThanaModelName);
                            insertPoMappingThanaList(poMappingThanaModel);

                            Log.e("fdfd", "onResponse: "+poMappingThanaModelID+" "+poMappingThanaModelName);
                           // poMappingThanaModel = new PoMappingThanaModel(poMappingThanaModelID,poMappingThanaModelName);
                        }

                        ///Provider
                        for(int j=0;j<response.body().getAllData().getPoMappingProviderList().size();j++){
                            String poMappingThanaModelID  = response.body().getAllData().getPoMappingProviderList().get(j).getId();
                            String poMappingThanaModelName  = response.body().getAllData().getPoMappingProviderList().get(j).getName();
                            String poMappingProviderThanaID  = response.body().getAllData().getPoMappingProviderList().get(j).getThanaId();
                            poMappingProviderModel = new PoMappingProviderModel(poMappingThanaModelID,poMappingThanaModelName,value,poMappingProviderThanaID);
                            insertPoMappingProvider(poMappingProviderModel);

                            Log.e("fdfd", "onResponse: "+poMappingThanaModelID+" "+poMappingProviderModel);
                        }


                        ///Division
                        for(int j=0;j<response.body().getAllData().getDivision().size();j++) {
                            int divisionID  = response.body().getAllData().getDivision().get(j).getId();
                            String divisionName  = response.body().getAllData().getDivision().get(j).getName();
                            divisionModel = new DivisionModel(divisionID,divisionName);
                            insertDivision(divisionModel);

                            Log.e("fdfd", "onResponse: "+divisionID+" "+divisionName);
                        }


                        ///District
                        for(int j=0;j<response.body().getAllData().getDistrict().size();j++) {
                            String districtID  = response.body().getAllData().getDistrict().get(j).getId();
                            String districtName  = response.body().getAllData().getDistrict().get(j).getName();
                            String divisionId  = response.body().getAllData().getDistrict().get(j).getDivisionId();
                            districtModel = new DistrictModel(districtID,districtName,divisionId);
                            insertDistrict(districtModel);

                            Log.e("district", "onResponse: "+districtID+" "+districtName);
                        }


                        ///Thana
                        for(int j=0;j<response.body().getAllData().getThana().size();j++) {
                            String thanaID  = response.body().getAllData().getThana().get(j).getId();
                            String thanaName  =  response.body().getAllData().getThana().get(j).getName();
                            String divisionId  =  response.body().getAllData().getThana().get(j).getDivisionId();
                            String districtID  =  response.body().getAllData().getThana().get(j).getDistrictId();
                            thanaModel = new ThanaModel(thanaID,thanaName,divisionId,districtID);
                            insertThanaList(thanaModel);

                            Log.e("thanaID", "onResponse: "+thanaID+" "+thanaName);
                        }


                        ///cityPouroshovaUnion
                        for(int j=0;j<response.body().getAllData().getCityPouroshobaUnionList().size();j++) {
                            String cityID  = response.body().getAllData().getCityPouroshobaUnionList().get(j).getId();
                            String cityName  =  response.body().getAllData().getCityPouroshobaUnionList().get(j).getName();
                            String thanaId  =  response.body().getAllData().getCityPouroshobaUnionList().get(j).getThana();
                            String type  =  response.body().getAllData().getCityPouroshobaUnionList().get(j).getType();
                            cityPouroshovaUnionModel = new cityPouroshovaUnionModel(cityID,cityName,thanaId,type);
                            insertCityPouroshovaUnionList(cityPouroshovaUnionModel);

                            Log.e("cityPouroshovaUnion", "onResponse: "+type+" "+thanaId);
                        }


                        ///who is purchasing
                        for(int j=0;j<response.body().getAllData().getBuyer().size();j++) {
                            int buyerID  = response.body().getAllData().getBuyer().get(j).getId();
                            String buyerName  =  response.body().getAllData().getBuyer().get(j).getName();
                            buyerModel = new BuyerModel(buyerID,buyerName);
                            insertBuyerList(buyerModel);

                            Log.e("insertBuyerList", "onResponse: "+buyerID+" "+buyerName);
                        }


                        ///pwInfo
                        for(int i= 0;i<response.body().getAllData().getPwRegistrationList().size();i++){
                            String poMappingThanaId = response.body().getAllData().getPwRegistrationList().get(i).poMappingThanaId;
                            String poMappingThanaName = response.body().getAllData().getPwRegistrationList().get(i).poMappingThanaName;
                            String providerId = response.body().getAllData().getPwRegistrationList().get(i).providerId;
                            String providerName = response.body().getAllData().getPwRegistrationList().get(i).providerName;
                            String pwName = response.body().getAllData().getPwRegistrationList().get(i).pwName;
                            String age = response.body().getAllData().getPwRegistrationList().get(i).age;
                            String mobileNo = response.body().getAllData().getPwRegistrationList().get(i).mobileNo;
                            String divisionId = response.body().getAllData().getPwRegistrationList().get(i).divisionId;
                            String divisionName = response.body().getAllData().getPwRegistrationList().get(i).divisionName;
                            String districtId = response.body().getAllData().getPwRegistrationList().get(i).districtId;
                            String districtName = response.body().getAllData().getPwRegistrationList().get(i).districtName;
                            String thanaId = response.body().getAllData().getPwRegistrationList().get(i).thanaId;
                            String thanaName = response.body().getAllData().getPwRegistrationList().get(i).thanaName;
                            String cityPouroshobha = response.body().getAllData().getPwRegistrationList().get(i).cityPouroshobha;
                            String cityPouroshobhaName = response.body().getAllData().getPwRegistrationList().get(i).cityPouroshobhaName;
                            String cityPouroshobhaId = response.body().getAllData().getPwRegistrationList().get(i).cityPouroshobhaId;
                            String latitude = response.body().getAllData().getPwRegistrationList().get(i).latitude;
                            String longitude = response.body().getAllData().getPwRegistrationList().get(i).longitude;
                            String numberOfPregnancy = response.body().getAllData().getPwRegistrationList().get(i).numberOfPregnancy;
                            String buyerId = response.body().getAllData().getPwRegistrationList().get(i).buyerId;
                            String buyerName = response.body().getAllData().getPwRegistrationList().get(i).buyerName;
                            String lmp = response.body().getAllData().getPwRegistrationList().get(i).lmp;
                            String edd = response.body().getAllData().getPwRegistrationList().get(i).edd;
                            String healthConditionId = response.body().getAllData().getPwRegistrationList().get(i).healthConditionId;
                            String healthConditionName = response.body().getAllData().getPwRegistrationList().get(i).healthConditionName;
                            String registrationDate = response.body().getAllData().getPwRegistrationList().get(i).registrationDate;
                            String purchaseQuantity = response.body().getAllData().getPwRegistrationList().get(i).purchaseQuantity;
                            String pwId = response.body().getAllData().getPwRegistrationList().get(i).pwId;

                            insertPwInfoModelList(poMappingThanaId,poMappingThanaName,providerId,providerName,pwName,age,mobileNo,divisionId,divisionName,
                                    districtId,districtName,thanaId,thanaName,cityPouroshobha,cityPouroshobhaName,cityPouroshobhaId,latitude,longitude,numberOfPregnancy,buyerId,
                                    buyerName,lmp,edd,healthConditionId,healthConditionName,registrationDate,purchaseQuantity,pwId);


                        }

                        ///StockInfo
                        for(int i=0;i<response.body().getAllData().getStockList().size();i++){
                            String poMappingThanaId = response.body().getAllData().getStockList().get(i).getPoMappingThanaId();
                            String poMappingThanaName = response.body().getAllData().getStockList().get(i).getPoMappingThanaName();
                            String providerId = response.body().getAllData().getStockList().get(i).getProviderId();
                            String providerName = response.body().getAllData().getStockList().get(i).getProviderName();
                            String purchaseQty = response.body().getAllData().getStockList().get(i).getPurchaseQty();
                            String purchaseDay = response.body().getAllData().getStockList().get(i).getPurchaseDay();
                            String date = response.body().getAllData().getStockList().get(i).getCreateDate();
                            String stockId = response.body().getAllData().getStockList().get(i).getStockId();
                            Log.e("i_value", "onResponse: "+i);

                            insertStockInfo(poMappingThanaId,poMappingThanaName,providerId,providerName,purchaseQty,purchaseDay,date,stockId);
                        }

                        ///health Condition
                        for(int j=0;j<response.body().getAllData().getHealthCondition().size();j++) {
                            int healthID  = response.body().getAllData().getHealthCondition().get(j).getId();
                            String healthName  =  response.body().getAllData().getHealthCondition().get(j).getName();
                            healthConditionModel = new HealthConditionModel(healthID,healthName);
                            insertHealthConditionModelList(healthConditionModel);

                            Log.e("insertBuyerList", "onResponse: "+healthID+" "+healthName);
                        }



                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<AllDataModelClass> call, Throwable t) {
                if(!isInternetOn()){
                    Toast.makeText(getActivity(), "No Internet Connection.", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

    }

    private void insertStockInfo(String poMappingThanaId, String poMappingThanaName, String providerId, String providerName, String purchaseQty, String purchaseDay, String date, String stockId) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                StockInfoModel stockInfoModel = new StockInfoModel(poMappingThanaId,poMappingThanaName,providerId,providerName,purchaseQty,purchaseDay,date,"1",stockId);
                return Database.getInstance(getContext()).getProviderDao().saveStockData(stockInfoModel);

            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);

            }
        }.execute();
    }

    private void insertPwInfoModelList(String poMappingThanaId,String poMappingThanaName, String providerId, String providerName, String pwName, String age, String mobileNo, String divisionId, String divisionName, String districtId, String districtName, String thanaId, String thanaName, String cityPouroshobha, String cityPouroshobhaName, String cityPouroshobhaId, String latitude, String longitude, String numberOfPregnancy, String buyerId, String buyerName, String lmp, String edd, String healthConditionId, String healthConditionName, String registrationDate, String purchaseQuantity, String pwId) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                PWInfoModel pwInfoModel = new PWInfoModel(ConstantUtils.Temp.id,poMappingThanaId,poMappingThanaName,providerId,providerName,pwName,age,mobileNo,divisionId,divisionName,
                        districtId,districtName,thanaId,thanaName,cityPouroshobha,cityPouroshobhaName,cityPouroshobhaId,latitude,longitude,numberOfPregnancy,buyerId,
                        buyerName,lmp,edd,healthConditionId,healthConditionName,registrationDate,purchaseQuantity,"1",pwId);


                return Database.getInstance(getContext()).getProviderDao().savePWInfoData(pwInfoModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);

            }
        }.execute();
    }

    private void insertHealthConditionModelList(HealthConditionModel healthConditionModel) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return Database.getInstance(getContext()).getProviderDao().insertHealthCondition(healthConditionModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toast.makeText(getContext(), "Pull Data Successfully Done.", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }.execute();
    }

    private void insertBuyerList(BuyerModel buyerModel) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return Database.getInstance(getContext()).getProviderDao().insertBuyer(buyerModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
               // Toast.makeText(getContext(), "insertBuyerList", Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }

    private void insertCityPouroshovaUnionList(cityPouroshovaUnionModel cityPouroshovaUnionModel) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return Database.getInstance(getContext()).getProviderDao().insertCityPouroshovaUnion(cityPouroshovaUnionModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
               // Toast.makeText(getContext(), "insertCityPouroshovaUnionList", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertThanaList(ThanaModel thanaModel) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return Database.getInstance(getContext()).getProviderDao().insertThana(thanaModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
               // Toast.makeText(getContext(), "ThanaModel", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertDistrict(DistrictModel districtModel) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return Database.getInstance(getContext()).getProviderDao().insertDistrict(districtModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                //Toast.makeText(getContext(), "districtModel", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertDivision(DivisionModel divisionModel) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return Database.getInstance(getContext()).getProviderDao().insertDivision(divisionModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                //Toast.makeText(getContext(), "divisionModel", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertPoMappingProvider(PoMappingProviderModel poMappingProviderModel) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return Database.getInstance(getContext()).getProviderDao().insertProvider(poMappingProviderModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                //Toast.makeText(getContext(), "pp", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertPoMappingThanaList(PoMappingThanaModel poMappingThanaModel) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //

                return Database.getInstance(getContext()).getProviderDao().insertPoMappingThana(poMappingThanaModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                //Toast.makeText(getContext(), "bbby", Toast.LENGTH_SHORT).show();
            }
        }.execute();


    }

    public final boolean isInternetOn() {
        ConnectivityManager connec =  (ConnectivityManager)getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) {

            return true;
        } else if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ) {

            return false;
        }
        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }



}