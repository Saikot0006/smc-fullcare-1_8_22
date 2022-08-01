package com.gs.smc_mmc.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.adepter.SR_Adepter;
import com.gs.smc_mmc.model.StockListModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PWInfoModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.adepter.PW_Record_Adepter;
import com.gs.smc_mmc.model.PW_RecordModel;
import com.gs.smc_mmc.model.PatientModel;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class PW_RecordFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<PW_RecordModel> pwList;
    private PW_Record_Adepter pw__record_adepter;
    private LinearLayout linearLayout,linearLayout2;
    private int userId = 0;
    private String start_date = "0000-00-00";
    private String end_date = "0000-00-00";
    private int start_age = -1;
    private int end_age = -1;
    List<PWInfoModel> pwInfoModelList;


    public PW_RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_w__record, container, false);



        recyclerView = view.findViewById(R.id.pwRecordID);

        linearLayout = view.findViewById(R.id.filterID);
        linearLayout2 = view.findViewById(R.id.newRegID);

        try {
            userId = ConstantUtils.Temp.id;
            Log.e("userId", "onCreateView: "+userId );
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("loginID", "onCreateView: "+start_date);


        ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        try {
            start_date = getArguments().getString("start_date","0000-00-00");
            end_date = getArguments().getString("end_date","0000-00-00");
            start_age = getArguments().getInt("start_age",-1);
            end_age = getArguments().getInt("end_age",-1);

            linearLayout2.setVisibility(View.GONE);

            Log.e("start_date", "onCreateView: "+start_date );
            Log.e("start_date", "onCreateView: "+end_date );
            Log.e("start_date", "onCreateView: "+start_age );
            Log.e("start_date", "onCreateView: "+end_age );

             if(start_date!="0000-00-00" && end_date != "0000-00-00" &&
                    start_age > -1 && end_age > -1){



                /*final JsonObject jsonObject4 = new JsonObject();
                jsonObject4.addProperty("user_id",userId);
                jsonObject4.addProperty("registration_start_date",start_date);
                jsonObject4.addProperty("registration_end_date",end_date);
                jsonObject4.addProperty("age_start",start_age);
                jsonObject4.addProperty("age_end",end_age);

                Log.e("jsonObject3", "onCreateView: "+jsonObject4.toString() );*/

                 new AsyncTask<Void, Void, Void>() {
                     @Override
                     protected Void doInBackground(Void... voids) {
                         List<PWInfoModel> PWInfoModelList = Database.getInstance(getContext()).getProviderDao()
                                 .getAllPWInfoByAgeAndDate(start_date,end_date,start_age,end_age);
                         Log.e("DateAndAge", "doInBackground: "+PWInfoModelList );
                         try {

                             //   Log.e("success1", "onResponse: "+jsonObject1.toString() );
                             //  Log.e("success1", "onResponse: "+jsonObject1.toString() );
                             pw__record_adepter = new PW_Record_Adepter(getActivity(),PWInfoModelList);

                             LinearLayoutManager llm = new LinearLayoutManager(getContext());
                             //   Log.i("TAG", "onResponse: "+ new Gson().toJson(response.body().getPcsList()));
                             recyclerView.setLayoutManager(llm);
                             recyclerView.setAdapter(pw__record_adepter);


                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                         return null;
                     }

                     @Override
                     protected void onPostExecute(Void unused) {
                         super.onPostExecute(unused);
                     }
                 }.execute();

               /* serviceApi.setAllPatientWomen(jsonObject4).enqueue(new Callback<PatientModel>() {
                    @Override
                    public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                        try {
                            if(response.isSuccessful()){
                               *//* pw__record_adepter = new PW_Record_Adepter(getActivity(),response.body().getPwList());
                                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(llm);
                                recyclerView.setAdapter(pw__record_adepter);*//*
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PatientModel> call, Throwable t) {

                    }
                });*/
            }

             /*if(start_date!="0000-00-00" && end_date != "0000-00-00"){
                Log.e("start_age", "onCreateView: "+start_date );
                Log.e("start_age", "onCreateView: "+end_date );

                final JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("user_id",userId);
                //  jsonObject1.addProperty("age_start",start_age);
                //  jsonObject1.addProperty("age_end",end_age);
                jsonObject1.addProperty("registration_start_date",start_date);
                jsonObject1.addProperty("registration_end_date",end_date);

                Log.e("pwJsonObject", "onCreateView: "+jsonObject1.toString() );

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        List<PWInfoModel> PWInfoModelList = Database.getInstance(getContext()).getProviderDao().getAllPWInfoByDate(start_date,end_date);
                        Log.e("PWInfoModelListDate", "doInBackground: "+PWInfoModelList );
                        try {

                            //   Log.e("success1", "onResponse: "+jsonObject1.toString() );
                            //  Log.e("success1", "onResponse: "+jsonObject1.toString() );
                            pw__record_adepter = new PW_Record_Adepter(getActivity(),PWInfoModelList);

                            LinearLayoutManager llm = new LinearLayoutManager(getContext());
                            //   Log.i("TAG", "onResponse: "+ new Gson().toJson(response.body().getPcsList()));
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter(pw__record_adepter);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);
                    }
                }.execute();

               *//* serviceApi.setAllPatientWomen(jsonObject1).enqueue(new Callback<PatientModel>() {
                    @Override
                    public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                        try {
                            if(response.isSuccessful()){
                                pw__record_adepter = new PW_Record_Adepter(getActivity(),response.body().getPwList());
                                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(llm);
                                recyclerView.setAdapter(pw__record_adepter);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PatientModel> call, Throwable t) {

                    }
                });*//*

            }*/

          /*  if(start_age > -1 && end_age > -1){
                final JsonObject jsonObject3 = new JsonObject();
                jsonObject3.addProperty("user_id",userId);
                jsonObject3.addProperty("age_start",start_age);
                jsonObject3.addProperty("age_end",end_age);

                Log.e("jsonObject3Age", "onCreateView: "+start_age );
                Log.e("jsonObject3Age", "onCreateView: "+end_age );

                Log.e("jsonObject3", "onCreateView: "+jsonObject3.toString() );

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        List<PWInfoModel> PWInfoModelList = Database.getInstance(getContext()).getProviderDao().getAllPWInfoByAge(start_age,end_age);
                        Log.e("PWInfoModelListDate", "doInBackground: "+PWInfoModelList );
                        try {

                            //   Log.e("success1", "onResponse: "+jsonObject1.toString() );
                            //  Log.e("success1", "onResponse: "+jsonObject1.toString() );
                            pw__record_adepter = new PW_Record_Adepter(getActivity(),PWInfoModelList);

                            LinearLayoutManager llm = new LinearLayoutManager(getContext());
                            //   Log.i("TAG", "onResponse: "+ new Gson().toJson(response.body().getPcsList()));
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter(pw__record_adepter);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);
                    }
                }.execute();

               *//* serviceApi.setAllPatientWomen(jsonObject3).enqueue(new Callback<PatientModel>() {
                    @Override
                    public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                        try {
                            if(response.isSuccessful()){
                                pw__record_adepter = new PW_Record_Adepter(getActivity(),response.body().getPwList());
                                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(llm);
                                recyclerView.setAdapter(pw__record_adepter);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PatientModel> call, Throwable t) {

                    }
                });*//*

            }*/

            /*if(start_date!="0000-00-00" && end_date != "0000-00-00"){
                Log.e("start_age", "onCreateView: "+start_date );
                Log.e("start_age", "onCreateView: "+end_date );

                final JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("user_id",userId);
              //  jsonObject1.addProperty("age_start",start_age);
              //  jsonObject1.addProperty("age_end",end_age);
                jsonObject1.addProperty("registration_start_date",start_date);
                jsonObject1.addProperty("registration_end_date",end_date);

                Log.e("pwJsonObject", "onCreateView: "+jsonObject1.toString() );

                serviceApi.setAllPatientWomen(jsonObject1).enqueue(new Callback<PatientModel>() {
                    @Override
                    public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                        try {
                            if(response.isSuccessful()){
                                pw__record_adepter = new PW_Record_Adepter(getActivity(),response.body().getPwList());
                                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(llm);
                                recyclerView.setAdapter(pw__record_adepter);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PatientModel> call, Throwable t) {

                    }
                });

            }*/





        }catch (Exception e){}





        try {
            if(start_date=="0000-00-00" && end_date == "0000-00-00" && start_age == -1 && end_age == -1){
                Log.e("start_date", "onCreateView: "+start_date );

             //   final JsonObject jsonObject = new JsonObject();
              //  jsonObject.addProperty("user_id",userId);

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {


                        try {

                            pwInfoModelList = Database.getInstance(getContext()).getProviderDao().getAllPWInfo();
                        }catch (Exception e){}

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);
                        try {
                            pw__record_adepter = new PW_Record_Adepter(getActivity(),pwInfoModelList);
                            LinearLayoutManager llm = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter(pw__record_adepter);
                            Log.e("pwInfoModelList", "onCreateView: "+pwInfoModelList.size() );
                            Log.e("pwInfoModelList", "onCreateView: "+pwInfoModelList.get(0).getName() );
                        }catch (Exception e){}


                    }
                }.execute();





                /*serviceApi.setAllPatientWomen(jsonObject).enqueue(new Callback<PatientModel>() {
                    @Override
                    public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                        try {
                            if(response.isSuccessful()){
                                pw__record_adepter = new PW_Record_Adepter(getActivity(),response.body().getPwList());
                                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(llm);
                                recyclerView.setAdapter(pw__record_adepter);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PatientModel> call, Throwable t) {

                    }
                });*/

            }

        }catch (Exception e){}


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_PW_RecordFragment_to_filterFragment);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",0);
                Navigation.findNavController(view).navigate(R.id.action_PW_RecordFragment_to_PW_RegistrationFragment,bundle);
            }
        });




        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
}