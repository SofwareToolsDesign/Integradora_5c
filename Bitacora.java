package com.stdesign.bitacorasutd.model;

/**
 * Created by Hurón Padilla on 4/16/2018.
 */

public class Bitacora {
    private String date;
    private String hour;
    private int lab;
    private String subject;
    private String group;
    private String practice;
    private int teacher;
    private String equipment;
    private String observations;

    public Bitacora(String date, String hour, int lab, String subject, String group, String practice, int teacher, String equipment, String observations) {
        this.date=date;
        this.hour=hour;
        this.lab=lab;
        this.subject=subject;
        this.group=group;
        this.practice=practice;
        this.teacher=teacher;
        this.equipment=equipment;
        this.observations=observations;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public int getLab() {
        return lab;
    }

    public String getSubject() {
        return subject;
    }

    public String getGroup() {
        return group;
    }

    public String getPractice() {
        return practice;
    }

    public int getTeacher() {
        return teacher;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getObservations() {
        return observations;
    }
}
