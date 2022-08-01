
package com.gs.smc_mmc.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.PoProviderModel;
import com.gs.smc_mmc.model.StockAddProviderModel;
import com.gs.smc_mmc.model.StockPoMappingListModel;
import com.gs.smc_mmc.model.StockProviderEditModel;
import com.gs.smc_mmc.model.StockProviderListModel;
import com.gs.smc_mmc.model.StockProviderViewModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PoMappingProviderModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PoMappingThanaModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class stockUpdateFragment extends Fragment {

    private Button saveBtn;
    private Spinner providerTypeSP;
    private Spinner daySP;
    private EditText nameET,providerIDET,stockQuantityET,daysET;
    private ArrayList<String> providerNameList = new ArrayList<>();
    private ArrayList<Integer> providerIdList = new ArrayList<>();
    private ArrayList<String> providerThanaAreaNameList = new ArrayList<>();
    private ArrayList<Integer> providerThanaAreaIdList = new ArrayList<>();
    private HashMap<String,String> providerNameHasList = new HashMap<>() ;
    private ArrayList<HashMap<String,String>> hasmap = new ArrayList<HashMap<String,String>>();
    private AutoCompleteTextView actvProviderTypeStockFragment;
    StockInfoModel stockInfoModel = null;

    private String providerId,providerName,providerThanaAreaId,providerThanaAreaName;
    String date = "0",flag="0";
    private int sid = 0;
    private int userID = 0;
    private int value = 1;
    String qty,days,pn;


        // private String id = "0";



    public stockUpdateFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stock_update, container, false);


        userID = ConstantUtils.Temp.id;

        saveBtn  = view.findViewById(R.id.providerSaveBtnID);
     //   nameET = view.findViewById(R.id.providerNameET);
      //  providerIDET = view.findViewById(R.id.providerIDET);
        stockQuantityET = view.findViewById(R.id.providerStockQuantityET);
        daysET = view.findViewById(R.id.providerDaysET);
       // providerTypeSP = view.findViewById(R.id.providerTypeSP);
        providerTypeSP = view.findViewById(R.id.providerThanaTypeSP);
        actvProviderTypeStockFragment = view.findViewById(R.id.actvProviderTypeStockFragment);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

     //   date = day+"-"+(month+1)+"-"+year;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = simpleDateFormat.format(new Date());

        Log.e("Calenderdate", "onCreateView: "+date );


        actvProviderTypeStockFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvProviderTypeStockFragment.showDropDown();
            }
        });

        try{
            sid = getArguments().getInt("sId",-1);
            Log.e("balsid", "onCreateView: "+sid );

        }catch (Exception e){}


       // ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        if(sid > 0){

            try {

                final JsonObject jsonObjectView = new JsonObject();
                jsonObjectView.addProperty("id",sid);
                jsonObjectView.addProperty("user_id",sid);
                //  jsonObjectView.addProperty("action","VIEW");
                Log.e("editStock", "onCreateView: "+jsonObjectView.toString()) ;
                Log.e("sid", "onCreateView: "+sid) ;

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        Log.e("detailsID", "doInBackground: "+sid );
                        List<StockInfoModel> stockInfoModelList = Database.getInstance(getContext()).getProviderDao().getSingleStockInfo(""+sid);


                        stockQuantityET.setText(stockInfoModelList.get(0).getStockQuantity());
                        daysET.setText(stockInfoModelList.get(0).getLastPurchasedDay());

                        try{
                            providerNameList.clear();
                            providerIdList.clear();
                            providerThanaAreaNameList.clear();
                            providerThanaAreaIdList.clear();

                            providerThanaAreaId = stockInfoModelList.get(0).getPoMappingProviderThanaID();
                            providerThanaAreaName = stockInfoModelList.get(0).getPoMappingProviderThanaName();
                            providerId = stockInfoModelList.get(0).getPoMappingProviderID();
                            providerName = stockInfoModelList.get(0).getPoMappingProviderName();
                            Log.e("providerThanaAreaId", "onResponse: "+providerThanaAreaName );

                            setProviderSpinner();
                            //setProviderList();

                            // Log.e("providerName", "onResponse: "+providerTypeSP.getSelectedItemPosition());
                            // Log.e("providerName", "onResponse: "+providerName);
                        }catch (Exception e){}
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);
                    }
                }.execute();


              /*  serviceApi.editStockProvider(jsonObjectView).enqueue(new Callback<StockProviderEditModel>() {
                    @Override
                    public void onResponse(Call<StockProviderEditModel> call, Response<StockProviderEditModel> response) {
                        stockQuantityET.setText(response.body().getPcsEdit().getInfo().getQty());
                        daysET.setText(response.body().getPcsEdit().getInfo().getDate());

                        qty = response.body().getPcsEdit().getInfo().getQty();
                        days = response.body().getPcsEdit().getInfo().getDate();

                        try{
                            providerNameList.clear();
                            providerIdList.clear();
                            providerThanaAreaNameList.clear();
                            providerThanaAreaIdList.clear();
                            providerId = response.body().getPcsEdit().getInfo().getProviderId();
                            providerThanaAreaId = response.body().getPcsEdit().getInfo().getProviderMappingThanaId();
                            Log.e("providerThanaAreaId", "onResponse: "+providerThanaAreaId );

                            setProviderSpinner();
                            //setProviderList();

                            // Log.e("providerName", "onResponse: "+providerTypeSP.getSelectedItemPosition());
                            // Log.e("providerName", "onResponse: "+providerName);
                        }catch (Exception e){}
                    }

                    @Override
                    public void onFailure(Call<StockProviderEditModel> call, Throwable t) {

                    }
                });*/

            }catch (Exception e){}





        }else {

        }

        ///providerTypeSP

        if(sid==0){
            setProviderSpinner();
            //setProviderList();
        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{


                    String sQty = stockQuantityET.getText().toString();
                    String days = daysET.getText().toString();
                    String nameStock = actvProviderTypeStockFragment.getText().toString();
                    Log.e("name", "onClick: "+nameStock );

                    if(nameStock.isEmpty()){
                        Toast.makeText(getActivity(), "Select provider", Toast.LENGTH_SHORT).show();
                        //  Log.e("ff", "onClick: "+providerId );
                        return;
                    }
                    if(sQty.isEmpty()){
                        stockQuantityET.setError("Enter number of quantity");
                        stockQuantityET.requestFocus();
                        return;
                    }

                    int s = Integer.parseInt(sQty);
                    if(s==0){
                        stockQuantityET.setError("Enter Stock quantity greater than zero");
                        stockQuantityET.requestFocus();
                        return;
                    }


                    if(days.isEmpty()){
                        daysET.setError("Enter days");
                        daysET.requestFocus();
                        return;
                    }


                    int d = Integer.parseInt(days);

                    if(d==0){
                        daysET.setError("Enter value greater than zero");
                        daysET.requestFocus();
                        return;
                    }

                    int pos = Integer.parseInt(providerId);
                    Log.e("providerId", "onClick: "+providerId );
                  if(providerId.equals("")){
                        Toast.makeText(getActivity(), "Select provider", Toast.LENGTH_SHORT).show();
                        Log.e("ff", "onClick: "+providerId );
                        return;
                    }

                     if(sid > 0){

                      //   Toast.makeText(getActivity(), "fff", Toast.LENGTH_SHORT).show();




                        /* final JsonObject jsonObject1 = new JsonObject();
                         jsonObject1.addProperty("action","ADD-EDIT");
                         jsonObject1.addProperty("provider_id",providerId);
                         jsonObject1.addProperty("purchase_date",days);
                         jsonObject1.addProperty("purchase_qty",sQty);
                         jsonObject1.addProperty("user_id",userID);
                         jsonObject1.addProperty("id",sid);*/
                        /* if(providerId.equals("0")){
                             Toast.makeText(getActivity(), "Select Provider", Toast.LENGTH_SHORT).show();
                             return;
                         }
                         if(actvProviderTypeStockFragment.getText().toString().isEmpty()){
                             Toast.makeText(getActivity(), "Select Provider", Toast.LENGTH_SHORT).show();
                             return;
                         }*/

                        if(Database.getInstance(getContext()).getProviderDao().searchProvider(actvProviderTypeStockFragment.getText().toString() ) <= 0){
                            Toast.makeText(getActivity(), "Select Provider", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            String name = actvProviderTypeStockFragment.getText().toString();
                            providerName = name;
                            providerId = Database.getInstance(getContext()).getProviderDao().getProviderID(actvProviderTypeStockFragment.getText().toString());
                            Log.e("FragmentName", "onClick: "+name );
                            Log.e("FragmentName", "onClick: "+providerId );
                        }



                         stockQuantityET.setText("");
                         daysET.setText("");

                       //  Log.e("jsonObject1", "onClick: "+jsonObject1.toString());
                         
                         /*serviceApi.addAllStockProvider(jsonObject1).enqueue(new Callback<StockAddProviderModel>() {
                             @Override
                             public void onResponse(Call<StockAddProviderModel> call, Response<StockAddProviderModel> response) {
                                 if(response.isSuccessful()){
                                     try {
                                            saveBtn.setClickable(false);
                                         //   Log.e("su", "onResponse: "+response.body().toString());
                                         // Log.e("su", "onResponse: "+response.body().getPcsAddEdit().get(0).getStatus());
                                         //   Navigation.findNavController(view).navigate(R.id.stockRecordsFragment);
                                         //     String pn = response.body().getPcsAddEdit().

                                         String qtyStock = stockQuantityET.getText().toString();
                                         String daysStock = daysET.getText().toString();
                                         String pnStock = actvProviderTypeStockFragment.getText().toString();
                                         if(qtyStock == qty && daysStock == days && pnStock == pn){
                                             Log.e("quantitiy", "onResponse: "+qtyStock);
                                             Log.e("quantitiy", "onResponse: "+daysStock);
                                             Log.e("quantitiy", "onResponse: "+pnStock);
                                         }
                                         Navigation.findNavController(view).popBackStack();
                                         Toast.makeText(getActivity(), ""+response.body().getPcsAddEdit().getMs(), Toast.LENGTH_SHORT).show();

                                   *//*  if(value == 1){

                                     }*//*

                                         //Log.e("su", "onFailure: save");
                                     } catch (Exception e) {
                                         e.printStackTrace();
                                     }
                                 }
                             }

                             @Override
                             public void onFailure(Call<StockAddProviderModel> call, Throwable t) {

                             }
                         });*/


                          updateDataLocalDatabase(providerThanaAreaId,providerThanaAreaName,providerId,providerName,sQty,days,String.valueOf(sid));
                         Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_SHORT).show();

                         Navigation.findNavController(view).popBackStack();






                     }else {




/*
                         final JsonObject jsonObject = new JsonObject();
                         jsonObject.addProperty("action","ADD-EDIT");
                         jsonObject.addProperty("provider_id",providerId);
                         jsonObject.addProperty("purchase_date",days);
                         jsonObject.addProperty("purchase_qty",sQty);
                         jsonObject.addProperty("user_id",userID);
                         jsonObject.addProperty("id","0");*/

                         if(Database.getInstance(getContext()).getProviderDao().searchProvider(actvProviderTypeStockFragment.getText().toString() ) <= 0){
                             Toast.makeText(getActivity(), "Select Provider", Toast.LENGTH_SHORT).show();
                             return;
                         }else {
                             String name = actvProviderTypeStockFragment.getText().toString();
                             providerName = name;
                             providerId = Database.getInstance(getContext()).getProviderDao().getProviderID(actvProviderTypeStockFragment.getText().toString());
                             Log.e("FragmentName", "onClick: "+name );
                             Log.e("FragmentName", "onClick: "+providerId );
                         }
                         stockQuantityET.setText("");
                         daysET.setText("");

                        // Log.e("jo", "onClick: "+jsonObject.toString());

                       /*  serviceApi.addAllStockProvider(jsonObject).enqueue(new Callback<StockAddProviderModel>() {
                             @Override
                             public void onResponse(Call<StockAddProviderModel> call, Response<StockAddProviderModel> response) {
                                 try {
                                     if(response.isSuccessful()){
                                         saveBtn.setClickable(false);
                                         //   Log.e("su", "onResponse: "+response.body().toString());
                                         // Log.e("su", "onResponse: "+response.body().getPcsAddEdit().get(0).getStatus());
                                         Toast.makeText(getActivity(), ""+response.body().getPcsAddEdit().getMs(), Toast.LENGTH_SHORT).show();
                                         //Navigation.findNavController(view).navigate(R.id.stockRecordsFragment);

                                         //Log.e("su", "onFailure: save");

                                     }
                                 } catch (Exception e) {
                                     e.printStackTrace();
                                 }
                             }

                             @Override
                             public void onFailure(Call<StockAddProviderModel> call, Throwable t) {

                             }
                         });*/

                         saveDataLocalDatabase(providerThanaAreaId,providerThanaAreaName,providerId,providerName,sQty,days);

                         Log.e("providerDetails", "onClick: providerDetails"+providerThanaAreaId+" "+providerThanaAreaName+" "+providerId+" "+providerName+" "+sQty+" "+days+" "+date );

                         Toast.makeText(getActivity(), "Stock added Successfully", Toast.LENGTH_SHORT).show();

                         Navigation.findNavController(view).popBackStack();

                     }

                }catch (Exception e){

                }


            }
        });

        return view;
    }

    private void updateDataLocalDatabase(String providerThanaAreaID,String providerThanaAreaName,String pID,String pName,String qty,String days,String sid) {

        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                // stockInfoModel = new StockInfoModel(providerThanaAreaID,providerThanaAreaName,pID,pName,qty,days,date,flag);
                 int upDateRow =  Database.getInstance(getActivity()).getProviderDao().stockValueUpdate(providerThanaAreaID,providerThanaAreaName,pID,pName,qty,days,date,flag,sid);
                Log.e("upDateRow", "doInBackground: "+providerThanaAreaID+" "+providerThanaAreaName+" "+pID+" "+pName+" "+qty+" "+days+" "+date+" "+flag+" "+sid );
                return upDateRow;

            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);

               // Log.e("stockInfoModel", "onPostExecute: "+StockInfoModel. );
            }
        }.execute();
    }

    private void saveDataLocalDatabase(String providerThanaAreaID,String providerThanaAreaName,String pID,String pName,String qty,String days) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                StockInfoModel stockInfoModel = new StockInfoModel(providerThanaAreaID,providerThanaAreaName,pID,pName,qty,days,date,flag,"0");
                return Database.getInstance(getContext()).getProviderDao().saveStockData(stockInfoModel);

            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                //Toast.makeText(getContext(), "StockInfoModel", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }




    /*private void updateDataLocalDatabase(String pID,String qty,String days,int id) {

        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return Database.getInstance(getContext()).getProviderDao().stockValueUpdate(days,qty,providerName,pID,id);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
            }
        }.execute();


    }*/


    private void setProviderList() {

        providerNameList.clear();
        providerIdList.clear();
        providerNameList.add("--Select--");
        providerIdList.add(0);


        final JsonObject providerObj = new JsonObject();
        Log.e("providerObj", "setProviderList: "+providerId );
        providerObj.addProperty("thana_id",providerThanaAreaId);
        providerObj.addProperty("user_id",userID);
        Log.e("providerObj", "setProviderList: "+providerObj.toString() );
        //ServiceApi providerTypeApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<PoMappingProviderModel> allPoMappingProvider = Database.getInstance(getActivity()).getProviderDao().getAllPoMappingProvider(providerThanaAreaId);
                    for(int providerApi=0;providerApi< allPoMappingProvider.size();providerApi++){
                        providerNameList.add(allPoMappingProvider.get(providerApi).getPoMappingProviderName());
                        providerIdList.add(Integer.parseInt(allPoMappingProvider.get(providerApi).getPoMappingProviderID()));
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);

                    if(getActivity()!=null){
                        ArrayAdapter<String> providerAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.custom_layout, providerNameList);


                        actvProviderTypeStockFragment.setAdapter(providerAdapter);
                    }
                    Log.e("providerObjID", "setProviderList: "+providerIdList );
                    if(sid > 0){
                        try {
                            Log.e("providerIdSID", "onResponse: "+providerIdList.indexOf(Integer.parseInt(providerId)));
                            Log.e("providerIdSID", "onResponse: "+providerIdList.size());
                            Log.e("providerIdSID", "onResponse: "+providerIdList.size());
                            Log.e("providerNameList", "onResponse: "+providerNameList.size());

                            int index = providerIdList.indexOf(Integer.parseInt(providerId));
                            Log.e("providerIdSID", "onResponse: "+index);
                            if(index>=0){
                                actvProviderTypeStockFragment.setText(providerNameList.get(index));
                       /* actvProviderTypeStockFragment.setFocusableInTouchMode(false);
                        actvProviderTypeStockFragment.clearFocus();
                        actvProviderTypeStockFragment.setCursorVisible(false);*/
                            }
                        }catch (Exception e){}


                    }
                    actvProviderTypeStockFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            providerId = providerIdList.get(position).toString();
                            providerName = providerNameList.get(position);
                            Log.e("providerId", "onItemClick: "+providerName );
                            Log.e("providerId", "onItemClick: "+providerId );
                            //  Toast.makeText(getContext(), ""+providerId, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }.execute();








        }catch (Exception e){}

       /* providerTypeApi.getAllPo_ProviderApi(providerObj).enqueue(new Callback<PoProviderModel>() {
            @Override
            public void onResponse(Call<PoProviderModel> call, Response<PoProviderModel> response) {
                Log.e("response_bodysize", "onResponse: "+response.body().getProvider().getProviderList().size());
                try {
                    if(response.isSuccessful()){

                        for(int providerApi=0;providerApi< response.body().getProvider().getProviderList().size();providerApi++){
                            providerNameList.add(response.body().getProvider().getProviderList().get(providerApi).getName());

                            providerIdList.add(Integer.parseInt(response.body().getProvider().getProviderList().get(providerApi).getId()));
                            Log.e("proResponseID", "onResponse: "+response.body().getProvider().getProviderList().get(providerApi).getId() );
                        }
                    }
                }catch (Exception e){
                    Log.e("providerEx", "onResponse: "+e.getLocalizedMessage() );
                }

                if(getActivity()!=null){
                    ArrayAdapter<String> providerAdapter = new ArrayAdapter<String>(getContext(),
                            R.layout.custom_layout, providerNameList);


                    actvProviderTypeStockFragment.setAdapter(providerAdapter);
                }

                Log.e("providerObjID", "setProviderList: "+providerIdList );
                if(sid > 0){
                    try {
                        Log.e("providerIdSID", "onResponse: "+providerIdList.indexOf(Integer.parseInt(providerId)));
                        Log.e("providerIdSID", "onResponse: "+providerIdList.size());
                        Log.e("providerIdSID", "onResponse: "+providerIdList.size());
                        Log.e("providerNameList", "onResponse: "+providerNameList.size());

                        int index = providerIdList.indexOf(Integer.parseInt(providerId));
                        Log.e("providerIdSID", "onResponse: "+index);
                        if(index>=0){
                            actvProviderTypeStockFragment.setText(providerNameList.get(index));
                       *//* actvProviderTypeStockFragment.setFocusableInTouchMode(false);
                        actvProviderTypeStockFragment.clearFocus();
                        actvProviderTypeStockFragment.setCursorVisible(false);*//*
                        }
                    }catch (Exception e){}


                }

                actvProviderTypeStockFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        providerId = providerIdList.get(position).toString();
                      //  Toast.makeText(getContext(), ""+providerId, Toast.LENGTH_SHORT).show();

                    }
                });



            }

            @Override
            public void onFailure(Call<PoProviderModel> call, Throwable t) {

            }
        });*/


    }

    private void setProviderSpinner() {

        providerThanaAreaNameList.clear();
        providerThanaAreaIdList.clear();
        providerThanaAreaNameList.add("--Select--");
        providerThanaAreaIdList.add(0);
        final JsonObject stockProviderJson = new JsonObject();
        stockProviderJson.addProperty("user_id",userID);
        Log.e("user_id", "setProviderSpinner: "+stockProviderJson.toString() );

        try {

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<PoMappingThanaModel> allPoMappingThana = Database.getInstance(getActivity()).getProviderDao().getAllPoMappingThana();
                    for(int p = 0;p<allPoMappingThana.size();p++){
                        providerThanaAreaNameList.add(allPoMappingThana.get(p).getPoMappingThanaName());
                        providerThanaAreaIdList.add(allPoMappingThana.get(p).getPoMappingThanaID());

                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);

                    ArrayAdapter<String> providerAdapter = new ArrayAdapter<String>(getContext(),
                            R.layout.custom_layout, providerThanaAreaNameList);
                    providerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    providerTypeSP.setAdapter(providerAdapter);
                    Log.e("positionSids", "onResponse: "+sid);

                    if(sid > 0){
                        try {
                            Log.e("providerIdSID", "onResponse: "+providerThanaAreaIdList.indexOf(Integer.parseInt(providerThanaAreaId)));
                            providerTypeSP.setSelection(providerThanaAreaIdList.indexOf(Integer.parseInt(providerThanaAreaId)));
                        }catch (Exception e){}

                    }

                    providerTypeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            providerThanaAreaId = providerThanaAreaIdList.get(position).toString();
                            providerThanaAreaName = providerThanaAreaNameList.get(position);

                            Log.e("providerThanaAreaId", "onItemSelected: "+providerThanaAreaName );
                            actvProviderTypeStockFragment.setText("");
                            providerIdList.clear();
                            providerNameList.clear();

                            setProviderList();

                          /*  if(parent.getItemAtPosition(position).equals("--Select--")){
                                // saveBtn.setClickable(false);
                                // Toast.makeText(getActivity(), "Select a Provider", Toast.LENGTH_SHORT).show();
                              //  return;
                                providerIdList.clear();
                                providerNameList.clear();
                                setProviderList();
                            }else {
                                providerThanaAreaId = providerThanaAreaIdList.get(position).toString();
                                providerThanaAreaName = providerThanaAreaNameList.get(position);

                                Log.e("providerThanaAreaId", "onItemSelected: "+providerThanaAreaName );
                                actvProviderTypeStockFragment.setText("");
                                providerIdList.clear();
                                providerNameList.clear();

                                setProviderList();
                                //  saveBtn.setClickable(true);

                            }*/

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                  //  Log.e("allPoMappingThana", "setProviderSpinner: "+allPoMappingThana );
                }
            }.execute();










        }catch (Exception e){}








       /* ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        serviceApi.getAllStockPoMapping(stockProviderJson).enqueue(new Callback<StockPoMappingListModel>() {
            @Override
            public void onResponse(Call<StockPoMappingListModel> call, Response<StockPoMappingListModel> response) {
                try {
                    if(response.isSuccessful()){
                        for(int p = 0;p<response.body().getPcsAddEdit().getPoMappingThana().size();p++){
                            providerThanaAreaNameList.add(response.body().getPcsAddEdit().getPoMappingThana().get(p).getName());
                            providerThanaAreaIdList.add(response.body().getPcsAddEdit().getPoMappingThana().get(p).getId());
                        }
                    }

                    Log.e("TAG", "onResponse: "+response.body().getPcsAddEdit().getPoMappingThana().get(0).getName());
                    Log.e("TAG", "onResponse: "+response.body().getPcsAddEdit().getPoMappingThana().get(0).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ArrayAdapter<String> providerAdapter = new ArrayAdapter<String>(getContext(),
                        R.layout.custom_layout, providerThanaAreaNameList);
                providerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                providerTypeSP.setAdapter(providerAdapter);

                if(sid > 0){
                    try {
                        Log.e("providerIdSID", "onResponse: "+providerThanaAreaIdList.indexOf(Integer.parseInt(providerThanaAreaId)));
                        providerTypeSP.setSelection(providerThanaAreaIdList.indexOf(Integer.parseInt(providerThanaAreaId)));
                    }catch (Exception e){}

                }

                providerTypeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if(parent.getItemAtPosition(position).equals("--Select--")){
                            // saveBtn.setClickable(false);
                            // Toast.makeText(getActivity(), "Select a Provider", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            providerThanaAreaId = providerThanaAreaIdList.get(position).toString();
                            providerIdList.clear();
                            providerNameList.clear();
                            actvProviderTypeStockFragment.setText("");
                            setProviderList();
                            //  saveBtn.setClickable(true);
                            Log.e("providerId", "onItemSelected: "+providerThanaAreaId );
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<StockPoMappingListModel> call, Throwable t) {

            }
        });*/

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
}