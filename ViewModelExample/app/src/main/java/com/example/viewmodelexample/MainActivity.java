package com.example.viewmodelexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.viewmodelexample.Stop.Stop;
import com.example.viewmodelexample.Stop.StopViewModel;
import com.example.viewmodelexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    StopViewModel stopViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        stopViewModel= new ViewModelProvider(this).get(StopViewModel.class);

        stopViewModel.deleteAllStops();
        stopViewModel.insertStop(new Stop("LOCATION",30.1234567,39.87654321));
        stopViewModel.insertStop(new Stop("DESTINATION",12.3456789,98.7654321));
        stopViewModel.getStopLiveData("LOCATION").observe(this, stop -> { if(stop!=null) binding.tv.setText(stop.getLat()+","+stop.getLng()); });

        binding.btn1.setOnClickListener(v -> stopViewModel.updateStop(new Stop("LOCATION",1.11,10.10)));
        binding.btn2.setOnClickListener(v -> stopViewModel.updateStop(new Stop("LOCATION",2.22,20.20)));
        binding.btn3.setOnClickListener(v -> stopViewModel.updateStop(new Stop("LOCATION",3.33,30.30)));

    }
}