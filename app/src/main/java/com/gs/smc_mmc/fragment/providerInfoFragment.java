package com.gs.smc_mmc.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.StockProviderViewModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class providerInfoFragment extends Fragment {


    private TextView stockNameTV;
    int userID = 0;
    private EditText stockTypeET,stockProviderIdET,stockQuantityET,stockLastDateET;
    public providerInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_provider_info, container, false);

        stockNameTV = view.findViewById(R.id.stockNameTV);
//        stockTypeET = view.findViewById(R.id.stockTypeET);
        stockProviderIdET = view.findViewById(R.id.stockProviderIdET);
        stockQuantityET = view.findViewById(R.id.stockQuantityET);
        stockLastDateET = view.findViewById(R.id.stockLastDateET);

        String id = getArguments().getString("sId");
        userID = ConstantUtils.Temp.id;

       // ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);
      //  jsonObject.addProperty("id",userID);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e("detailsID", "doInBackground: "+id );
                List<StockInfoModel> stockInfoModelList = Database.getInstance(getContext()).getProviderDao().getSingleStockInfo(id);

                stockNameTV.setText(stockInfoModelList.get(0).getPoMappingProviderName());
                //   stockTypeET.setText(response.body().getPcsView().getInfo().get);
                stockProviderIdET.setText(stockInfoModelList.get(0).getPoMappingProviderID());
                stockQuantityET.setText(stockInfoModelList.get(0).getStockQuantity());
                stockLastDateET.setText(stockInfoModelList.get(0).getLastPurchasedDay());
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();

        /*serviceApi.viewStockProvider(jsonObject).enqueue(new Callback<StockProviderViewModel>() {
            @Override
            public void onResponse(Call<StockProviderViewModel> call, Response<StockProviderViewModel> response) {
                if(response.isSuccessful()){
                    stockNameTV.setText(response.body().getPcsView().getInfo().getProviderName());
                  //   stockTypeET.setText(response.body().getPcsView().getInfo().get);
                    stockProviderIdET.setText(response.body().getPcsView().getInfo().getId());
                    stockQuantityET.setText(response.body().getPcsView().getInfo().getQty());
                    stockLastDateET.setText(response.body().getPcsView().getInfo().getDate());
                    // stockTypeET.setText(response.body().getPcsViewEdit().get(0).getInfo().get);
                }
            }

            @Override
            public void onFailure(Call<StockProviderViewModel> call, Throwable t) {

            }
        });*/


      /* serviceApi.viewStockProvider(jsonObject).enqueue(new Callback<SViewModel>() {
           @Override
           public void onResponse(Call<SViewModel> call, Response<SViewModel> response) {
               if(response.isSuccessful()){
                   stockNameTV.setText(response.body().getPcsViewEdit().get(0).getInfo().getProviderName());
                  // stockTypeET.setText(response.body().getPcsViewEdit().get(0).getInfo());
                   stockProviderIdET.setText(response.body().getPcsViewEdit().get(0).getInfo().getId());
                   stockQuantityET.setText(response.body().getPcsViewEdit().get(0).getInfo().getQty());
                   stockLastDateET.setText(response.body().getPcsViewEdit().get(0).getInfo().getDate());
                  // stockTypeET.setText(response.body().getPcsViewEdit().get(0).getInfo().get);
               }

           }

           @Override
           public void onFailure(Call<SViewModel> call, Throwable t) {

           }
       });*/

        return view;
    }
}