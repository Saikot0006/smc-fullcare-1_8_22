package com.gs.smc_mmc.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class StockFilterFragment extends Fragment {

    //private EditText swFilterFirstET,swFilterSecondET;
   // private EditText swFilterSecondET;

    private Button swFilterResetBtn,swFilterApplyBtn;
    private TextView swFilterFirstET,swFilterSecondET;
    public StockFilterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_stock_filter, container, false);

        swFilterFirstET = view.findViewById(R.id.swFilterFirstID);
        swFilterSecondET = view.findViewById(R.id.swFilterSecondID);
        swFilterResetBtn = view.findViewById(R.id.swFilterResetID);
        swFilterApplyBtn = view.findViewById(R.id.swFilterApplyID);

        swFilterFirstET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(getContext(),new OnDateSetListenerImpl(),year,month,day);

                datePickerDialog.show();

            }
        });

        swFilterSecondET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(getContext(),new OnDateSetListener2Impl(),year,month,day);

                datePickerDialog.show();
            }
        });

        swFilterResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swFilterFirstET.setText("");
                swFilterSecondET.setText("");
            }
        });

        swFilterApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start_date = swFilterFirstET.getText().toString();
                String end_date = swFilterSecondET.getText().toString();

                if(start_date.isEmpty()){
                    swFilterFirstET.setError("Enter start date");
                    swFilterFirstET.requestFocus();
                    return;
                }if(end_date.isEmpty()){
                    swFilterSecondET.setError("Enter end date");
                    swFilterSecondET.requestFocus();
                    return;
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString("start_date",start_date);
                    bundle.putString("end_date",end_date);

                    Navigation.findNavController(view).navigate(R.id.action_stockFilterFragment_to_stockRecordsFragment,bundle);

                    swFilterFirstET.setText("");
                    swFilterSecondET.setText("");



                }
            }
        });
        return view;
    }


    class OnDateSetListenerImpl implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String selectedDate = sdf.format(calendar.getTime());
            swFilterFirstET.setText(selectedDate);

        }
    }

    class OnDateSetListener2Impl implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String selectedDate = sdf.format(calendar.getTime());
            swFilterSecondET.setText(selectedDate);

        }
    }

}