package com.example.viewmodelexample.Stop;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.viewmodelexample.Room.Dao;
import com.example.viewmodelexample.Room.RoomDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StopViewModel extends AndroidViewModel {

    /* M Osama: Stops AS whole & everySingleStop data need to be tracked allover the project */
    private LiveData<List<Stop>> stopsLiveData;
    private Map<String, LiveData<Stop>> stopLiveDataMap = new HashMap<>();

    /* M Osama: deal with Room db */
    private Dao dao;

    public StopViewModel(Application application){
        super(application);
        dao = RoomDB.getInstance(application).dao();
        stopsLiveData=dao.getStops();
    }

    /* M Osama: functions used to deal with Room rather than dao */
    public LiveData<List<Stop>> getStopsLiveData() {    return stopsLiveData;   }
    public LiveData<Stop> getStopLiveData(String stopType) {
        if (!stopLiveDataMap.containsKey(stopType)) { LiveData<Stop> stopLiveData = dao.getStop(stopType); stopLiveDataMap.put(stopType, stopLiveData);  return stopLiveData; }
        else return stopLiveDataMap.get(stopType);
    }
    public void updateStop(Stop stop){
        new UpdateAsyncTask(dao).execute(stop);
    }
    public void insertStop(Stop stop){
        new InsertAsyncTask(dao).execute(stop);
    }
    public void deleteAllStops() {
        new DeleteAllAsyncTask(dao).execute();
    }

    /* M Osama: performs asyncTasks to prevent blocking UI */
    private static class UpdateAsyncTask extends AsyncTask<Stop, Void, Void> {
        private Dao dao;

        private UpdateAsyncTask(Dao dao) {  this.dao = dao; }

        @Override
        protected Void doInBackground(Stop... stop) {
            dao.updateStop(stop[0].getStopType(),stop[0].getLat(),stop[0].getLng());
            return null;
        }
    }
    private static class InsertAsyncTask extends AsyncTask<Stop, Void, Void> {
        private Dao dao;

        private InsertAsyncTask(Dao dao) {  this.dao = dao; }

        @Override
        protected Void doInBackground(Stop... stop) {
            dao.insertStop(stop[0]);
            return null;
        }
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteStops();
            return null;
        }
    }

}
