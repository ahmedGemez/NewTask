package com.example.newtask;

import com.example.newtask.entities.CarsResponse;

import io.reactivex.Single;

public class HomeUseCase {


    private NewTaskRepository newTaskRepository;

    public HomeUseCase() {
        newTaskRepository = new NewTaskRepository();
    }


    public Single<CarsResponse> getCars(int page) {
        return newTaskRepository.getCars(page);
    }
}


