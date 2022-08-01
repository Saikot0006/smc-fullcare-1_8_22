package com.gs.smc_mmc.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.StockListModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.adepter.SR_Adepter;


import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class stockRecordsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SR_Adepter sr_adepter;
    private LinearLayout l1,l2;
    int v = 0;
    private String start_date = "00-00-0000";
    private String end_date = "00-00-0000";
    List<StockListModel> stockList;

    public stockRecordsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stock_records, container, false);

        recyclerView = view.findViewById(R.id.srRecordID);

        l1 = view.findViewById(R.id.updateStockFilter);
        l2 = view.findViewById(R.id.updateStockID);
        v = ConstantUtils.Temp.id;
        Log.e("loginID", "onCreateView: "+v );
        Log.e("loginID", "onCreateView: "+start_date);

        ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        try {
            start_date = getArguments().getString("start_date","00-00-0000");
            end_date = getArguments().getString("end_date","00-00-0000");

            Log.e("start_date__end_date", "onCreateView: "+start_date+" "+end_date);

            if(start_date!="00-00-0000" && end_date != "00-00-0000"){




              l2.setVisibility(View.GONE);

         //   final JsonObject jsonObject = new JsonObject();
         //   jsonObject.addProperty("user_id",v);
         //   jsonObject.addProperty("start_date",start_date);
          //  jsonObject.addProperty("end_date",end_date);
            //    Log.e("jsonObject", "onCreateView: "+jsonObject.toString() );

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        List<StockInfoModel> stockInfoModelList = Database.getInstance(getContext()).getProviderDao().getAllStockInfobyDate(start_date,end_date);
                        Log.e("stockInfoModelList", "doInBackground: "+stockInfoModelList );
                        Log.e("stockInfoModelList", "doInBackground: "+start_date+" "+end_date );
                        try {

                            //   Log.e("success1", "onResponse: "+jsonObject1.toString() );
                            //  Log.e("success1", "onResponse: "+jsonObject1.toString() );
                            sr_adepter = new SR_Adepter(getActivity(),stockInfoModelList);

                            LinearLayoutManager llm = new LinearLayoutManager(getContext());
                            //   Log.i("TAG", "onResponse: "+ new Gson().toJson(response.body().getPcsList()));
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter(sr_adepter);


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

         /*   serviceApi.getAllStockList(jsonObject).enqueue(new Callback<StockListModel>() {
                @Override
                public void onResponse(Call<StockListModel> call, Response<StockListModel> response) {
                       Log.e("success0", "onResponse: "+jsonObject.toString() );

                    if(response.isSuccessful()){

                        try {
                            if(response.body().getPcsList().size()>0){
                                   Log.e("success1", "onResponse: "+jsonObject.toString() );

                              //  sr_adepter = new SR_Adepter(getActivity(),response.body().getPcsList());
                              //  LinearLayoutManager llm = new LinearLayoutManager(getContext());
                                sr_adepter = new SR_Adepter(getActivity(),response.body().getPcsList());
                                recyclerView.setAdapter(sr_adepter);

                                recyclerView = view.findViewById(R.id.srRecordID);
                                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(llm);


                               *//* recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                sr_adepter = new SR_Adepter( getActivity() ,response.body().getPcsList());*//*
                             //   recyclerView.setLayoutManager(llm);
                                recyclerView.setAdapter(sr_adepter);
                                   Log.i("TAG", "onResponse: "+ new Gson().toJson(response.body().getPcsList()));


                              //  recyclerView.setAdapter(sr_adepter);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }

                @Override
                public void onFailure(Call<StockListModel> call, Throwable t) {

                }
            });*/
        }




    }catch (Exception e){}





        try {
           if(start_date =="00-00-0000" && end_date =="00-00-0000"){
              /* ServiceApi serviceApi1 = RetrofitInstance.retrofit.create(ServiceApi.class);
               Log.e("start_date", "onCreateView: "+start_date );
               Log.e("start_date", "onCreateView: "+end_date );
               final JsonObject jsonObject1 = new JsonObject();
               jsonObject1.addProperty("user_id",v);*/


               new AsyncTask<Void, Void, Void>() {
                   @Override
                   protected Void doInBackground(Void... voids) {
                       List<StockInfoModel> stockInfoModelList = Database.getInstance(getContext()).getProviderDao().getAllStockInfo();
                       try {

                               //   Log.e("success1", "onResponse: "+jsonObject1.toString() );
                               //  Log.e("success1", "onResponse: "+jsonObject1.toString() );
                               sr_adepter = new SR_Adepter(getActivity(),stockInfoModelList);

                               LinearLayoutManager llm = new LinearLayoutManager(getContext());
                               //   Log.i("TAG", "onResponse: "+ new Gson().toJson(response.body().getPcsList()));
                               recyclerView.setLayoutManager(llm);
                               recyclerView.setAdapter(sr_adepter);


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

              /* serviceApi1.getAllStockList(jsonObject1).enqueue(new Callback<StockListModel>() {
                   @Override
                   public void onResponse(Call<StockListModel> call, Response<StockListModel> response) {
                      // Log.e("success0", "onResponse: "+jsonObject1.toString() );
                     //  Log.e("success0", "onResponse: "+jsonObject1.toString() );

                   }

                   @Override
                   public void onFailure(Call<StockListModel> call, Throwable t) {
                       Log.e("failed", "onResponse: "+t.getLocalizedMessage());
                   }
               });*/
           }
        }catch (Exception e){}





       /* try {

            ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

            v = ConstantUtils.Temp.id;
             start_date = getArguments().getString("start_date","00-00-0000");
             end_date = getArguments().getString("end_date","00-00-0000");

            Log.e("start_date", "onCreateView: "+start_date);
            Log.e("start_date", "onCreateView: "+end_date);
            Log.e("loginID", "onCreateView: "+v );

        *//*    if(start_date!="00-00-0000" && end_date != "00-00-0000"){
              //  Log.e("start_date", "onCreateView: "+start_date );

                final JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("user_id",v);
                jsonObject.addProperty("start_date",start_date);
                jsonObject.addProperty("end_date",end_date);

                serviceApi.getAllStockList(jsonObject).enqueue(new Callback<StockListModel>() {
                    @Override
                    public void onResponse(Call<StockListModel> call, Response<StockListModel> response) {
                        if(response.isSuccessful()){
                            sr_adepter = new SR_Adepter(getActivity(),response.body().getPcsList());
                            LinearLayoutManager llm = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter(sr_adepter);

                        }
                    }

                    @Override
                    public void onFailure(Call<StockListModel> call, Throwable t) {

                    }
                });
            }*//*


        } catch (Exception e) {
            e.printStackTrace();
        }
*/











        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_stockRecordsFragment_to_stockFilterFragment);

            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_stockRecordsFragment_to_stockUpdateFragment);

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