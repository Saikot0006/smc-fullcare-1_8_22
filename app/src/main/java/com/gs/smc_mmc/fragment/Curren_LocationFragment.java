package com.gs.smc_mmc.fragment;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gs.smc_mmc.R;
import com.gs.smc_mmc.view_model.LocationViewModel;

import org.jetbrains.annotations.NotNull;


public class Curren_LocationFragment extends Fragment {


    private LocationViewModel locationViewModel;
    private TextView latLonTV;
    public Curren_LocationFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curren__location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        latLonTV = view.findViewById(R.id.latLonTV);
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        locationViewModel.getLocationMutableLiveData().observe(getActivity(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();

                latLonTV.setText(lat+", "+lng);
            }
        });

    }
}