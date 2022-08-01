package com.gs.smc_mmc.fragment;

import android.app.DatePickerDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.CityPouroshovaUnion;
import com.gs.smc_mmc.model.DistrictNameModel;
import com.gs.smc_mmc.model.PWEditModel;
import com.gs.smc_mmc.model.PoProviderModel;
import com.gs.smc_mmc.model.PwRegistrationModel;
import com.gs.smc_mmc.model.ThanaModel;
import com.gs.smc_mmc.model.roomDatabaseModel.BuyerModel;
import com.gs.smc_mmc.model.roomDatabaseModel.DistrictModel;
import com.gs.smc_mmc.model.roomDatabaseModel.DivisionModel;
import com.gs.smc_mmc.model.roomDatabaseModel.HealthConditionModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PWInfoModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PoMappingProviderModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PoMappingThanaModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;
import com.gs.smc_mmc.model.roomDatabaseModel.cityPouroshovaUnionModel;
import com.gs.smc_mmc.roomdatabase.Database;
import com.gs.smc_mmc.serviceApi.RetrofitInstance;
import com.gs.smc_mmc.serviceApi.ServiceApi;
import com.gs.smc_mmc.utils.ConstantUtils;
import com.gs.smc_mmc.view_model.LocationViewModel;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PW_RegistrationFragment extends Fragment {

    private Button saveBtn;
    private LocationViewModel locationViewModel;
    private TextView latLonTV;
    private Spinner patientDivisionSP,patientDistrictSP,patientThanaSP,purchasingSP,healthConditionSP,corporationSP,urbanSP,providerTypenameSP;
    private EditText fullNameET,ageET,mobileET,noPregnancyET,pouroshobhaET,purchase_quantityET;
    private String divisionType,districtType,thanaType,whoPurchasing,healthType;
    private ArrayList<String> divisionNamelist = new ArrayList<>();
    private ArrayList<Integer> divisionIDlist = new ArrayList<>();
    private ArrayList<String> healthConditionNameList = new ArrayList<String>();
    private ArrayList<Integer> healthConditionIDList = new ArrayList<Integer>();
    private ArrayList<String> buyerNameList = new ArrayList<>();
    private ArrayList<Integer> buyerIDList = new ArrayList<>();
    private ArrayList<String> districtNameList = new ArrayList<String>();
    private ArrayList<Integer> districtIDList = new ArrayList<Integer>();
    private ArrayList<String> thanaNameList = new ArrayList<>();
    private ArrayList<Integer> thanaIDList = new ArrayList<Integer>();
    private ArrayList<String> providerNameList = new ArrayList<>();
    private ArrayList<Integer> providerIDList = new ArrayList<>();
    private ArrayList<String> providerThanaAreaNameList = new ArrayList<>();
    private ArrayList<Integer> providerThanaAreaIdList = new ArrayList<>();
    private SimpleDateFormat lmpDateFormat, dateEDDFormat;
    private AutoCompleteTextView actvProviderTypePWFragment;
    private RadioGroup rgCityPouroshobhaPwRegistrationFragment;
    private RadioButton rbCorporationPWRegistrationFragment,rbPouroshobhaPWRegistrationFragment,rbUnionPWRegistrationFragment;
    private String cityDivision = "City Corporation";

    String lat,lng;
    int up = 1, disID = 1,thnID = 1,proID = 1;

    private String divisionId = "0",divisionName,districtId = "0",districtName,thanaId="0",thanaName,healthId="0",healthName,buyerID="0",
            buyerName,corporationOrPoroshoba,corporationOrPoroshobaName,pouroshobha,providerID,providerName,corName,providerThanaAreaId,providerThanaAreaName;
    private JsonObject divisionJsonObject,districtJsonObject,thanaJsonObject;
    private int id = 0,urbanPosition,userId;
    private ArrayList<String> tagTemp;

    String flag = "0";

    private TextView usernameTV,pwRegDateET,eddET,lmpET;
    private String localCityName;
   /* private String urbanList[] = {
            "--Select--","Yse","No"
    };*/
    ArrayList<String> urbanList = new ArrayList<>();
    ArrayList<String> corporationList = new ArrayList<>();
    ArrayList<Integer> corporationIDList = new ArrayList<>();
   /* private String corporationList[] = {
            "--Select--","City Corporation","Pouroshobha"
    };*/




    public PW_RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_p_w__registration, container, false);

        urbanList.add("Yes");
        urbanList.add("No");
       // corporationList.add("--Select--");
        /*corporationList.add("Pouroshobha");*/

        saveBtn = view.findViewById(R.id.saveBtnID);

        usernameTV = view.findViewById(R.id.usernameTV);
        fullNameET = view.findViewById(R.id.fullnameEdt);
        ageET = view.findViewById(R.id.ageEdit);
        mobileET = view.findViewById(R.id.mobileEdt);
        noPregnancyET = view.findViewById(R.id.noPregnancyID);
        lmpET = view.findViewById(R.id.pwLMPID);
        eddET = view.findViewById(R.id.pwEDDID);

        patientDivisionSP = view.findViewById(R.id.pwDivisionSP);
        patientDistrictSP = view.findViewById(R.id.pwDistrictSP);
        patientThanaSP = view.findViewById(R.id.pwThanaSP);
        purchasingSP = view.findViewById(R.id.pwWhoPurchasingSP);
        healthConditionSP = view.findViewById(R.id.pwHealthConditionSP);
        corporationSP = view.findViewById(R.id.pwCorporationSP);
        urbanSP = view.findViewById(R.id.pwUrbanSP);
        pouroshobhaET = view.findViewById(R.id.pwPouroshobhaET);
       // providerTypenameSP = view.findViewById(R.id.providerTypenameSP);
        providerTypenameSP = view.findViewById(R.id.providerThanaTypeSP);
        pwRegDateET = view.findViewById(R.id.pwRegDate);
        purchase_quantityET = view.findViewById(R.id.purchase_quantityET);
        actvProviderTypePWFragment = view.findViewById(R.id.actvProviderTypePWFragment);
        rgCityPouroshobhaPwRegistrationFragment = view.findViewById(R.id.rgCityPouroshobhaPwRegistrationFragment);
        rbCorporationPWRegistrationFragment = view.findViewById(R.id.rbCorporationPWRegistrationFragment);
        rbPouroshobhaPWRegistrationFragment = view.findViewById(R.id.rbPouroshobhaPWRegistrationFragment);
        rbUnionPWRegistrationFragment = view.findViewById(R.id.rbUnionPWRegistrationFragment);


       // actvProviderTypePWFragment.setFocusable(true);
        //actvProviderTypePWFragment.setFocusableInTouchMode(true);
        //actvProviderTypePWFragment.requestFocus();
        
        
       

        actvProviderTypePWFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvProviderTypePWFragment.showDropDown();
            }
        });

        saveBtn.setClickable(true);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

      /*  Log.e("Calenderdate", "onCreateView: "+day+" "+(month+1)+" "+year );
        pwRegDateET.setText(""+year+"/"+(month+1)+"/"+day);*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        pwRegDateET.setText(date);

        Log.e("Calenderdate", "onCreateView: "+date );


        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        locationViewModel.getLocationMutableLiveData().observe(getActivity(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                 lat = String.valueOf(location.getLatitude());
                 lng = String.valueOf(location.getLongitude());

            }
        });


        try {
            id = getArguments().getInt("ID",-1);
            Log.e("ID", "onCreateView: "+id );
            userId = ConstantUtils.Temp.id;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(id > 0){
            ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action","EDIT");
            jsonObject.addProperty("id",id);
            jsonObject.addProperty("user_id",userId);
            Log.e("ID", "onCreateView: "+id );
            Log.e("jsonValue", "onCreateView: "+jsonObject.toString() );

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<PWInfoModel> pWInfoModellList = Database.getInstance(getContext()).getProviderDao().getSinglePWInfo(String.valueOf(id));
                    Log.e("sizeOfPWInfoModellList", "doInBackground: "+pWInfoModellList.size());

                    try {
                        corporationOrPoroshoba = pWInfoModellList.get(0).getCityPouroshobhaId();
                        fullNameET.setText( pWInfoModellList.get(0).getName());
                        ageET.setText(pWInfoModellList.get(0).getAge());
                        mobileET.setText(pWInfoModellList.get(0).getMobileNo());
                        lmpET.setText(pWInfoModellList.get(0).getLmp());
                        eddET.setText(pWInfoModellList.get(0).getEdd());
                        purchase_quantityET.setText(pWInfoModellList.get(0).getPurchaseQuantity());

                        pwRegDateET.setText(pWInfoModellList.get(0).getRegistrationDate());
                        noPregnancyET.setText(pWInfoModellList.get(0).getNumberOfPregnancy());
                        try {

                            pouroshobhaET.setText(pWInfoModellList.get(0).getCityPouroshobhaName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("fullDetailsError", "onResponse: "+e.getLocalizedMessage() );
                        }


                    }catch (Exception e){}

                    try {
                        providerThanaAreaId = pWInfoModellList.get(0).getProviderMappingThanaId();
                    }catch (Exception e){}

                    try{
                        providerNameList.clear();
                        providerIDList.clear();

                        providerID = pWInfoModellList.get(0).getProviderId();
                        providerName = pWInfoModellList.get(0).getProviderName();




                        //  providerNameList.add(response.body().getPwViewEdit().get(0).getInfo().getProviderName());
                        //     providerIDList.add(Integer.parseInt(response.body().getPwViewEdit().get(0).getInfo().getProviderId()));
                        Log.e("providerThanaAreaId", "onResponse: "+providerThanaAreaId );
                        Log.e("providerID", "onResponse: "+pWInfoModellList.get(0).getProviderName() );

                        setproviderSpinner();
                        setProviderList();


                        //   providerID = response.body().getPwViewEdit().get(0).getInfo().getProviderId();
                        Log.e("providerID1", "onResponse: "+providerID );

                    }catch (Exception e){}

                    ///divisionNamelist
                    try {
                        //divisionNamelist.clear();
                        //divisionIDlist.clear();
                        divisionId = pWInfoModellList.get(0).getDivisionId();
                        setDivisionSpinner();
                        // divisionNamelist.add(response.body().getPwViewEdit().get(0).getInfo().getDivisionName());

                        Log.e("divisionId", "onResponse: "+divisionId );
                        //patientDivisionSP.setSelection(divisionIDlist.indexOf(Integer.parseInt(divisionId)));
                        Log.e("divisionId_index", "onResponse: "+divisionIDlist.indexOf(Integer.parseInt(divisionId)) );


                    }catch (Exception e){
                        Log.e("divisionError", "onResponse: "+e.getLocalizedMessage() );
                    }

                    //district
                    try {
                        districtNameList.clear();
                        districtIDList.clear();
                        //  districtNameList.add(response.body().getPwViewEdit().get(0).getInfo().getDistrictName());
                        districtId = pWInfoModellList.get(0).getDistrictId();
                        setDistrictSpinner();
                        Log.e("districtsId", "onResponse: "+districtId );

                    }catch (Exception e){}

                    //thana
                    try {
                        // thanaNameList.add("--Select--");
                        //thanaIDList.add(0);
                        thanaNameList.clear();
                        thanaNameList.add(pWInfoModellList.get(0).getThanaName());
                        thanaId = pWInfoModellList.get(0).getThanaId();

                    }catch (Exception e){
                        Log.e("thana", "onResponse: "+e);
                    }

                    ///cityDivision
                    try {
                        cityDivision = pWInfoModellList.get(0).getCityPouroshobha();
                        Log.e("cityDivision", "onResponse: "+cityDivision);
                        radioButtonData();
                    }catch (Exception e){}

                    ///buyerNameList
                    try{
                        buyerNameList.clear();
                        buyerIDList.clear();
                        buyerID = pWInfoModellList.get(0).getBuyerId();
                        Log.e("buyerID", "onResponse: "+buyerID);
                        setBuyerSpinner();
                    }catch (Exception e){}

                    ///healthConditionNameList
                    try {
                        healthConditionIDList.clear();
                        healthConditionNameList.clear();
                        healthId = pWInfoModellList.get(0).getHealthConditionId();
                        Log.e("healthId", "onResponse: "+healthId);
                        setHealthSpinner();
                    }catch (Exception e){}

                    ///isUrban
                    //urbanList = new String[] {response.body().getPwViewEdit().get(0).getInfo().getBuyerName()};
                    try{

                        urbanList.clear();

                        //urbanList.add(response.body().getPwViewEdit().get(0).getInfo().getIsUrban());
                       // urbanPosition = Integer.parseInt(pWInfoModellList.get(0).getIsUrban());

                        if(urbanPosition==1){
                            urbanList.add("YES");
                            urbanList.add("NO");
                            urbanPosition = 1;
                            up = 1;
                            Log.e("urbanPosition", "onResponse: "+urbanPosition );
                        }else {
                            urbanList.add("NO");
                            urbanList.add("YES");
                            urbanPosition = 1;
                            up = 2;
                        }
                        ArrayAdapter<String> Urbanadapter = new ArrayAdapter<String>(
                                getContext(),R.layout.custom_layout,urbanList);

                        Urbanadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        urbanSP.setAdapter(Urbanadapter);


                    }catch (Exception e){}

                    ///city corporation
                    try {


                        corporationList.clear();
                        corporationIDList.clear();



                        corName = pWInfoModellList.get(0).getCityPouroshobhaName();
                        Log.e("corName", "onResponse: "+corporationOrPoroshoba);
                        // corporationList.add(corName);
                        setCityCorporationSpinner();

                        // corporationList.remove(corName);



                        //    corporationAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        //   corporationSP.setAdapter(corporationAdepter);
                    }catch (Exception e){
                        Log.e("corporationList", "onResponse: "+e );
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);
                }
            }.execute();

          /*  serviceApi.setPWEdit(jsonObject).enqueue(new Callback<PWEditModel>() {
                @Override
                public void onResponse(Call<PWEditModel> call, Response<PWEditModel> response) {
                    Log.e("fdfd", "onResponse: "+response.body() );
                    if(response.isSuccessful()){

                        try {
                            corporationOrPoroshoba = response.body().getPwViewEdit().get(0).getInfo().getCityPouroshobhaId();
                            fullNameET.setText( response.body().getPwViewEdit().get(0).getInfo().getName());
                            ageET.setText(response.body().getPwViewEdit().get(0).getInfo().getAge());
                            mobileET.setText(response.body().getPwViewEdit().get(0).getInfo().getMobileNo());
                            lmpET.setText(response.body().getPwViewEdit().get(0).getInfo().getLmp());
                            eddET.setText(response.body().getPwViewEdit().get(0).getInfo().getEdd());
                            purchase_quantityET.setText(response.body().getPwViewEdit().get(0).getInfo().getPurchaseQuantity());

                            pwRegDateET.setText(response.body().getPwViewEdit().get(0).getInfo().getRegistrationDate());
                            noPregnancyET.setText(response.body().getPwViewEdit().get(0).getInfo().getNumberOfPregnancy());
                            try {

                                  pouroshobhaET.setText(response.body().getPwViewEdit().get(0).getInfo().getCityPouroshobhaName());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("fullDetailsError", "onResponse: "+e.getLocalizedMessage() );
                            }


                        }catch (Exception e){}

                        try {
                            providerThanaAreaId = response.body().getPwViewEdit().get(0).getInfo().getProviderMappingThanaId();
                        }catch (Exception e){}


                        try{
                            providerNameList.clear();
                            providerIDList.clear();

                           providerID = response.body().getPwViewEdit().get(0).getInfo().getProviderId();




                          //  providerNameList.add(response.body().getPwViewEdit().get(0).getInfo().getProviderName());
                       //     providerIDList.add(Integer.parseInt(response.body().getPwViewEdit().get(0).getInfo().getProviderId()));
                            Log.e("providerThanaAreaId", "onResponse: "+providerThanaAreaId );
                            Log.e("providerID", "onResponse: "+response.body().getPwViewEdit().get(0).getInfo().getProviderName() );

                            setproviderSpinner();
                            setProviderList();


                         //   providerID = response.body().getPwViewEdit().get(0).getInfo().getProviderId();
                            Log.e("providerID1", "onResponse: "+providerID );

                        }catch (Exception e){}



                        ///corporationNameList
                       *//* try {
                            corporationIDList.clear();
                            corporationList.clear();
                            corName = response.body().getPwViewEdit().get(0).getInfo().getCityPouroshobhaName();
                            //Log.e("corName", "onResponse: "+corName );
                           // setCityCorporationSpinner();
                        }catch (Exception e){}*//*

                        ///divisionNamelist
                        try {
                            //divisionNamelist.clear();
                            //divisionIDlist.clear();
                            divisionId = response.body().getPwViewEdit().get(0).getInfo().getDivisionId();
                            setDivisionSpinner();
                           // divisionNamelist.add(response.body().getPwViewEdit().get(0).getInfo().getDivisionName());

                            Log.e("divisionId", "onResponse: "+divisionId );
                            //patientDivisionSP.setSelection(divisionIDlist.indexOf(Integer.parseInt(divisionId)));
                            Log.e("divisionId_index", "onResponse: "+divisionIDlist.indexOf(Integer.parseInt(divisionId)) );


                        }catch (Exception e){
                            Log.e("divisionError", "onResponse: "+e.getLocalizedMessage() );
                        }


                        try {
                            districtNameList.clear();
                            districtIDList.clear();
                          //  districtNameList.add(response.body().getPwViewEdit().get(0).getInfo().getDistrictName());
                            districtId = response.body().getPwViewEdit().get(0).getInfo().getDistrictId();
                            setDistrictSpinner();
                            Log.e("districtsId", "onResponse: "+districtId );

                        }catch (Exception e){}

                        try {
                            // thanaNameList.add("--Select--");
                            //thanaIDList.add(0);
                            thanaNameList.clear();
                           thanaNameList.add(response.body().getPwViewEdit().get(0).getInfo().getThanaName());
                            thanaId = response.body().getPwViewEdit().get(0).getInfo().getThanaId();

                        }catch (Exception e){
                            Log.e("thana", "onResponse: "+e);
                        }

                        ///cityDivision
                        try {
                            cityDivision = response.body().getPwViewEdit().get(0).getInfo().getCityPouroshobha();
                            Log.e("cityDivision", "onResponse: "+cityDivision);
                            radioButtonData();
                        }catch (Exception e){}

                        ///buyerNameList
                        try{
                            buyerNameList.clear();
                            buyerIDList.clear();
                            buyerID = response.body().getPwViewEdit().get(0).getInfo().getBuyerId();
                            Log.e("buyerID", "onResponse: "+buyerID);
                            setBuyerSpinner();
                        }catch (Exception e){}

                        ///healthConditionNameList
                        try {
                            healthConditionIDList.clear();
                            healthConditionNameList.clear();
                            healthId = response.body().getPwViewEdit().get(0).getInfo().getHealthConditionId();
                            Log.e("healthId", "onResponse: "+healthId);
                            setHealthSpinner();
                        }catch (Exception e){}

                        ///isUrban
                        //urbanList = new String[] {response.body().getPwViewEdit().get(0).getInfo().getBuyerName()};
                        try{

                            urbanList.clear();

                            //urbanList.add(response.body().getPwViewEdit().get(0).getInfo().getIsUrban());
                            urbanPosition = Integer.parseInt(response.body().getPwViewEdit().get(0).getInfo().getIsUrban());

                            if(urbanPosition==1){
                                urbanList.add("YES");
                                urbanList.add("NO");
                                urbanPosition = 1;
                                up = 1;
                                Log.e("urbanPosition", "onResponse: "+urbanPosition );
                            }else {
                                urbanList.add("NO");
                                urbanList.add("YES");
                                urbanPosition = 1;
                                up = 2;
                            }
                            ArrayAdapter<String> Urbanadapter = new ArrayAdapter<String>(
                                    getContext(),R.layout.custom_layout,urbanList);

                            Urbanadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            urbanSP.setAdapter(Urbanadapter);


                        }catch (Exception e){}

                        ///city corporation
                        try {


                            corporationList.clear();
                            corporationIDList.clear();



                            corName = response.body().getPwViewEdit().get(0).getInfo().getCityPouroshobhaName();
                            Log.e("corName", "onResponse: "+corporationOrPoroshoba);
                           // corporationList.add(corName);
                            setCityCorporationSpinner();

                           // corporationList.remove(corName);



                        //    corporationAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                         //   corporationSP.setAdapter(corporationAdepter);
                        }catch (Exception e){
                            Log.e("corporationList", "onResponse: "+e );
                        }





                        //  healthET.setText(response.body().getPwViewEdit().get(0).getInfo().getHealthConditionName());

                    }
                }

                @Override
                public void onFailure(Call<PWEditModel> call, Throwable t) {
                    Log.e("fai", "onFailure: "+t.getLocalizedMessage() );
                }
            });*/

        }


        Log.e("id", "onCreateView: "+id );




//........................................XXXXXXXXXXXX....................................................
        ///ProviderSP

        if(id==0){
            radioButtonData();
            setproviderSpinner();
            setRadioButtonData();
            //setProviderList();
            setDivisionSpinner();
            setDistrictSpinner();
            setThanaSpinner();
            setBuyerSpinner();
            setHealthSpinner();
            //setCityCorporationSpinner();
        }



        //Who purchasingSP


        //healthConditionSP




        ///Urban

        ArrayAdapter<String> Urbanadapter = new ArrayAdapter<String>(
                getContext(),R.layout.custom_layout,urbanList);

        Urbanadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        urbanSP.setAdapter(Urbanadapter);


        urbanSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  String selectedType = urbanList[position];
                 int pos = urbanSP.getSelectedItemPosition();
                urbanPosition = pos + 1;
                Log.e("urbanSP", "onItemSelected: "+urbanPosition );
                //Toast.makeText(getActivity(), ""+urbanSP.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), ""+selectedType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //City Corporation




       lmpET.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calendar calendar = Calendar.getInstance();
               int day = calendar.get(Calendar.DAY_OF_MONTH);
               int year = calendar.get(Calendar.YEAR);
               int month = calendar.get(Calendar.MONTH);


               DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       Calendar calendar = Calendar.getInstance();
                       calendar.set(year,month,dayOfMonth);
                       lmpDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                       String selectedDate = lmpDateFormat.format(calendar.getTime());
                       lmpET.setText(selectedDate);
                   }
               }, year, month, day);

               datePickerDialog.show();
           }
       });

        eddET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year,month,dayOfMonth);
                         dateEDDFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String selectedDate = dateEDDFormat.format(calendar.getTime());
                        eddET.setText(selectedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        pwRegDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String selectedDate = dateFormat.format(calendar.getTime());
                        pwRegDateET.setText(selectedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });





        saveBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


              //  providerID = usernameTV.getText().toString();
                String pouroshobha = pouroshobhaET.getText().toString();
                String fullName = fullNameET.getText().toString();
                Log.e("fullName", "onClick: "+fullName );
                String age = ageET.getText().toString();
                Log.e("fullName", "onClick: "+age );
                String mobile = mobileET.getText().toString();
                String noOfPregnancy = noPregnancyET.getText().toString();
                String pwlmp = lmpET.getText().toString();
                String pwedd = eddET.getText().toString();
                String regDate = pwRegDateET.getText().toString();
                String purchase_quantity = purchase_quantityET.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date strPWDate = null;
                Date strEDDDate = null;
                try {
                    strPWDate = sdf.parse(pwlmp);
                    strEDDDate = sdf.parse(pwedd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    if (strEDDDate.getTime() > strPWDate.getTime()) {

                      //  Toast.makeText(getActivity(), "sdfdsfds", Toast.LENGTH_SHORT).show();
                        try {
                            //Dates to compare
                            String CurrentDate=  pwlmp;
                            String FinalDate=  pwedd;

                            Date date1;
                            Date date2;

                            LocalDate dateBefore = LocalDate.parse(CurrentDate);
                            LocalDate dateAfter = LocalDate.parse(FinalDate);
                            long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
                            Log.e("noOfDaysBetween", "onClick: "+noOfDaysBetween);


                            if(noOfDaysBetween>=186 && noOfDaysBetween<=310){
                                // Toast.makeText(getActivity(), "differenceDates"+differenceDates, Toast.LENGTH_SHORT).show();
                                Log.e("differenceDates", "onClick: "+noOfDaysBetween+"differenceDates" );
                            }else {
                                Toast.makeText(getActivity(), "Invalid LMP/EDD", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (Exception exception) {
                            Log.e("DIDN'T WORK", "exception " + exception);
                        }





                    }else {
                        Toast.makeText(getActivity(), "Invalid LMP/EDD ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                /*if(pouroshobha.isEmpty()){
                    pouroshobhaET.setError("Enter your pouroshobha");
                    pouroshobhaET.requestFocus();
                    return;
                }*/if(fullName.isEmpty()){
                    fullNameET.setError("Enter your name");
                    fullNameET.requestFocus();
                    return;
                }if(age.isEmpty()){
                    ageET.setError("Enter your age");
                    ageET.requestFocus();
                    return;
                }if(mobile.isEmpty()){
                    mobileET.setError("Enter your mobile number");
                    mobileET.requestFocus();
                    return;
                } if (mobile.length() < 10){

                    Toast.makeText(getActivity(), "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;

                }/*if(noOfPregnancy.isEmpty()){
                    noPregnancyET.setError("Enter your pregnancy number");
                    noPregnancyET.requestFocus();
                    return;
                }if(pwlmp.isEmpty()){
                    Toast.makeText(getActivity(), "Enter LMP", Toast.LENGTH_SHORT).show();
                    return;
                }if(pwedd.isEmpty()){
                    Toast.makeText(getActivity(), "Enter EDD", Toast.LENGTH_SHORT).show();
                    return;
                }if(regDate.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Registration Date", Toast.LENGTH_SHORT).show();
                    return;
                }if(purchase_quantity.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Purchase quantity", Toast.LENGTH_SHORT).show();
                    return;
                }*/




                if(id > 0){

                    if(urbanPosition==0){
                        urbanPosition = up;
                    }


                    Log.e("providerNameUpdate", "onClick: "+providerName );
                    /*if(providerID.equals("0")){
                        Toast.makeText(getActivity(), "Select Provider", Toast.LENGTH_SHORT).show();
                        return;
                    }*/
                    if(Database.getInstance(getContext()).getProviderDao().searchProvider(actvProviderTypePWFragment.getText().toString() ) <= 0){
                        Toast.makeText(getActivity(), "Select Provider", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        String name = actvProviderTypePWFragment.getText().toString();
                        providerName = name;
                        providerID = Database.getInstance(getContext()).getProviderDao().getProviderID(actvProviderTypePWFragment.getText().toString());
                        Log.e("FragmentName", "onClick: "+name );
                        Log.e("FragmentName", "onClick: "+providerID );
                    }

                    int p = Integer.parseInt(providerID);
                    int ds = Integer.parseInt(districtId);
                    /*if(disID == 0){
                        providerID = String.valueOf(proID);
                        Log.e("prrID", "onClick: "+providerID );
                    }*/

                    Log.e("prrID", "onClick: "+providerID );

                    Log.e("urbanPosition", "onClick: "+urbanPosition );
                    Log.e("urbanPosition", "onClick: "+up );
                    final JsonObject jsonObject = new JsonObject();

                    jsonObject.addProperty("provider_id", providerID);
                    jsonObject.addProperty("name", fullName);
                    Log.e("name", "onClick: "+fullName );
                    jsonObject.addProperty("age", age);
                    jsonObject.addProperty("purchase_quantity", purchase_quantity);
                    jsonObject.addProperty("division_id", divisionId);
                    jsonObject.addProperty("district_id", districtId);
                    jsonObject.addProperty("thana_id", thanaId);
                    jsonObject.addProperty("city_pouroshobha", cityDivision);
                    jsonObject.addProperty("city_pouroshobha_name", corporationOrPoroshoba);
                    jsonObject.addProperty("is_urban", urbanPosition);
                    jsonObject.addProperty("mobile_no", mobile);
                    jsonObject.addProperty("is_mob_personal", "25");
                    jsonObject.addProperty("number_of_pregnancy", noOfPregnancy);
                    jsonObject.addProperty("buyer_id", buyerID);
                    jsonObject.addProperty("lmp", pwlmp);
                    jsonObject.addProperty("edd", pwedd);
                    jsonObject.addProperty("health_condition_id", healthId);
                    jsonObject.addProperty("comments", "gfsdg");
                    jsonObject.addProperty("registration_date", regDate);
                    jsonObject.addProperty("user_id", userId);
                    jsonObject.addProperty("action", "ADD-EDIT");
                    jsonObject.addProperty("latitude", lat);
                    jsonObject.addProperty("longitude", lng);
                    jsonObject.addProperty("id", id);

                    fullNameET.setText("");
                    ageET.setText("");
                    mobileET.setText("");
                    pouroshobhaET.setText("");
                    noPregnancyET.setText("");
                    lmpET.setText("");
                    eddET.setText("");
                    pwRegDateET.setText("");
                    purchase_quantityET.setText("");

                    Log.e("jsonObject", "onClick: "+jsonObject.toString());

                   // ServiceApi serviceApi1 = RetrofitInstance.retrofit.create(ServiceApi.class);


                    updatePwInfoDatabase(fullName,age,mobile,noOfPregnancy,pwlmp,pwedd,regDate,purchase_quantity,String.valueOf(id));
                    // Navigation.findNavController(view).navigate(R.id.PW_RecordFragment);
                    Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(view).popBackStack();


                   /* serviceApi1.pushPwRegistration(jsonObject).enqueue(new Callback<PwRegistrationModel>() {
                        @Override
                        public void onResponse(Call<PwRegistrationModel> call, Response<PwRegistrationModel> response) {
                            Log.e("SaveData", "onResponse: Save "+response.body());

                            if(response.isSuccessful()){
                                saveBtn.setClickable(false);

                                //    Log.e("pwRegis", "onResponse: "+response.body().getPwAddEdit().get(0).getProviderList().);
                                //   Log.e("pwRegis", "onResponse: "+response.body().getPwViewEdit().get(0).getInfo().getAge());
                                Log.e("SaveData", "onResponse: Save");

                                if(fullName.isEmpty() && age.isEmpty() && mobile.isEmpty()){
                                    Toast.makeText(getActivity(), "Could not Save.Please try again!!", Toast.LENGTH_SHORT).show();
                                    saveBtn.setClickable(true);
                                    Log.e("SaveData", "onResponse: Could not Save.Please try again!!");

                                    //Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
                                    // Navigation.findNavController(view).navigate(R.id.PW_RecordFragment);
                                }else {

                                    //Navigation.findNavController(view).navigate(R.id.PW_RecordFragment);

                                    try {
                                        Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
                                        Log.e("SaveData", "onResponse: Save");

                                        Navigation.findNavController(view).popBackStack();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e("SaveData", "onResponse: "+e.getLocalizedMessage());

                                    }
                                    ///...............................///


                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<PwRegistrationModel> call, Throwable t) {
                            Log.e("t", "onFailure: "+t.getMessage() );
                        }
                    });*/
                    //Log.e("id", "onClick: "+id );

                }else{

                 //   int mob = Integer.parseInt(mobile);




                    if(providerID==null){
                        Toast.makeText(getActivity(), "Select Provider", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Database.getInstance(getContext()).getProviderDao().searchProvider(actvProviderTypePWFragment.getText().toString() ) <= 0){
                        Toast.makeText(getActivity(), "Select Provider", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        String name = actvProviderTypePWFragment.getText().toString();
                        providerName = name;
                        providerID = Database.getInstance(getContext()).getProviderDao().getProviderID(actvProviderTypePWFragment.getText().toString());
                        Log.e("FragmentName", "onClick: "+name );
                        Log.e("FragmentName", "onClick: "+providerID );
                    }
                    /*if(divisionId==null){
                        Toast.makeText(getActivity(), "Select Division", Toast.LENGTH_SHORT).show();
                        return;
                    }if(districtId==null){
                        Toast.makeText(getActivity(), "Select District", Toast.LENGTH_SHORT).show();
                        return;
                    }if(thanaId==null){
                        Toast.makeText(getActivity(), "Select Thana", Toast.LENGTH_SHORT).show();
                        return;
                    }if(buyerID==null){
                        Toast.makeText(getActivity(), "Select Buyer", Toast.LENGTH_SHORT).show();
                        return;
                    }if(healthId==null){
                        Toast.makeText(getActivity(), "Select Health Condition", Toast.LENGTH_SHORT).show();
                        return;
                    }*/


                    final JsonObject jsonObject1 = new JsonObject();
                    jsonObject1.addProperty("provider_id", providerID);
                    jsonObject1.addProperty("name", fullName);
                    jsonObject1.addProperty("age", age);
                    jsonObject1.addProperty("purchase_quantity", purchase_quantity);
                    jsonObject1.addProperty("division_id", divisionId);
                    jsonObject1.addProperty("district_id", districtId);
                    jsonObject1.addProperty("thana_id", thanaId);
                    jsonObject1.addProperty("city_pouroshobha", cityDivision);
                    jsonObject1.addProperty("city_pouroshobha_name", corporationOrPoroshoba);
                    jsonObject1.addProperty("is_urban", urbanPosition);
                    jsonObject1.addProperty("mobile_no", mobile);
                    jsonObject1.addProperty("is_mob_personal", "25");
                    jsonObject1.addProperty("number_of_pregnancy", noOfPregnancy);
                    jsonObject1.addProperty("buyer_id", buyerID);
                    jsonObject1.addProperty("lmp", pwlmp);
                    jsonObject1.addProperty("edd", pwedd);
                    jsonObject1.addProperty("health_condition_id", healthId);
                    jsonObject1.addProperty("comments", "gfsdg");
                    jsonObject1.addProperty("registration_date", regDate);
                    jsonObject1.addProperty("user_id", userId);
                    jsonObject1.addProperty("action", "ADD-EDIT");
                    jsonObject1.addProperty(  "latitude", lat);
                    jsonObject1.addProperty(  "longitude", lng);
                    jsonObject1.addProperty("id", "0");

                    Log.e("pw", "onClick: "+jsonObject1.toString() );

                    fullNameET.setText("");
                    ageET.setText("");
                    mobileET.setText("");
                    pouroshobhaET.setText("");
                    noPregnancyET.setText("");
                    lmpET.setText("");
                    eddET.setText("");
                    pwRegDateET.setText("");


                    ServiceApi serviceApi1 = RetrofitInstance.retrofit.create(ServiceApi.class);



                    try {
                       /* serviceApi1.pushPwRegistration(jsonObject1).enqueue(new Callback<PwRegistrationModel>() {
                            @Override
                            public void onResponse(Call<PwRegistrationModel> call, Response<PwRegistrationModel> response) {
                                if(response.isSuccessful()){
                                 //   saveBtn.setClickable(false);
                                    if(response.body().getPwAddEdit().get(0).getStatus()==-1){
                                        try {
                                            Toast.makeText(getActivity(), "Mobile Number Already Exits. Please, try again.", Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        return;
                                    }

                                    if(fullName.isEmpty() && age.isEmpty() && mobile.isEmpty() &&
                                            noOfPregnancy.isEmpty() && regDate.isEmpty() && pwlmp.isEmpty() &&
                                            pouroshobha.isEmpty() && pwedd.isEmpty() && divisionId==null){
                                        Toast.makeText(getActivity(), "Could not Save.Please try again!!", Toast.LENGTH_SHORT).show();
                                        saveBtn.setClickable(true);

                                        //Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
                                        // Navigation.findNavController(view).navigate(R.id.PW_RecordFragment);
                                    }else {


                                        //  Navigation.findNavController(view).navigate(R.id.PW_RecordFragment);
                                        try {
                                            Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(view).popBackStack();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }


                                    }

                                }else {

                                }

                            }

                            @Override
                            public void onFailure(Call<PwRegistrationModel> call, Throwable t) {
                                //   Log.e("pw", "onFailure: "+t.getLocalizedMessage() );

                            }
                        });*/
                        saveDataLocalDatabase(fullName,age,mobile,noOfPregnancy,pwlmp,pwedd,regDate,purchase_quantity);
                       // Navigation.findNavController(view).navigate(R.id.PW_RecordFragment);
                        Toast.makeText(getActivity(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).popBackStack();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                //int divId = Integer.parseInt(divisionId);



            }
        });


        return view;
    }


    private void radioButtonData() {

          if(id > 0){
                    switch (cityDivision){
                        case "City Corporation" :
                            rbCorporationPWRegistrationFragment.setChecked(true);
                            break;

                        case "Pouroshobha" :
                            rbPouroshobhaPWRegistrationFragment.setChecked(true);
                            break;

                        case "Union" :
                            rbUnionPWRegistrationFragment.setChecked(true);
                            break;
                    }

                }

        rgCityPouroshobhaPwRegistrationFragment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = getActivity().findViewById(checkedId);
                cityDivision = rb.getText().toString();
               // Toast.makeText(getContext(), ""+cityDivision, Toast.LENGTH_SHORT).show();

                setCityCorporationSpinner();
            }
        });
    }

    private void setRadioButtonData() {
    }


    private void setThanaSpinner() {

        if(getActivity()!=null){
            ArrayAdapter<String> thanaAdapter = new ArrayAdapter<>(
                    getActivity(),R.layout.custom_layout,
                    thanaNameList);
            thanaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            patientThanaSP.setAdapter(thanaAdapter);
        }



        if(id > 0){
            try {
                patientThanaSP.setSelection(thanaIDList.indexOf(Integer.parseInt(thanaId)));
                Log.e("patientThanaSP", "setThanaSpinner: "+thanaIDList.indexOf(Integer.parseInt(thanaId))+"thanaID"+thanaId);
            }catch (Exception e){}

        }
        patientThanaSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {
                    thanaId = String.valueOf(thanaIDList.get(position));
                    thanaName = String.valueOf(thanaNameList.get(position));
                    //Toast.makeText(getContext(), selectedType, Toast.LENGTH_SHORT).show();
                    Log.e("thanaName", "onItemSelected: "+thanaName );
                    setCityCorporationSpinner();
                }catch (Exception e){}

               /* if(id > 0){

                }else {
                    setCityCorporationSpinner();
                }*/


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setDistrictSpinner() {

        if(getActivity()!=null){
            ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(
                    getActivity(),R.layout.custom_layout,
                    districtNameList);

            districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            patientDistrictSP.setAdapter(districtAdapter);
        }



          if(id > 0){
              try {
                  patientDistrictSP.setSelection(districtIDList.indexOf(Integer.parseInt(districtId)));
                  Log.e("patientsDistrict", "setDistrictSpinner: "+districtIDList.indexOf(Integer.parseInt(districtId))+"districtId"+districtId);
              }catch (Exception e){}
          }
        patientDistrictSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    districtId = String.valueOf(districtIDList.get(position));
                    districtName = String.valueOf(districtNameList.get(position));
                }catch (Exception e){}


                Log.e("districtName", "onItemSelected: "+districtName );
                //Toast.makeText(getContext(), ""+districtId, Toast.LENGTH_SHORT).show();


                thanaJsonObject = new JsonObject();
                thanaJsonObject.addProperty("division_id",divisionId);
                thanaJsonObject.addProperty("district_id",districtId);

                // Log.e("thanaJson",new Gson().toJson(thanaJsonObject));
              //  ServiceApi thanaApi = RetrofitInstance.retrofit.create(ServiceApi.class);
                thanaNameList.clear();
                thanaIDList.clear();
                thanaNameList.add("--Select--");
                thanaIDList.add(0);
               /* thanaApi.getAllThana(thanaJsonObject).enqueue(new Callback<ThanaModel>() {
                    @Override
                    public void onResponse(Call<ThanaModel> call, Response<ThanaModel> response) {
                        if(response.isSuccessful()){
                            //   Log.e("f", "onResponse: Success" );
                            try {
                                if(response.isSuccessful()){
                                    for(int thana=0;thana<response.body().getThana().size();thana++){
                                        thanaNameList.add(response.body().getThana().get(thana).getName());
                                        thanaIDList.add(Integer.parseInt(response.body().getThana().get(thana).getId()));
                                        // thanaId = response.body().getThana().get(thana).getId();
                                        //     Log.e("ff", "onResponse: "+Integer.parseInt(response.body().getThana().get(thana).getId()));
                                        Log.e("thana", "onResponse: "+response.body().getThana().get(thana).getName());
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            setThanaSpinner();
                        }
                    }

                    @Override
                    public void onFailure(Call<ThanaModel> call, Throwable t) {

                    }
                });*/



                try {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            List<com.gs.smc_mmc.model.roomDatabaseModel.ThanaModel> allThana = Database.getInstance(getActivity()).getProviderDao().getAllThana(divisionId,districtId);
                            for(int thana=0;thana<allThana.size();thana++){
                                thanaNameList.add(allThana.get(thana).getThanaName());
                                thanaIDList.add(Integer.parseInt(allThana.get(thana).getThanaID()));
                                // thanaId = response.body().getThana().get(thana).getId();
                                //     Log.e("ff", "onResponse: "+Integer.parseInt(response.body().getThana().get(thana).getId()));
                                Log.e("thana", "onResponse: "+allThana.get(thana).getThanaName());
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);
                            setThanaSpinner();
                        }
                    }.execute();
                }catch (Exception e){}


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setCityCorporationSpinner() {


        corporationList.clear();
        corporationIDList.clear();
        corporationList.add("--Select--");
        corporationIDList.add(0);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("city_poroshova_union_type", cityDivision);
        jsonObject.addProperty("thana_id", thanaId);
        Log.e("getCpuList", "setCityCorporationSpinner: " + jsonObject.toString());
        ServiceApi serviceApi = RetrofitInstance.retrofit.create(ServiceApi.class);
        Call<CityPouroshovaUnion> call = serviceApi.get_city_poroshova_union_list(jsonObject);
      /*  call.enqueue(new Callback<CityPouroshovaUnion>() {
            @Override
            public void onResponse(Call<CityPouroshovaUnion> call, Response<CityPouroshovaUnion> response) {
                try {
                    if (response.isSuccessful()) {
                        if( response.body().getCpuList().getCpuList().size()==0){
                            corporationList.clear();
                            corporationIDList.clear();
                            corporationList.add("--Select--");
                            corporationIDList.add(0);
                        }else {
                            Log.e("getCpuList", "onResponse: " + response.body().getCpuList().getCpuList().size());

                            Log.e("corporationListCheck", new Gson().toJson(corporationList));
                            for (int i = 0; i < response.body().getCpuList().getCpuList().size(); i++) {
                                Log.e("getCpuList", "onResponse: " + response.body().getCpuList().getCpuList().get(i).getId());
                                corporationList.add(response.body().getCpuList().getCpuList().get(i).getName());
                                corporationIDList.add(Integer.parseInt(response.body().getCpuList().getCpuList().get(i).getId()));
                                Log.e("corporationIDList", "onResponse: " + response.body().getCpuList().getCpuList().get(i).getName());
                        }
                            Log.e("corporationListCheck", new Gson().toJson(corporationList));
                            Log.e("corporationListCheck", new Gson().toJson(corporationIDList));
                        }


                    }

                }catch (Exception e){}



                if (getActivity() != null) {
                    ArrayAdapter<String> corporationAdepter = new ArrayAdapter<String>(
                            getContext(), R.layout.custom_layout, corporationList
                    );

                    corporationAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    corporationSP.setAdapter(corporationAdepter);
                }

                if(id > 0){
                    //corporationOrPoroshoba = corName;
                    Log.e("corporationIDListSaikot", "corporationIDList: "+corporationIDList.indexOf(Integer.parseInt(corporationOrPoroshoba)));
                    if(Integer.parseInt(corporationOrPoroshoba)!=0){
                        Log.e("corporationIDListSaikot", "corporationIDList: "+corporationOrPoroshoba);
                        corporationSP.setSelection(corporationIDList.indexOf(Integer.parseInt(corporationOrPoroshoba)));
                    }

                    //    corporationList.add(corName);
                    // Log.e("corporationListData", "setCityCorporationSpinner: "+corporationList );
                    Log.e("corporationListData", "setCityCorporationSpinner: "+corporationOrPoroshoba );



                }

                corporationSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
                        if(parent.getItemAtPosition(position).equals("--Select--")){

                            return;
                        }else {
                            corporationOrPoroshoba = String.valueOf(corporationIDList.get(position));
                            Log.e("corporationOrPoroshoba", "onItemSelected: "+corporationOrPoroshoba );
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onFailure(Call<CityPouroshovaUnion> call, Throwable t) {
                Log.e("errorProblem", "onFailure: " + t.getLocalizedMessage());
            }
        });*/

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<cityPouroshovaUnionModel> allCityPouroshovaUnion = Database.getInstance(getActivity()).getProviderDao().getAllCityPouroshovaUnion(thanaId,cityDivision);
                    for (int i = 0; i < allCityPouroshovaUnion.size(); i++) {
                        Log.e("getCpuList", "onResponse: " + allCityPouroshovaUnion.get(i).getId());
                        corporationList.add(allCityPouroshovaUnion.get(i).getCityPouroshovaUnionName());
                        corporationIDList.add(Integer.parseInt(allCityPouroshovaUnion.get(i).getCityPouroshovaUnionID()));
                        Log.e("corporationIDList", "onResponse: " +allCityPouroshovaUnion.get(i).getCityPouroshovaUnionName());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);

                    if (getActivity() != null) {
                        ArrayAdapter<String> corporationAdepter = new ArrayAdapter<String>(
                                getContext(), R.layout.custom_layout, corporationList
                        );

                        corporationAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        corporationSP.setAdapter(corporationAdepter);
                    }

                    if(id > 0){
                        //corporationOrPoroshoba = corName;
                      //  Log.e("corporationIDListSaikot", "corporationIDList: "+corporationIDList.indexOf(Integer.parseInt(corporationOrPoroshoba)));

                        try {
                            if(Integer.parseInt(corporationOrPoroshoba)!=0){
                                Log.e("corporationIDListSaikot", "corporationIDList: "+corporationOrPoroshoba);
                                corporationSP.setSelection(corporationIDList.indexOf(Integer.parseInt(corporationOrPoroshoba)));
                            }
                        }catch (Exception e){}


                        //    corporationList.add(corName);
                        // Log.e("corporationListData", "setCityCorporationSpinner: "+corporationList );
                        Log.e("corporationListData", "setCityCorporationSpinner: "+corporationOrPoroshoba );



                    }

                    corporationSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
                            if(parent.getItemAtPosition(position).equals("--Select--")){

                                return;
                            }else {
                                corporationOrPoroshoba = String.valueOf(corporationIDList.get(position));
                                corporationOrPoroshobaName = String.valueOf(corporationList.get(position));

                                Log.e("corporationOrPoroshoba", "onItemSelected: "+corporationOrPoroshobaName );
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }.execute();
        }catch (Exception e){}


    }

    private void setDivisionSpinner() {
       // divisionNamelist.clear();
        //divisionIDlist.clear();
        //districtIDList.clear();
        //districtNameList.clear();
        divisionNamelist.add("--- Select ---");
        divisionIDlist.add(0);
        districtNameList.add("--Select--");
        districtIDList.add(0);
        thanaNameList.add("--Select--");
        thanaIDList.add(0);
        ServiceApi divisionApi = RetrofitInstance.retrofit.create(ServiceApi.class);
      /*  divisionApi.getAllDivisionApi().enqueue(new Callback<PwRegistrationModel>() {
            @Override
            public void onResponse(Call<PwRegistrationModel> call, Response<PwRegistrationModel> response) {
                try {
                    if(response.isSuccessful()){
                        Log.e("ppp",response.body().toString());
                        for(int division=0; division<=response.body().getPwAddEdit().get(0).getDivisions().size(); division++){
                            divisionNamelist.add(response.body().getPwAddEdit().get(0).getDivisions().get(division).getName());
                            divisionIDlist.add(response.body().getPwAddEdit().get(0).getDivisions().get(division).getId());

                            Log.e("division", "onResponse: "+response.body().getPwAddEdit().get(0).getDivisions().get(division).getName());
                            Log.e("division", "onResponse: "+response.body().getPwAddEdit().get(0).getDivisions().get(division).getId());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (getActivity()!=null) {
                    ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(getActivity(),
                            R.layout.custom_layout, divisionNamelist);
                    divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    patientDivisionSP.setAdapter(divisionAdapter);
                }
               *//* ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(getContext(),
                        R.layout.custom_layout, divisionNamelist);
                divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                patientDivisionSP.setAdapter(divisionAdapter);*//*

                if(id > 0) {
                    patientDivisionSP.setSelection(divisionIDlist.indexOf(Integer.parseInt(divisionId)));
                    Log.e("patientDivisionSP", "onResponse: "+divisionIDlist.indexOf(divisionId)+"division"+divisionId );
                }

                patientDivisionSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        //   Log.e("div", "onResponse: "+divisionIDlist.get(position));

                        divisionId =  String.valueOf(divisionIDlist.get(position));
                        districtJsonObject = new JsonObject();
                        districtJsonObject.addProperty("division_id",divisionId);

                        ServiceApi districtApi = RetrofitInstance.retrofit.create(ServiceApi.class);
                        districtNameList.clear();
                        districtIDList.clear();
                        districtNameList.add("--Select--");
                        districtIDList.add(0);

                        districtApi.setAllDistrictApi(districtJsonObject).enqueue(new Callback<DistrictNameModel>() {
                            @Override
                            public void onResponse(Call<DistrictNameModel> call, Response<DistrictNameModel> response) {
                                try {
                                    if(response.isSuccessful()){
                                        for(int dis = 0;dis<response.body().getDistrict().size();dis++){

                                            districtNameList.add(response.body().getDistrict().get(dis).getName());
                                            districtIDList.add(Integer.parseInt(response.body().getDistrict().get(dis).getId()));

                                            // districtId = response.body().getDistrict().get(dis).getId();

                                            Log.e("district", "onResponse: "+response.body().getDistrict().get(dis).getName());
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                setDistrictSpinner();
                            }

                            @Override
                            public void onFailure(Call<DistrictNameModel> call, Throwable t) {

                            }


                        });

                        // Log.e("div", "onResponse: "+divisionIDlist.toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<PwRegistrationModel> call, Throwable t) {

            }
        });*/


        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<DivisionModel> allDivision = Database.getInstance(getActivity()).getProviderDao().getAllDivision();
                    for(int division=0; division<allDivision.size(); division++){
                        divisionNamelist.add(allDivision.get(division).getDivisionName());
                        divisionIDlist.add(allDivision.get(division).getDivisionID());

                        Log.e("division", "onResponse: "+allDivision.get(division).getDivisionName());
                        Log.e("division", "onResponse: "+allDivision.get(division).getDivisionID());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);

                    if (getActivity()!=null) {
                        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(getActivity(),
                                R.layout.custom_layout, divisionNamelist);
                        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        patientDivisionSP.setAdapter(divisionAdapter);
                    }

                    if(id > 0) {
                        try {
                            patientDivisionSP.setSelection(divisionIDlist.indexOf(Integer.parseInt(divisionId)));

                        }catch (Exception e){}
                    }

                    patientDivisionSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            divisionId =  String.valueOf(divisionIDlist.get(position));
                            divisionName =  String.valueOf(divisionNamelist.get(position));
                            districtJsonObject = new JsonObject();
                            districtJsonObject.addProperty("division_id",divisionId);
                            Log.e("divisionName", "onItemSelected: "+divisionName );

                            ServiceApi districtApi = RetrofitInstance.retrofit.create(ServiceApi.class);
                            districtNameList.clear();
                            districtIDList.clear();
                            districtNameList.add("--Select--");
                            districtIDList.add(0);

                           /* districtApi.setAllDistrictApi(districtJsonObject).enqueue(new Callback<DistrictNameModel>() {
                                @Override
                                public void onResponse(Call<DistrictNameModel> call, Response<DistrictNameModel> response) {
                                    try {
                                        if(response.isSuccessful()){
                                            for(int dis = 0;dis<response.body().getDistrict().size();dis++){

                                                districtNameList.add(response.body().getDistrict().get(dis).getName());
                                                districtIDList.add(Integer.parseInt(response.body().getDistrict().get(dis).getId()));

                                                // districtId = response.body().getDistrict().get(dis).getId();

                                                Log.e("district", "onResponse: "+response.body().getDistrict().get(dis).getName());
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }



                                }

                                @Override
                                public void onFailure(Call<DistrictNameModel> call, Throwable t) {

                                }


                            });*/

                            try {
                                new AsyncTask<Void, Void, Void>() {
                                    @Override
                                    protected Void doInBackground(Void... voids) {
                                        List<DistrictModel> allDistrict = Database.getInstance(getActivity()).getProviderDao().getAllDistrict(divisionId);
                                        for(int dis = 0;dis<allDistrict.size();dis++){

                                            districtNameList.add(allDistrict.get(dis).getDistrictName());
                                            districtIDList.add(Integer.parseInt(allDistrict.get(dis).getDistrictID()));

                                            // districtId = response.body().getDistrict().get(dis).getId();

                                            Log.e("district", "onResponse: "+allDistrict.get(dis).getDistrictID());
                                        }
                                        return null;
                                    }

                                    @Override
                                    protected void onPostExecute(Void unused) {
                                        super.onPostExecute(unused);
                                        setDistrictSpinner();
                                    }
                                }.execute();
                            }catch (Exception e){}


                            // Log.e("div", "onResponse: "+divisionIDlist.toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }.execute();
        }catch (Exception e){}






    }

    private void setHealthSpinner() {

        healthConditionIDList.clear();
        healthConditionNameList.clear();
        healthConditionNameList.add("--Select--");
        healthConditionIDList.add(0);

        ServiceApi healthApi = RetrofitInstance.retrofit.create(ServiceApi.class);

      /*  healthApi.getHealthCondition().enqueue(new Callback<PwRegistrationModel>() {
            @Override
            public void onResponse(Call<PwRegistrationModel> call, Response<PwRegistrationModel> response) {
                try {
                    if(response.isSuccessful()){
                        for(int condition= 0;condition<response.body().getPwAddEdit().get(0).getHealthConditions().size();condition++){
                            healthConditionNameList.add(response.body().getPwAddEdit().get(0).getHealthConditions().get(condition).getName());
                            healthConditionIDList.add(response.body().getPwAddEdit().get(0).getHealthConditions().get(condition).getId());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(getActivity()!=null){
                    ArrayAdapter<String>  healthConditionAdepter= new ArrayAdapter<>(
                            getActivity(),R.layout.custom_layout,
                            healthConditionNameList);

                    healthConditionAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                    healthConditionSP.setAdapter(healthConditionAdepter);
                }


                if(id > 0){
                    Log.e("healthCondition", "healthCondition: "+healthConditionIDList.indexOf(Integer.parseInt(healthId)+"healthId"+healthId));
                    healthConditionSP.setSelection(healthConditionIDList.indexOf(Integer.parseInt(healthId)));
                }

                healthConditionSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(getContext(), ""+ healthConditionIDList.get(position).getClass().getI, Toast.LENGTH_SHORT).show();
                        if(parent.getItemAtPosition(position).equals("--Select--")){
                            //   saveBtn.setClickable(false);
                            //  Toast.makeText(getActivity(), "aa", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            healthId = healthConditionIDList.get(position).toString();
                        }

                        Log.e("f", "onItemSelected: "+healthId );
                        // Log.e("f", "onItemSelected: "+Integer.parseInt(healthConditionNameList.get(position)) );
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<PwRegistrationModel> call, Throwable t) {

            }
        });*/

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<HealthConditionModel> allHealthCondition = Database.getInstance(getActivity()).getProviderDao().getAllHealthCondition();
                    for(int condition= 0;condition<allHealthCondition.size();condition++){
                        healthConditionNameList.add(allHealthCondition.get(condition).getHealthConditionName());
                        healthConditionIDList.add(allHealthCondition.get(condition).getHealthConditionID());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);

                    if(getActivity()!=null){
                        ArrayAdapter<String>  healthConditionAdepter= new ArrayAdapter<>(
                                getActivity(),R.layout.custom_layout,
                                healthConditionNameList);

                        healthConditionAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        healthConditionSP.setAdapter(healthConditionAdepter);
                    }


                    if(id > 0){
                        try {
                            Log.e("healthCondition", "healthCondition: "+healthConditionIDList.indexOf(Integer.parseInt(healthId)+"healthId"+healthId));
                            healthConditionSP.setSelection(healthConditionIDList.indexOf(Integer.parseInt(healthId)));
                        }catch (Exception e){}

                    }

                    healthConditionSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // Toast.makeText(getContext(), ""+ healthConditionIDList.get(position).getClass().getI, Toast.LENGTH_SHORT).show();
                            if(parent.getItemAtPosition(position).equals("--Select--")){
                                //   saveBtn.setClickable(false);
                                //  Toast.makeText(getActivity(), "aa", Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                healthId = healthConditionIDList.get(position).toString();
                                healthName = healthConditionNameList.get(position).toString();
                            }

                            Log.e("f", "onItemSelected: "+healthId );
                            // Log.e("f", "onItemSelected: "+Integer.parseInt(healthConditionNameList.get(position)) );
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }.execute();
        }catch (Exception e){}



    }

    private void setBuyerSpinner() {
        buyerNameList.clear();
        buyerIDList.clear();
        buyerNameList.add("--Select--");
        buyerIDList.add(0);

        ServiceApi buyerApi = RetrofitInstance.retrofit.create(ServiceApi.class);

       /* buyerApi.getAllBuyer().enqueue(new Callback<PwRegistrationModel>() {
            @Override
            public void onResponse(Call<PwRegistrationModel> call, Response<PwRegistrationModel> response) {
                try {
                    if(response.isSuccessful()){
                        for(int purcher = 0;purcher<response.body().getPwAddEdit().get(0).getBuyers().size();purcher++){
                            buyerNameList.add(response.body().getPwAddEdit().get(0).getBuyers().get(purcher).getName());
                            buyerIDList.add(response.body().getPwAddEdit().get(0).getBuyers().get(purcher).getId());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(getActivity()!=null){
                    ArrayAdapter<String>  purchasingAdepter= new ArrayAdapter<>(
                            getActivity(),R.layout.custom_layout,
                            buyerNameList);

                    purchasingAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                    purchasingSP.setAdapter(purchasingAdepter);
                }



                if(id > 0){
                    Log.e("index_buyer", "setBuyerSpinner: "+buyerIDList.indexOf(Integer.parseInt(buyerID)+"buyerID"+buyerID));
                    purchasingSP.setSelection(buyerIDList.indexOf(Integer.parseInt(buyerID)));
                }

                purchasingSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //whoPurchasing = purchasing[position];
                        //Toast.makeText(getContext(), selectedType, Toast.LENGTH_SHORT).show();
                        if(parent.getItemAtPosition(position).equals("--Select--")){
                            // saveBtn.setClickable(false);
                            return;
                        }else {
                            buyerID = buyerIDList.get(position).toString();
                        }

                        //   buyerNameID = buyerNameList.get(position);
                        //  Log.e("ff", "onItemSelected: "+buyerID );
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



            }

            @Override
            public void onFailure(Call<PwRegistrationModel> call, Throwable t) {

            }
        });*/

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<BuyerModel> allBuyer = Database.getInstance(getActivity()).getProviderDao().getAllBuyer();
                    for(int purcher = 0;purcher<allBuyer.size();purcher++){
                        buyerNameList.add(allBuyer.get(purcher).getBuyerName());
                        buyerIDList.add(allBuyer.get(purcher).getBuyerId());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);

                    if(getActivity()!=null){
                        ArrayAdapter<String>  purchasingAdepter= new ArrayAdapter<>(
                                getActivity(),R.layout.custom_layout,
                                buyerNameList);

                        purchasingAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        purchasingSP.setAdapter(purchasingAdepter);
                    }



                    if(id > 0){
                        try {
                            purchasingSP.setSelection(buyerIDList.indexOf(Integer.parseInt(buyerID)));

                        }catch (Exception e){}
                    }

                    purchasingSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //whoPurchasing = purchasing[position];
                            //Toast.makeText(getContext(), selectedType, Toast.LENGTH_SHORT).show();
                            if(parent.getItemAtPosition(position).equals("--Select--")){
                                // saveBtn.setClickable(false);
                                return;
                            }else {
                                buyerID = buyerIDList.get(position).toString();
                                buyerName = buyerNameList.get(position).toString();

                                Log.e("buyerName", "onItemSelected: "+buyerName );
                            }

                            //   buyerNameID = buyerNameList.get(position);
                            //  Log.e("ff", "onItemSelected: "+buyerID );
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }.execute();
        }catch (Exception e){}









    }

    private void setProviderList() {

        providerNameList.clear();
        providerIDList.clear();
        providerNameList.add("--Select--");
        providerIDList.add(0);

        final JsonObject providerObj = new JsonObject();
        Log.e("providerObj", "setProviderList: "+providerID );
        providerObj.addProperty("thana_id",providerThanaAreaId);
        providerObj.addProperty("user_id",userId);
        Log.e("providerObj", "setProviderList: "+providerObj.toString() );
        Log.e("providerObjID", "setProviderList: "+providerID );
        ServiceApi providerTypeApi = RetrofitInstance.retrofit.create(ServiceApi.class);

       /* providerTypeApi.getAllPo_ProviderApi(providerObj).enqueue(new Callback<PoProviderModel>() {
            @Override
            public void onResponse(Call<PoProviderModel> call, Response<PoProviderModel> response) {
                Log.e("response_bodysize", "onResponse: "+response.body().getProvider().getProviderList().size());
                try {
                    if(response.isSuccessful()){

                        for(int providerApi=0;providerApi< response.body().getProvider().getProviderList().size();providerApi++){
                           providerNameList.add(response.body().getProvider().getProviderList().get(providerApi).getName());

                            providerIDList.add(Integer.parseInt(response.body().getProvider().getProviderList().get(providerApi).getId()));
                            Log.e("proResponseID", "onResponse: "+response.body().getProvider().getProviderList().get(providerApi).getId() );
                        }
                    }
                }catch (Exception e){
                    Log.e("providerEx", "onResponse: "+e.getLocalizedMessage() );
                }

                if(getActivity()!=null){
                    ArrayAdapter<String> providerAdapter = new ArrayAdapter<String>(getContext(),
                            R.layout.custom_layout, providerNameList);


                    actvProviderTypePWFragment.setAdapter(providerAdapter);
                }

                Log.e("providerObjID", "setProviderList: "+providerID );
                if(id > 0){
                    Log.e("providerIdSID", "onResponse: "+providerIDList.indexOf(Integer.parseInt(providerID)));
                    Log.e("providerIdSID", "onResponse: "+providerIDList.size());
                    Log.e("providerIdSID", "onResponse: "+providerIDList.size());
                    Log.e("providerNameList", "onResponse: "+providerNameList.size());

                    int index = providerIDList.indexOf(Integer.parseInt(providerID));
                    Log.e("providerIdSID", "onResponse: "+index);
                    if(index>=0){
                        actvProviderTypePWFragment.setText(providerNameList.get(index));
                    }

                }

                actvProviderTypePWFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        providerID = providerIDList.get(position).toString();
                       //  Toast.makeText(getContext(), ""+providerID, Toast.LENGTH_SHORT).show();
                    }
                });

              *//*  providerTypenameSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            if(parent.getItemAtPosition(position).equals("--Select--")){
                                // saveBtn.setClickable(false);
                                return;
                            }else {
                                ///saveBtn.setClickable(true);
                                providerID = String.valueOf(providerIDList.get(position));
                                // providerTypenameSP.setSelection(Integer.parseInt(providerID));
                                Log.e("pp", "onItemSelected: "+providerID );
                                // usernameTV.setText(""+providerID);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*//*



            }

            @Override
            public void onFailure(Call<PoProviderModel> call, Throwable t) {

            }
        });*/

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    List<PoMappingProviderModel> allPoMappingProvider = Database.getInstance(getActivity()).getProviderDao().getAllPoMappingProvider(providerThanaAreaId);
                    for(int providerApi=0;providerApi< allPoMappingProvider.size();providerApi++){
                        providerNameList.add(allPoMappingProvider.get(providerApi).getPoMappingProviderName());
                        providerIDList.add(Integer.parseInt(allPoMappingProvider.get(providerApi).getPoMappingProviderID()));
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);
                    if(getActivity()!=null){
                        ArrayAdapter<String> providerAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.custom_layout, providerNameList);


                        actvProviderTypePWFragment.setAdapter(providerAdapter);
                    }

                    Log.e("providerObjID", "setProviderList: "+providerID );
                    if(id > 0){
                        Log.e("providerIdSID", "onResponse: "+providerIDList.indexOf(Integer.parseInt(providerID)));
                        Log.e("providerIdSID", "onResponse: "+providerIDList.size());
                        Log.e("providerIdSID", "onResponse: "+providerIDList.size());
                        Log.e("providerNameList", "onResponse: "+providerNameList.size());

                        int index = providerIDList.indexOf(Integer.parseInt(providerID));
                        Log.e("providerIdSID", "onResponse: "+index);
                        if(index>=0){
                            actvProviderTypePWFragment.setText(providerNameList.get(index));
                        }

                    }

                    actvProviderTypePWFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            providerID = providerIDList.get(position).toString();
                            providerName = providerNameList.get(position);
                             // Toast.makeText(getContext(), ""+providerID, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }.execute();
        }catch (Exception e){}
    }

    private void setproviderSpinner() {

        providerThanaAreaNameList.clear();
        providerThanaAreaIdList.clear();
        providerThanaAreaNameList.add("--Select--");
        providerThanaAreaIdList.add(0);
        final JsonObject providerObj = new JsonObject();
        providerObj.addProperty("user_id",userId);
        Log.e("providerObj", "setproviderSpinner: "+providerObj.toString() );
        ServiceApi providerTypeApi = RetrofitInstance.retrofit.create(ServiceApi.class);
      /*  providerTypeApi.getAllProviderApi(providerObj).enqueue(new Callback<PwRegistrationModel>() {
            @Override
            public void onResponse(Call<PwRegistrationModel> call, Response<PwRegistrationModel> response) {
                try {
                    if(response.isSuccessful()){
                        for(int providerApi=0;providerApi< response.body().getPwAddEdit().get(0).getPoMapingThana().size();providerApi++){
                            providerThanaAreaNameList.add(response.body().getPwAddEdit().get(0).getPoMapingThana().get(providerApi).getName());
                            providerThanaAreaIdList.add(response.body().getPwAddEdit().get(0).getPoMapingThana().get(providerApi).getId());
                             Log.e("pro", "onResponse: "+response.body().getPwAddEdit().get(0).getPoMapingThana().get(providerApi).getId() );
                        }
                    }
                }catch (Exception e){}

                if(getActivity()!=null){
                    ArrayAdapter<String> providerAdapter = new ArrayAdapter<String>(getContext(),
                            R.layout.custom_layout, providerThanaAreaNameList);
                    providerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                    providerTypenameSP.setAdapter(providerAdapter);
                }



                if(id > 0){
                    Log.e("Index",providerThanaAreaIdList.indexOf(Integer.parseInt(providerThanaAreaId))+"providerID"+providerThanaAreaId);
                    providerTypenameSP.setSelection(providerThanaAreaIdList.indexOf(Integer.parseInt(providerThanaAreaId)));
                }




                providerTypenameSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            if(parent.getItemAtPosition(position).equals("--Select--")){
                               // saveBtn.setClickable(false);
                                actvProviderTypePWFragment.setText("");
                                return;
                            }else {
                                ///saveBtn.setClickable(true);
                                providerThanaAreaId = String.valueOf(providerThanaAreaIdList.get(position));
                                // providerTypenameSP.setSelection(Integer.parseInt(providerID));
                                Log.e("pp", "onItemSelected: "+providerThanaAreaId );
                                // usernameTV.setText(""+providerID);
                                providerIDList.clear();
                                providerNameList.clear();
                                actvProviderTypePWFragment.setText("");
                                setProviderList();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



            }

            @Override
            public void onFailure(Call<PwRegistrationModel> call, Throwable t) {

            }
        });*/

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
                    if(getActivity()!=null){
                        ArrayAdapter<String> providerAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.custom_layout, providerThanaAreaNameList);
                        providerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        providerTypenameSP.setAdapter(providerAdapter);
                    }
                    if(id > 0){
                        try {
                            Log.e("Index",providerThanaAreaIdList.indexOf(Integer.parseInt(providerThanaAreaId))+"providerID"+providerThanaAreaId);
                            providerTypenameSP.setSelection(providerThanaAreaIdList.indexOf(Integer.parseInt(providerThanaAreaId)));
                        }catch (Exception e){}
                    }

                    providerTypenameSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                /*if(parent.getItemAtPosition(position).equals("--Select--")){
                                    // saveBtn.setClickable(false);
                                    providerIDList.clear();
                                    providerNameList.clear();
                                    actvProviderTypePWFragment.setText("");
                                    return;
                                }else {
                                    ///saveBtn.setClickable(true);
                                    providerThanaAreaId = String.valueOf(providerThanaAreaIdList.get(position));
                                    providerThanaAreaName = String.valueOf(providerThanaAreaNameList.get(position));
                                    // providerTypenameSP.setSelection(Integer.parseInt(providerID));
                                    Log.e("pp", "onItemSelected: "+providerThanaAreaName );
                                    // usernameTV.setText(""+providerID);
                                    providerIDList.clear();
                                    providerNameList.clear();
                                    actvProviderTypePWFragment.setText("");
                                    setProviderList();
                                }*/
                                providerThanaAreaId = String.valueOf(providerThanaAreaIdList.get(position));
                                providerThanaAreaName = String.valueOf(providerThanaAreaNameList.get(position));
                                // providerTypenameSP.setSelection(Integer.parseInt(providerID));
                                Log.e("pp", "onItemSelected: "+providerThanaAreaName );
                                // usernameTV.setText(""+providerID);
                                providerIDList.clear();
                                providerNameList.clear();
                                actvProviderTypePWFragment.setText("");
                                setProviderList();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }.execute();


        }catch (Exception e){}
    }

    private void saveDataLocalDatabase(String fullName,String age,String mobile,String noOfPregnancy,String pwlmp,String pwedd,String regDate,String purchase_quantity) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                String updateID = "0";
                PWInfoModel pwInfoModel = new PWInfoModel(ConstantUtils.Temp.id,providerThanaAreaId,providerThanaAreaName,providerID,providerName,fullName,
                        age,mobile,divisionId,divisionName,districtId,districtName,thanaId,thanaName,cityDivision,corporationOrPoroshobaName,
                        corporationOrPoroshoba,lat,lng,noOfPregnancy,buyerID,buyerName,pwlmp,pwedd,healthId,healthName,regDate,purchase_quantity,flag,updateID);
                return  Database.getInstance(getContext()).getProviderDao().savePWInfoData(pwInfoModel);

            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                //Toast.makeText(getContext(), "StockInfoModel", Toast.LENGTH_SHORT).show();

            }
        }.execute();
    }


    private void updatePwInfoDatabase(String fullName, String age, String mobile, String noOfPregnancy, String pwlmp, String pwedd, String regDate, String purchase_quantity,String id) {

        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {

                return  Database.getInstance(getContext()).getProviderDao().updatePwInfoDatabase(providerThanaAreaId,userId,providerThanaAreaName,providerID,providerName,
                        fullName,age,mobile,divisionId,divisionName,districtId,districtName,thanaId,thanaName,cityDivision,corporationOrPoroshobaName,corporationOrPoroshoba,
                        lat,lng,noOfPregnancy,buyerID,buyerName,pwlmp,pwedd,healthId,healthName,regDate,purchase_quantity,flag,id);

            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
            }
        }.execute();
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }



}