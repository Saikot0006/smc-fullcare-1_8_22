package com.gs.smc_mmc.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.PWViewModel;
import com.gs.smc_mmc.model.PW_RecordModel;
import com.gs.smc_mmc.model.PwRegistrationModel;
import com.gs.smc_mmc.model.ThanaModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PWInfoModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;

import java.util.List;


public class patientInfoFragment extends Fragment {

    private TextView nameTV;
    private EditText ageET,mobileET,divisionET,districtET,thanaET,
            purcherET,pragnancyET,healthET,lmpET,eddET;
    String id;



    public patientInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_patient_info, container, false);

        nameTV = view.findViewById(R.id.pw_viewName);
        ageET = view.findViewById(R.id.pw_viewAge);
        mobileET = view.findViewById(R.id.pwViewMobile);
        divisionET = view.findViewById(R.id.pwViewDivision);
        districtET = view.findViewById(R.id.pwViewDistrict);
        thanaET = view.findViewById(R.id.pwViewThana);
        purcherET = view.findViewById(R.id.pwViewPurcher);
        pragnancyET = view.findViewById(R.id.pwViewPregnancy);
        healthET = view.findViewById(R.id.pwViewHealth);
        lmpET = view.findViewById(R.id.pwViewLmp);
        eddET = view.findViewById(R.id.pwViewEdd);


        //String id = null;
        try {
             id = getArguments().getString("ID","-1");
        } catch (Exception e) {
            e.printStackTrace();
        }


        // String  value = getArguments().getString("value");

        int pwId = Integer.parseInt(id);
        Log.e("id---",""+pwId);


        ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);
      //  Log.e("ii", "onCreateView: "+id);
     //   Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();


        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action","VIEW");
        jsonObject.addProperty("id",id);
        jsonObject.addProperty("user_id",id);

        Log.e("req", "onCreateView: "+jsonObject.toString() );

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                //Log.e("detailsID", "doInBackground: "+id );
                List<PWInfoModel> pWInfoModellList = Database.getInstance(getContext()).getProviderDao().getSinglePWInfo(id);
                Log.e("sizeOfPWInfoModellList", "doInBackground: "+pWInfoModellList.size());

                nameTV.setText(pWInfoModellList.get(0).getName());
                ageET.setText(pWInfoModellList.get(0).getAge());
                mobileET.setText(pWInfoModellList.get(0).getMobileNo());
                pragnancyET.setText(pWInfoModellList.get(0).getNumberOfPregnancy());
                lmpET.setText(pWInfoModellList.get(0).getLmp());
                eddET.setText(pWInfoModellList.get(0).getEdd());

                if(pWInfoModellList.get(0).getDivisionName().equals("--- Select ---")){
                    divisionET.setText("");
                }else {
                    divisionET.setText(pWInfoModellList.get(0).getDivisionName());
                }

                if(pWInfoModellList.get(0).getDistrictName().equals("--Select--")){
                    districtET.setText("");
                }else{
                    districtET.setText(pWInfoModellList.get(0).getDistrictName());
                }

                if(pWInfoModellList.get(0).getThanaName().equals("--Select--")){
                    thanaET.setText("");
                }else {
                    thanaET.setText(pWInfoModellList.get(0).getThanaName());
                }


                purcherET.setText(pWInfoModellList.get(0).getBuyerName());
                healthET.setText(pWInfoModellList.get(0).getHealthConditionName());
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();

       /* serviceApi.setPWInfo(jsonObject).enqueue(new Callback<PWViewModel>() {
            @Override
            public void onResponse(Call<PWViewModel> call, Response<PWViewModel> response) {
                if(response!=null){

                    try {

                        if(pwId > 0){
                            nameTV.setText( response.body().getPwViewEdit().get(0).getInfo().getName());
                            ageET.setText(response.body().getPwViewEdit().get(0).getInfo().getAge());
                            mobileET.setText(response.body().getPwViewEdit().get(0).getInfo().getMobileNo());
                            pragnancyET.setText(response.body().getPwViewEdit().get(0).getInfo().getNumberOfPregnancy());
                            lmpET.setText(response.body().getPwViewEdit().get(0).getInfo().getLmp());
                            eddET.setText(response.body().getPwViewEdit().get(0).getInfo().getEdd());

                            divisionET.setText(response.body().getPwViewEdit().get(0).getInfo().getDivisionName());
                            districtET.setText(response.body().getPwViewEdit().get(0).getInfo().getDistrictName());
                            thanaET.setText(response.body().getPwViewEdit().get(0).getInfo().getThanaName());
                            thanaET.setText(response.body().getPwViewEdit().get(0).getInfo().getThanaName());
                            purcherET.setText(response.body().getPwViewEdit().get(0).getInfo().getBuyerName());
                            healthET.setText(response.body().getPwViewEdit().get(0).getInfo().getHealthConditionName());
                            //healthET.setText(response.body().getPwViewEdit().get(0).getHealthConditions().get(0).getName());

                           // Log.e("TAG", "onResponse: "+response.body().getPwViewEdit().get(0).getHealthConditions().get(0).getName() );



                         //   Log.e("TAG", "onResponse: "+ new Gson().toJson(response.body().getPwViewEdit().get(0).getHealthConditions()));


                        }


                       // Toast.makeText(getActivity(), response.body().getPwViewEdit().get(0).getHealthConditions().get(pwId).getName(), Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Log.e("TAG", "onResponse: "+e );
                    }


                }
            }

            @Override
            public void onFailure(Call<PWViewModel> call, Throwable t) {

            }
        });*/





       /* saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JsonObject jsonObject1 = new JsonObject();

                jsonObject1.addProperty("id",id);
                jsonObject1.addProperty("action","EDIT");

                Log.e("jsonrequest",jsonObject1.toString());

                serviceApi.editPWInfo(jsonObject1).enqueue(new Callback<PWViewModel>() {
                    @Override
                    public void onResponse(Call<PWViewModel> call, Response<PWViewModel> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

                            final JsonObject jo = new JsonObject();
                            jo.addProperty("provider_id",1);
                            jo.addProperty("name",nameTV.getText().toString());
                            jo.addProperty("age",ageET.getText().toString());
                            jo.addProperty("mobile_no",mobileET.getText().toString());
                            jo.addProperty("division_id", 1);
                            jo.addProperty("district_id",1);
                            jo.addProperty("thana_id",1);
                            jo.addProperty("thana_id",1);
                            jo.addProperty("health_condition_id",1);
                            jo.addProperty("lmp",lmpET.getText().toString());
                            jo.addProperty("edd",eddET.getText().toString());

                           *//* serviceApi.pushPwRegistration(jo).enqueue(new Callback<PwRegistrationModel>() {
                                @Override
                                public void onResponse(Call<PwRegistrationModel> call, Response<PwRegistrationModel> response) {
                                    if(response.isSuccessful()){

                                    }
                                }

                                @Override
                                public void onFailure(Call<PwRegistrationModel> call, Throwable t) {

                                }
                            });*//*


                        }
                    }

                    @Override
                    public void onFailure(Call<PWViewModel> call, Throwable t) {

                    }
                });
            }
        });*/
        return view;
    }
}