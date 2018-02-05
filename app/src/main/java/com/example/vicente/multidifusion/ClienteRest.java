package com.example.vicente.multidifusion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ClienteRest {

    @GET("persona")
    Call<ArrayList<Persona>> getPersonas();

    @POST("persona")
    Call<Persona> postPersona(@Body Persona persona);

}
