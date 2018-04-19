package com.stdesign.bitacorasutd.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hurón Padilla on 4/18/2018.
 */

public class ResultBitacora {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    public ResultBitacora(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
