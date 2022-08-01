package com.gs.smc_mmc.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.gs.smc_mmc.model.CityPouroshovaUnion;
import com.gs.smc_mmc.model.LocationModel;
import com.gs.smc_mmc.model.PwRegistrationModel;
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

import java.util.List;
import java.util.TreeMap;

@Dao
public interface ProviderAndPoMappingThanaDao {

    @Insert
    long insertPoMappingThana(PoMappingThanaModel poMappingThanaModel);

    @Insert
    long insertProvider(PoMappingProviderModel poMappingProviderModel);

    @Insert
    long insertDivision(DivisionModel divisionModel);

    @Insert
    long insertDistrict(DistrictModel districtModel);

    @Insert
    long insertThana(ThanaModel thanaModel);

    @Insert
    long insertCityPouroshovaUnion(cityPouroshovaUnionModel cityPouroshovaUnionModel);

    @Insert
    long insertBuyer(BuyerModel buyerModel);

    @Insert
    long insertHealthCondition(HealthConditionModel healthConditionModel);

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Insert
    long savePWInfoData(PWInfoModel pwInfoModel);

    @Query("select * from pwinfo")
    List<PWInfoModel> getAllPWInfo();

    @Query("SELECT * FROM pwinfo WHERE registrationDate BETWEEN :sDate AND :eDate")
    List<PWInfoModel> getAllPWInfoByDate(String sDate,String eDate);

    @Query("SELECT * FROM pwinfo WHERE age BETWEEN :sAge AND :eAge")
    List<PWInfoModel> getAllPWInfoByAge(int sAge,int eAge);

    @Query("SELECT * FROM pwinfo WHERE registrationDate between :sDate AND :eDate And age between :sAge AND :eAge")
    List<PWInfoModel> getAllPWInfoByAgeAndDate(String sDate,String eDate,int sAge,int eAge);

    @Query("select * from pwinfo where id = :sId")
    List<PWInfoModel> getSinglePWInfo(String sId);

    @Query("select * from pwinfo where flag = :flag")
    List<PWInfoModel> getAllPwInfoByFlag(String flag);

    @Query("update pwinfo set providerMappingThanaId = :providerThanaAreaID, userID =:userId ,providerMappingThanaName = :providerThanaAreaName, providerId = :pID, providerName = :pName,name = :fullName,age = :age,mobileNo = :mobile, divisionId = :divisionId, divisionName =:divisionName, districtId = :districtId, districtName = :districtName, thanaId =:thanaId,thanaName =:thanaName,cityPouroshobha =:cityDivision,cityPouroshobhaName =:corporationOrPoroshobaName, cityPouroshobhaId=:corporationOrPoroshoba, latitude=:lat, longitude=:lng, numberOfPregnancy=:noOfPregnancy, buyerId =:buyerID, buyerName =:buyerName,lmp=:pwlmp,edd =:pwedd,healthConditionId=:healthId,healthConditionName=:healthName,registrationDate =:regDate,purchaseQuantity=:purchase_quantity,flag= :flag where id = :sid")
    int updatePwInfoDatabase(String providerThanaAreaID, int userId,String providerThanaAreaName, String pID, String pName, String fullName, String age, String mobile, String divisionId, String divisionName, String districtId, String districtName, String thanaId, String thanaName, String cityDivision, String corporationOrPoroshobaName, String corporationOrPoroshoba, String lat, String lng, String noOfPregnancy, String buyerID, String buyerName, String pwlmp, String pwedd, String healthId, String healthName, String regDate, String purchase_quantity,String flag,String sid);

    @Query("UPDATE pwinfo SET flag = :flag where id = :id")
    int updateAllPushByFlag(String flag,long id);

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////Delete////////////////////////////////////////////////////////////////////////////////////////
    @Query("Delete from poMappingThana")
    void deletePoMappingThana();
    @Query("Delete from pomappingprovider")
    void deleteProvider();
    @Query("Delete from division")
    void deleteDivision();
    @Query("Delete from district")
    void deleteDistrict();
    @Query("Delete from thana")
    void deleteThana();
    @Query("Delete from Buyer")
    void deleteBuyer();
    @Query("Delete from HealthCondition")
    void deleteHealthCondition();
    @Query("Delete from PwInfo where flag = :flag")
    void deletePwInfo(String flag);
    @Query("Delete from StockInfo where flag = :flag")
    void deleteStockInfo(String flag);
    @Query("Delete from cityPouroshovaUnion")
    void deleteCityPouroshovaUnion();
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*@Query("select id from StockInfo where id = :poMappingProviderThanaID")
    int getIndexData(String poMappingProviderThanaID);*/

    @Query("select * from poMappingThana")
    List<PoMappingThanaModel> getAllPoMappingThana();

    @Query("select * from poMappingProvider where thanaID = :providerThanaAreaId")
    List<PoMappingProviderModel> getAllPoMappingProvider(String providerThanaAreaId);

    @Query("select * from healthcondition")
    List<HealthConditionModel> getAllHealthCondition();

    @Query("select * from buyer")
    List<BuyerModel> getAllBuyer();

    @Query("select * from division")
    List<DivisionModel> getAllDivision();

    @Query("select * from district where divisionID = :divisionID")
    List<DistrictModel> getAllDistrict(String divisionID);

    @Query("select * from thana where divisionID = :divisionID and districtID = :districtID")
    List<ThanaModel> getAllThana(String divisionID,String districtID);

    @Query("select * from cityPouroshovaUnion where thanaID = :thanaID and type = :type")
    List<cityPouroshovaUnionModel> getAllCityPouroshovaUnion(String thanaID, String type);

    @Insert
    long saveStockData(StockInfoModel stockInfoModel);


    @Query("select * from stockinfo")
    List<StockInfoModel> getAllStockInfo();

    @Query("select * from stockinfo where flag = :flag")
    List<StockInfoModel> getAllStockInfoByFlag(String flag);

    @Query("UPDATE stockinfo SET flag = :flag where id = :id")
    int updateAllStockInfoByFlag(String flag,long id);


    //@Query("select * from stockinfo where date >= :sDate and date <= :eDate")
    @Query("SELECT * FROM stockinfo WHERE date BETWEEN :sDate AND :eDate")
    List<StockInfoModel> getAllStockInfobyDate(String sDate,String eDate);


    @Query("select * from stockinfo where id = :sId")
    List<StockInfoModel> getSingleStockInfo(String sId);



    @Query("update stockinfo set poMappingProviderThanaID = :providerThanaAreaID,poMappingProviderThanaName = :providerThanaAreaName, poMappingProviderID = :pID, poMappingProviderName = :pName,stockQuantity = :qty,lastPurchasedDay = :days,date = :date,flag = :flag where id = :sid")
    int stockValueUpdate(String providerThanaAreaID,String providerThanaAreaName,String pID,String pName,String qty,String days,String date,String flag,String sid);

    @Query("select count(*) from poMappingProvider where name = :providerName")
    int searchProvider(String providerName);

    @Query("select poMappingProviderID from poMappingProvider where name = :providerName")
    String getProviderID(String providerName);

    @Insert
    long locationData(LocationModel locationModel);

    @Query("Delete from location")
    void deleteLocationData();

    @Query("Select * from location")
    List<LocationModel> getLocationData();


}
