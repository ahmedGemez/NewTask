package com.example.newtask;

import com.example.newtask.entities.CarsResponse;
import com.example.newtask.network.NewTaskApi;
import com.example.newtask.network.NewTaskClient;

import java.util.HashMap;

import io.reactivex.Single;
import okhttp3.ResponseBody;

public class NewTaskRepository {

    private NewTaskApi api;

    public NewTaskRepository() {
        NewTaskClient newTaskClient = new NewTaskClient();
        api = newTaskClient.providesApiRequest();
    }

    public Single<CarsResponse> getCars(int page) {
        return api.getCars(page);
    }



}
