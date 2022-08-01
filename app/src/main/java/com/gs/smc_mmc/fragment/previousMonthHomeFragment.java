package com.gs.smc_mmc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.DashboardModel;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class previousMonthHomeFragment extends Fragment {

    private TextView preQuaterVisitor,prePwRegistration;
    int b = 0;

    public previousMonthHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_previous_month_home, container, false);

        preQuaterVisitor = view.findViewById(R.id.preQuaterVisitor);
        prePwRegistration = view.findViewById(R.id.prePwRegistration);

        b = ConstantUtils.Temp.id;

        Log.e("id", "onCreateView: "+b);

        ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        if(b > 0){
            try {
                final JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("user_id",b);
                serviceApi.postDashBoard(jsonObject).enqueue(new Callback<DashboardModel>() {
                    @Override
                    public void onResponse(Call<DashboardModel> call, Response<DashboardModel> response) {
                        if(response.isSuccessful()){
                            preQuaterVisitor.setText(""+response.body().getDashb().getPreMonth().getTotalVisit());
                            prePwRegistration.setText(""+response.body().getDashb().getPreMonth().getTotalPWRegistration());

                        }
                    }

                    @Override
                    public void onFailure(Call<DashboardModel> call, Throwable t) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return view;
    }
}