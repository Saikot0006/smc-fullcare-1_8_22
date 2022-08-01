package com.gs.smc_mmc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.gs.smc_mmc.model.LocationModel;
import com.gs.smc_mmc.model.PoWorkLocationSaveModel;
import com.gs.smc_mmc.prefs.AuthPreference;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;
import com.gs.smc_mmc.utils.LocationPermissionHelper;
import com.gs.smc_mmc.view_model.LocationViewModel;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient providerClient;
    private LocationViewModel locationViewModel;
    private LocationRequest locationRequest;
    private final JsonObject jsonObject = new JsonObject();
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    public AuthPreference authPreference;
    private BroadcastReceiver MyReceiver;


    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull @NotNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            for (Location location : locationResult.getLocations()) {
                locationViewModel.setLocationMutableLiveData(location);
                Log.e("location_update_data", "onLocationResult: "+location );
                // Log.e("f", "onLocationResult: "+locationViewModel.setLocationMutableLiveData(location.getLatitude()));
                //  jsonObject.addProperty("location", );




            }

        }
    };

    private void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this,MyService.class));
        providerClient = LocationServices.getFusedLocationProviderClient(this);
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        authPreference = new AuthPreference(this);

        MyReceiver = new NetworkChangeReceiver();

        locationPermission();

        broadcastIntent();


    }

    private void locationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        if (!LocationPermissionHelper.hasLocationPermission(this)) {
            LocationPermissionHelper.requestLocationPermission(this);
            return;
        }

        getDeviceLastLocation();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!LocationPermissionHelper.hasLocationPermission(this)) {
            Toast.makeText(this, "You denied required permission", Toast.LENGTH_SHORT).show();
            return;
        }

        getDeviceLastLocation();
    }

    private void getDeviceLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        providerClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location == null) {
                    createLocationRequest();
                    return;
                }
                locationViewModel.setLocationMutableLiveData(location);

               /* double lat = location.getLatitude();
                double lng = location.getLongitude();

                Log.e("Current Location", "Latitude: "+lat);
                Log.e("Current Location", "Longitude: "+lng);*/
            }
        });
    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setNumUpdates(1);

        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> responseTask = settingsClient.checkLocationSettings(locationSettingsRequest);

        responseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                requestDeviceLocationUpdate();
            }
        });

        responseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException apiException = (ResolvableApiException) e;
                        apiException.startResolutionForResult(MainActivity.this, 222);
                    } catch (Exception e1) {

                    }

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222 && resultCode == RESULT_OK) {
            requestDeviceLocationUpdate();
        }
    }

    private void requestDeviceLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        providerClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }


    private String currentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Log.e("sdfFormat", "currentDate: "+(sdf.format(cal.getTime())));
        return sdf.format(cal.getTime());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "onDestroy: onDestroy" );
        unregisterReceiver(MyReceiver);
    }


}