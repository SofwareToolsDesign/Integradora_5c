package com.stdesign.bitacorasutd.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hurón Padilla on 4/18/2018.
 */

public class ResultSchedule {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("resultados")
    private Schedule schedule;

    public ResultSchedule(Boolean error, String message, Schedule schedule) {
        this.error = error;
        this.message = message;
        this.schedule = schedule;
    }

    public Boolean getError() {
            return error;
        }

    public String getMessage() {
            return message;
        }

    public Schedule getSchedule() {
            return schedule;
        }

}
