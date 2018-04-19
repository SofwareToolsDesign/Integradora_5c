package com.stdesign.bitacorasutd.model;

/**
 * Created by Hurón Padilla on 4/11/2018.
 */

public class User {

    private String us;
    private String name;
    private String lastname;
    private int id_user;

    public User(String us, String name, String lastname, int id_user) {
        this.us = us;
        this.name = name;
        this.lastname = lastname;
        this.id_user = id_user;
    }


    public String getUs() {
        return us;
    }

    public String getName() {
        return name;
    }

    public String getLastname(){return lastname;}

    public int getIdUser(){return id_user;}

}