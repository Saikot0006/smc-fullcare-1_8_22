package com.gs.smc_mmc.adepter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.PW_RecordModel;
import com.gs.smc_mmc.model.PatientModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PWInfoModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class PW_Record_Adepter extends RecyclerView.Adapter<PW_Record_Adepter.PWViewHolder>{

    private Context context;
    private List<PWInfoModel> pwList;
    String Id,name,age,mobile,noPregnancy;

    public PW_Record_Adepter(Context context, List<PWInfoModel> pwList) {
        this.context = context;
        this.pwList = pwList;
    }

    @NonNull
    @NotNull
    @Override
    public PWViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pw_record_layout,parent,false);

        return new PWViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PW_Record_Adepter.PWViewHolder holder, int position) {
        holder.pwDateTV.setText(pwList.get(position).getRegistrationDate());
        holder.pwAgeTV.setText(pwList.get(position).getAge());
        holder.pwNameTV.setText(pwList.get(position).getName());
        holder.pwMenuTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Id = String.valueOf(pwList.get(position).getId());
                 name = pwList.get(position).getName();
                 age = pwList.get(position).getAge();
                 mobile = pwList.get(position).getMobileNo();

                PopupMenu popupMenu = new PopupMenu(context,view);
                popupMenu.getMenuInflater().inflate(R.menu.pw_record_menu,popupMenu.getMenu());

                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.pw_viewID){
                            try {
                                //  String pwId = pwList.get(position).getId();

                                Bundle viewBundle = new Bundle();
                                viewBundle.putString("ID",Id);
                                Log.e("ff", "onMenuItemClick: "+Id);
                                //Toast.makeText(context, ""+Id, Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.patientInfoFragment,viewBundle);
                                return false;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }else if(menuItem.getItemId() == R.id.pw_editID){
                            try {
                                Bundle viewBundle = new Bundle();
                                viewBundle.putInt("ID",Integer.parseInt(Id));
                                Navigation.findNavController(view).navigate(R.id.PW_RegistrationFragment,viewBundle);
                                Log.e("ee", "onMenuItemClick: edit" );
                                return false;
                                //Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }

                        }
                        return false;

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return pwList.size();
    }

    class PWViewHolder extends RecyclerView.ViewHolder {

        TextView pwDateTV,pwAgeTV,pwNameTV,pwMenuTV;
        public PWViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            pwDateTV = itemView.findViewById(R.id.pwDateId);
            pwAgeTV = itemView.findViewById(R.id.pwAgeId);
            pwNameTV = itemView.findViewById(R.id.pwNameId);
            pwMenuTV = itemView.findViewById(R.id.pwMenuId);


        }

    }

}

