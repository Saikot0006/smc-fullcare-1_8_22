package com.gs.smc_mmc;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gs.smc_mmc.model.LocationModel;
import com.gs.smc_mmc.model.PoWorkLocationSaveModel;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends Service {

    Context context;
    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult( LocationResult locationResult) {
            super.onLocationResult(locationResult);
            for (Location location : locationResult.getLocations()) {
                double latitude = locationResult.getLastLocation().getLatitude();
                double longitude = locationResult.getLastLocation().getLongitude();


                insertLocationData(latitude,longitude);
                Log.e("location_update", "onLocationResult: " + latitude + " " + longitude);

                Timer timer  = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        po_work_location_save(context);
                    }
                },60000);


            }

        }
    };

    private void insertLocationData(Double latitude,Double longitude) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(date);

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {

                LocationModel locationModel = new LocationModel(currentDate,latitude,longitude);
                return com.gs.smc_mmc.roomdatabase.Database.getInstance(getApplicationContext()).getProviderDao().locationData(locationModel);
            }
        }.execute();

    }
    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        startLocationService();
    }
    private void startLocationService() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void stopLocationServices(){
        LocationServices.getFusedLocationProviderClient(this)
                .removeLocationUpdates(locationCallback);
        stopForeground(true);
        //  stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String channelId = "location_notification_channel";
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(),
                channelId
        );
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Location Service");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentText("Running");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager != null
                    && notificationManager.getNotificationChannel(channelId) == null) {
                NotificationChannel notificationChannel = new NotificationChannel(
                        channelId,
                        "Location Service",
                        NotificationManager.IMPORTANCE_HIGH
                );
                notificationChannel.setDescription("Get Current Location Continuously");
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        startForeground(1,builder.build());
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void po_work_location_save(Context context) {
        Log.e("hello001", "po_work_location_save: hello001");
        final JsonObject jsonObjectView = new JsonObject();
        final JsonObject jsonObjectDetails = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        List<LocationModel> locationModelsData = com.gs.smc_mmc.roomdatabase.Database.getInstance(context).getProviderDao().getLocationData();
        Log.e("locationModelsData", "po_work_location_save: " + locationModelsData.size());
        for (int i = 0; i < locationModelsData.size(); i++) {
            Log.e("locationLatitude", "po_work_location_save: " + locationModelsData.get(i).getLatitude());
            jsonObjectView.addProperty("latitude", locationModelsData.get(i).getLatitude());
            jsonObjectView.addProperty("longitude", locationModelsData.get(i).getLongitude());
            // jsonObjectView.addProperty("date",locationModelsData.get(i).getDate());
            jsonObjectView.addProperty("date", System.currentTimeMillis());

            Log.e("locationModelsData", "po_work_location_save: " + jsonObjectView.toString());

            jsonArray.add(jsonObjectView);

        }

        jsonObjectDetails.addProperty("userID", ConstantUtils.Temp.id);
        jsonObjectDetails.add("data", jsonArray);

        Log.e("locationModelsDataArray", "po_work_location_save: " + jsonObjectDetails.toString());

        ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);
        serviceApi.poWorkLocationSave(jsonObjectDetails).enqueue(new Callback<PoWorkLocationSaveModel>() {
            @Override
            public void onResponse(Call<PoWorkLocationSaveModel> call, Response<PoWorkLocationSaveModel> response) {
                Log.e("BoolenData", "po_work_location_save: " + response.isSuccessful());
                if (response.isSuccessful()) {
                    Log.e("respomseValue", "onResponse: "+response.body().getAllData());
                    deleteLocationData(context);
                }
            }

            @Override
            public void onFailure(Call<PoWorkLocationSaveModel> call, Throwable t) {
                Log.e("locationMessage", "onFailure: " + t.getLocalizedMessage());
            }
        });





        // Log.e("sdfFormat", "po_work_location_save: "+jsonObjectView.toString() );

    }

    private void deleteLocationData(Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e("deleteLocationData", "doInBackground: delete");
                Database.getInstance(context).getProviderDao().deleteLocationData();
                return null;
            }
        }.execute();
    }

}