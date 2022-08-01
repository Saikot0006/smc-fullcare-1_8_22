package com.gs.smc_mmc.adepter;

import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.PopupMenu;
import android.widget.TextView;

import com.gs.smc_mmc.R;
import com.gs.smc_mmc.model.StockListModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;



public class SR_Adepter extends RecyclerView.Adapter<SR_Adepter.SRViewHolder>{
    private Context context;
    private List<StockInfoModel> srList;
    private long Id;
    private int sId;


    public SR_Adepter(Context context, List<StockInfoModel> srList) {
        this.context = context;
        this.srList = srList;
    }

    @NonNull
    @NotNull
    @Override
    public SRViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.stock_record_layout,parent,false);
        return new SRViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SR_Adepter.SRViewHolder holder, int position) {
        holder.providerTV.setText(srList.get(position).getPoMappingProviderName());
      //  holder.typeTV.setText(srList.get(position).get);
        holder.quantityTV.setText(srList.get(position).getStockQuantity());
        holder.menuTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = srList.get(position).getStockUpdateId();
                sId = Integer.parseInt(String.valueOf(Id));

                PopupMenu popupMenu = new PopupMenu(context,view);
                popupMenu.getMenuInflater().inflate(R.menu.sr_menu,popupMenu.getMenu());

                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.sr_viewID){
                            try {
                                Bundle bundle = new Bundle();
                                bundle.putString("sId",String.valueOf(Id));

                                Navigation.findNavController(view).navigate(R.id.action_stockRecordsFragment_to_providerInfoFragment,bundle);
                                //return false;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }if(menuItem.getItemId() == R.id.sr_editID){
                            try {
                                Bundle bundle = new Bundle();
                                bundle.putInt("sId",sId);

                                Navigation.findNavController(view).navigate(R.id.action_stockRecordsFragment_to_stockUpdateFragment,bundle);
                               // return false;
                            } catch (Exception e) {
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
        return srList.size();
    }

    class SRViewHolder extends RecyclerView.ViewHolder {

        TextView providerTV,typeTV,quantityTV,menuTV;
        public SRViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            providerTV = itemView.findViewById(R.id.srNameId);
            typeTV = itemView.findViewById(R.id.srTypeId);
            quantityTV = itemView.findViewById(R.id.srQuantityId);
            menuTV = itemView.findViewById(R.id.srMenuId);
        }
    }
}
