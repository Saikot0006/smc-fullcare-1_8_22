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

import com.gs.smc_mmc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class filterFragment extends Fragment {

   private Button filerResetButton,filerApplyButton;
   private EditText pwFilterFirstAge,pwFilterSecondAge;
   private TextView pwFilterFirst,pwFilterSecond;
    String age1;
    String age2;

    public filterFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        filerResetButton = view.findViewById(R.id.filterResetID);
        filerApplyButton = view.findViewById(R.id.filterApplyID);
        pwFilterFirst = view.findViewById(R.id.pwFilterFirstID);
        pwFilterSecond = view.findViewById(R.id.pwFilterSecondID);
        pwFilterFirstAge = view.findViewById(R.id.pwFilterFirstAgeID);
        pwFilterSecondAge = view.findViewById(R.id.pwFilterSecondAgeID);

        pwFilterFirst.setOnClickListener(new View.OnClickListener() {
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

        pwFilterSecond.setOnClickListener(new View.OnClickListener() {
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

        filerResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwFilterFirst.setText("");
                pwFilterSecond.setText("");
                pwFilterFirstAge.setText("");
                pwFilterSecondAge.setText("");
            }
        });

        filerApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String start_date = pwFilterFirst.getText().toString();
                String end_date = pwFilterSecond.getText().toString();
                 age1 = pwFilterFirstAge.getText().toString();
                 age2 = pwFilterSecondAge.getText().toString();
                /* if(age1.isEmpty()){
                     age1 = "0";
                 }if(age2.isEmpty()){
                     age2 = "0";
                 }*/

                 if(start_date.isEmpty()){
                     pwFilterFirst.requestFocus();
                     pwFilterFirst.setError("Enter First date");
                     return;
                 }if(end_date.isEmpty()){
                    pwFilterSecond.requestFocus();
                    pwFilterSecond.setError("Enter Last date");
                    return;
                 }if(age1.isEmpty()){
                    pwFilterFirstAge.requestFocus();
                    pwFilterFirstAge.setError("Enter First Age");
                    return;
                 }if(age2.isEmpty()){
                    pwFilterSecondAge.requestFocus();
                    pwFilterSecondAge.setError("Enter Last Age");
                    return;
                 }

               // Navigation.findNavController(view).navigate(R.id.PW_RecordFragment);

                Bundle bundle1 = new Bundle();
                bundle1.putString("start_date",start_date);
                bundle1.putString("end_date",end_date);

                //Bundle bundle2 = new Bundle();
                bundle1.putInt("start_age",Integer.parseInt(age1));
                bundle1.putInt("end_age",Integer.parseInt(age2));

                Log.e("filter", "onClick: "+start_date);
                Log.e("filter", "onClick: "+end_date);
                Log.e("filter", "onClick: "+age1);
                Log.e("filter", "onClick: "+age2);


                Navigation.findNavController(view).navigate(R.id.action_filterFragment_to_PW_RecordFragment,bundle1);
                pwFilterFirst.setText("");
                pwFilterSecond.setText("");
                pwFilterFirstAge.setText("");
                pwFilterSecondAge.setText("");

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
            pwFilterFirst.setText(selectedDate);

        }
    }

    class OnDateSetListener2Impl implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String selectedDate = sdf.format(calendar.getTime());
            pwFilterSecond.setText(selectedDate);

        }
    }
}