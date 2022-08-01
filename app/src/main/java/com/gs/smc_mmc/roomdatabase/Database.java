package com.gs.smc_mmc.roomdatabase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gs.smc_mmc.dao.ProviderAndPoMappingThanaDao;
import com.gs.smc_mmc.dao.StockInfoDao;
import com.gs.smc_mmc.model.LocationModel;
import com.gs.smc_mmc.model.roomDatabaseModel.BuyerModel;
import com.gs.smc_mmc.model.roomDatabaseModel.DistrictModel;
import com.gs.smc_mmc.model.roomDatabaseModel.DivisionModel;
import com.gs.smc_mmc.model.roomDatabaseModel.HealthConditionModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PWInfoModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PoMappingProviderModel;
import com.gs.smc_mmc.model.roomDatabaseModel.PoMappingThanaModel;
import com.gs.smc_mmc.model.roomDatabaseModel.StockInfoModel;
import com.gs.smc_mmc.model.roomDatabaseModel.ThanaModel;
import com.gs.smc_mmc.model.roomDatabaseModel.cityPouroshovaUnionModel;

@androidx.room.Database(entities = {
        PoMappingThanaModel.class,PoMappingProviderModel.class, DivisionModel.class, DistrictModel.class,
        ThanaModel.class, cityPouroshovaUnionModel.class, BuyerModel.class, HealthConditionModel.class,
        StockInfoModel.class, PWInfoModel.class, LocationModel.class
},version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public static Database db;
    public abstract ProviderAndPoMappingThanaDao getProviderDao();
    public abstract StockInfoDao getStockInfoDao();

    public static Database getInstance(Context context){

        if(db==null){
            db = Room.databaseBuilder(context,Database.class,"smc_mms")
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }
}
