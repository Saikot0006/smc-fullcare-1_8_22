package com.gs.smc_mmc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.adepter.PW_Record_Adepter;
import com.gs.smc_mmc.model.DashboardModel;
import com.gs.smc_mmc.model.PW_RecordModel;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class pwRecordHomeFragment extends Fragment {

    int b = 0;
    TextView currentQuaterVisitor,currentPwRegistration;

    public pwRecordHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pw_record_home, container, false);

        currentQuaterVisitor = view.findViewById(R.id.currentQuaterVisitor);
        currentPwRegistration = view.findViewById(R.id.currentPwRegistration);


        b = ConstantUtils.Temp.id;

        Log.e("id", "onCreateView: "+b);

        if(b > 0){
            ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("user_id",b);
            serviceApi.postDashBoard(jsonObject).enqueue(new Callback<DashboardModel>() {
                @Override
                public void onResponse(Call<DashboardModel> call, Response<DashboardModel> response) {
                    if(response.isSuccessful()){
                        currentQuaterVisitor.setText(""+response.body().getDashb().getCurrentQuarter().getTotalVisit());
                        currentPwRegistration.setText(""+response.body().getDashb().getCurrentQuarter().getTotalPWRegistration());

                    }
                }

                @Override
                public void onFailure(Call<DashboardModel> call, Throwable t) {

                }
            });
        }




        return view;
    }


}