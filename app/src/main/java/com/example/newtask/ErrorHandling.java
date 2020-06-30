package com.example.newtask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import io.reactivex.functions.BiConsumer;
import retrofit2.HttpException;

public class ErrorHandling implements BiConsumer<Throwable, String> {
    private Context context;

    public ErrorHandling(Context context) {
        this.context = context;
    }

    @Override
    public void accept(Throwable throwable, String msg) throws IOException {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            int code = httpException.code();
            if (code == 400) {
                Log.e("error400 user error", httpException.getMessage());
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            } else if (code == 401) {
                Log.e("error401 unauthorized", httpException.getMessage());
            } else if (code == 500) {
                Log.e(" 500 server error", httpException.getMessage());
            }
        } else
            Toast.makeText(context, "Connection time out", Toast.LENGTH_LONG).show();
    }


}
