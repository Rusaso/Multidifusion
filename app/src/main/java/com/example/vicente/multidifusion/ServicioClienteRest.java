package com.example.vicente.multidifusion;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// https://ide.c9.io/izvdamdaw/curso1718 <-- C9 Carmelo

public class ServicioClienteRest extends Service {
    public ServicioClienteRest() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags,
                              int startId) {
        String estado = intent.getStringExtra("estado");
        String numero = intent.getStringExtra("numero");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ios-vicjod.c9users.io/p2/persona/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClienteRest cliente = retrofit.create(ClienteRest.class);

        Call<Persona> call = cliente.postPersona(new Persona(0, "pepe"));
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call,
                                   Response<Persona> response) {
                Persona p = response.body();
                Log.v("XYZYX", "Persona: " + p.toString());
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                Log.v("XYZYX", t.getLocalizedMessage());
            }
        });

        Call<ArrayList<Persona>> call2 = cliente.getPersonas();
        call2.enqueue(new Callback<ArrayList<Persona>>() {
            @Override
            public void onResponse(Call<ArrayList<Persona>> call, Response<ArrayList<Persona>> response) {
                ArrayList<Persona> ap = response.body();
                Log.v("XYZYX", "Personas: " + ap.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Persona>> call, Throwable t) {
                Log.v("XYZYX", t.getLocalizedMessage());
            }
        });

        //START_NOT_STICKY, START_REDELIVER_INTENT, START_STICKY
        return START_REDELIVER_INTENT;
    }
}
