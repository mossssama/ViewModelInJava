package com.example.viewmodelexample.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.viewmodelexample.Stop.Stop;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insertStop(Stop stop);

    @Query("DELETE FROM stops")
    void deleteStops();

    @Query("SELECT * FROM stops WHERE stopType=:stopType")
    LiveData<Stop> getStop(String stopType);

    @Query("SELECT * FROM stops")
    LiveData<List<Stop>> getStops();

    @Query("UPDATE stops SET lat =:mlat, lng =:mlng WHERE stoptype=:stopType")
    void updateStop(String stopType,double mlat,double mlng);
}
