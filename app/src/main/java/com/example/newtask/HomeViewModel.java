package com.example.newtask;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newtask.entities.CarsResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private HomeUseCase useCase;
    public MutableLiveData<CarsResponse> getCarsLiveData;
    public MutableLiveData<Boolean> isLoading;
    private ErrorHandling errorHandling;
    public CompositeDisposable compositeDisposable;



    public HomeViewModel(Context context) {
        useCase = new HomeUseCase();
        isLoading = new MutableLiveData<>();
        getCarsLiveData = new MutableLiveData<>();
        errorHandling = new ErrorHandling(context);
        compositeDisposable = new CompositeDisposable();
    }


    public void getCars(int page) {
        Disposable disposable = useCase.getCars(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                        doOnSubscribe(ignored -> isLoading.setValue(true))
                .doFinally(() -> isLoading.setValue(false)).
                        subscribe(response -> {
                            getCarsLiveData.setValue(response);
                                }, error -> {
                                    Log.d("errorrrr", error + "");
                                    errorHandling.accept(error, "error");
                                }
                        );
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!compositeDisposable.isDisposed() && compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
