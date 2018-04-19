package com.stdesign.bitacorasutd.api;

import com.stdesign.bitacorasutd.model.ResultBitacora;
import com.stdesign.bitacorasutd.model.ResultSchedule;
import com.stdesign.bitacorasutd.model.ResultUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Hurón Padilla on 4/15/2018.
 */

public interface APIService {

    @FormUrlEncoded
    @POST("register")
    Call<ResultBitacora> registerBitacora(
            @Field("date") String date,
            @Field("hour") String hour,
            @Field("lab") int lab,
            @Field("subject") String subject,
            @Field("group") String group,
            @Field("practice") String practice,
            @Field("teacher") int teacher,
            @Field("equipment") String equipment,
            @Field("observations") String observations,
            @Field("email") String email);


    @FormUrlEncoded
    @POST("login")
    Call<ResultUser> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    //getting schedule
    @POST("schedule")
    Call<ResultSchedule> getScheduleByQr(
            @Field("laboratory") String laboratory,
            @Field("dia") String dia,
            @Field("hora") String hora);
}