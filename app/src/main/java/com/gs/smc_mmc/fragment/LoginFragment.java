package com.gs.smc_mmc.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.JsonObject;
import com.gs.smc_mmc.MyService;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.AllDataModelClass;
import com.gs.smc_mmc.model.LoginModel;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {


    private AuthPreference authPreference;
    private Button loginBtn;
    private EditText userET,passET;
    private ProgressBar progressBar;
    private int progress;
    private ProgressDialog progressDialog;
    private PoMappingThanaModel poMappingThanaModel;
    private PoMappingProviderModel poMappingProviderModel;
    private DivisionModel divisionModel;
    private DistrictModel districtModel;
    private ThanaModel thanaModel;
    private cityPouroshovaUnionModel cityPouroshovaUnionModel;
    private BuyerModel buyerModel;
    private HealthConditionModel healthConditionModel;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        NavController navCo = navHostFragment.getNavController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginBtn =  view.findViewById(R.id.loginBtn);
        userET = view.findViewById(R.id.useridEdt);
        passET = view.findViewById(R.id.passwordEdt);
        progressBar = view.findViewById(R.id.progressBarID);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");


        authPreference = new AuthPreference(getContext());
        if(authPreference.getStatusValue()){


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    doWork();
                }
            });
            thread.start();
            progressBar.setVisibility(View.VISIBLE);
          //  Toast.makeText(getContext(), ""+authPreference.isAdminLoggedIn(), Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putString("id",String.valueOf(authPreference.isAdminLoggedIn()));
            Log.e("bundle", "onCreateView: "+bundle);


            //Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment,bundle);
            Navigation.findNavController(getActivity().findViewById(R.id.fragmentContainerView)).navigate(R.id.action_loginFragment_to_homeFragment,bundle);

        }


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Hello_Login", "onPostExecute: Hello Login btn" );
                loginBtn.setClickable(false);
                progressDialog.show();
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);

                if(!authPreference.getStatusValue()){
                    loginBtn.setClickable(true);
                }

                String user = userET.getText().toString();
                String password = passET.getText().toString();

           //     Toast.makeText(getActivity(), "aaaaaaaa", Toast.LENGTH_SHORT).show();

                if(user.isEmpty()){
                    userET.setError("Enter your username");
                  //  userET.requestFocus();
                }if(password.isEmpty()){
                    passET.setError("Enter your password");
                //    passET.requestFocus();
                }if(user!=null && password!=null){
                    final JsonObject jsonObject = new JsonObject();

                    Log.e("user", "onClick: "+user );
                    Log.e("user", "onClick: "+password );
                    jsonObject.addProperty("username",user);
                    jsonObject.addProperty("password",password);
                    Log.e("res", "req: "+jsonObject.toString());

                    if(!isInternetOn()){
                        progressDialog.setCanceledOnTouchOutside(true);
                        progressDialog.setCancelable(true);
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "No Internet Connection.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);


                    serviceApi.userServiceLoginApi(jsonObject).enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                            // Toast.makeText(getActivity(), "abc", Toast.LENGTH_SHORT).show();

              //              Log.e("response_id", "onClick: "+response.body().getUserInfo().getData().getId());

                            if(response.isSuccessful()){
                                loginBtn.setClickable(true);

                                try {
                                    Log.e("response", "onClick: "+response.body().toString());
                                    Log.e("Hello_Login", "onPostExecute: Hello serviceApi" );
                                    getAllDataFromServer(response.body().getUserInfo().getData().getId());
                                }catch (Exception e){}




                               // Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                               // Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
                                if(response.body().getUserInfo().getStatus() == 1){
                                    try {

                                        Thread thread = new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                //doWork();
                                            }
                                        });
                                        thread.start();
                                       // progressBar.setVisibility(View.VISIBLE);
                                        String id = response.body().getUserInfo().getData().getId();
                                        authPreference.setLoginStatus(Integer.parseInt(id));
                                        authPreference.setLoginStatusValue(true);

                                        Bundle bundle = new Bundle();
                                        bundle.putString("id",id);
                                        authPreference.setLoginStatus(Integer.parseInt(id));
                                      //  Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                                       // Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment,bundle);
                                        //Navigation.findNavController(view).popBackStack();
                                        ConstantUtils.Temp.id = Integer.parseInt(id);
                                        Log.e("id", "onResponse: "+id );

                                        userET.setText("");
                                        passET.setText("");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }else {
                                    loginBtn.setClickable(true);
                                    Toast.makeText(getActivity(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();

                                }
                                //  Log.e("ff", "onResponse: "+response.body().getUserInfo().get(0).getStatus());
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {
                            Log.e("f", "onFailure: "+t.getLocalizedMessage() );
                            progressDialog.dismiss();
                           // Toast.makeText(getActivity(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();

                        }
                    });

                }




               // Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);



            }
        });

        return view;
    }

    private void getAllDataFromServer(String value) {

        //progressDialog.show();
      //  progressDialog.setCancelable(false);
      //  progressDialog.setCanceledOnTouchOutside(false);
        ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id",value);

        Log.e("reqObj", "onCreateView: "+jsonObject.toString() );
        Log.e("reqObj", "onCreateView: "+value );

        serviceApi.getDataWebToApps(jsonObject).enqueue(new Callback<AllDataModelClass>() {
            @Override
            public void onResponse(Call<AllDataModelClass> call, Response<AllDataModelClass> response) {

                Log.e("poMappingSize", "onResponse: 0"+response.body().toString() );
                Log.e("Hello_Login", "onPostExecute: Hello getAll data from server" );
                try {
                    if(response.isSuccessful()){

                        locationSetting();


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
                            insertHealthConditionModelList(healthConditionModel,value);


                        }

                        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<AllDataModelClass> call, Throwable t) {
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.setCancelable(true);
                progressDialog.dismiss();
                Toast.makeText(getContext(), "No Internet connection.", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void locationSetting() {













    }

    private void insertHealthConditionModelList(HealthConditionModel healthConditionModel,String value) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return Database.getInstance(getContext()).getProviderDao().insertHealthCondition(healthConditionModel);
            }

            @Override
            protected void onPostExecute(Long aLong) {
               // Toast.makeText(getContext(), "Pull Data Successfully Done.", Toast.LENGTH_SHORT).show();
                try {
                    progressDialog.dismiss();

                    Log.e("id_value", "onPostExecute: "+value );
                    Bundle bundle = new Bundle();
                    bundle.putString("id",value);
                    Log.e("Hello_Login", "onPostExecute: Hello Login" );
                    Navigation.findNavController(getActivity().findViewById(R.id.fragmentContainerView)).navigate(R.id.homeFragment,bundle);


                }catch (Exception e){}



            }
        }.execute();
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

    private void insertPwInfoModelList(String poMappingThanaId, String poMappingThanaName, String providerId, String providerName, String pwName, String age, String mobileNo, String divisionId, String divisionName, String districtId, String districtName, String thanaId, String thanaName, String cityPouroshobha, String cityPouroshobhaName, String cityPouroshobhaId, String latitude, String longitude, String numberOfPregnancy, String buyerId, String buyerName, String lmp, String edd, String healthConditionId, String healthConditionName, String registrationDate, String purchaseQuantity, String pwId) {

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


    private void doWork() {

        for(progress=100;progress<=1000;progress+=100){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
}