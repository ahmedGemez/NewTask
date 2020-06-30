package com.example.newtask;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rcy_cars)
    RecyclerView rcyCars;
    private HomeViewModel viewModel;

    private CarAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        rcyCars.setLayoutManager(new LinearLayoutManager(this));
        rcyCars.setHasFixedSize(true);

        viewModel = new HomeViewModel(this);
        viewModel.getCars(1);
        observeCars();



    }

    private void observeCars(){
        viewModel.getCarsLiveData.observe(this, carsResponse -> {
            if (carsResponse.getData() != null)
            {
                adapter=new CarAdapter(carsResponse.getData());
                rcyCars.setAdapter(adapter);
            }

        });
    }

}
