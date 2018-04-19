package com.stdesign.bitacorasutd.model;

/**
 * Created by Hurón Padilla on 4/19/2018.
 */

public class Schedule {
    private String materia;
    private String grupo;
    private int laboratorio=0;
    private String dia;
    private String hora;

    public Schedule(String materia, String grupo, int laboratorio, String dia, String hora) {
        this.materia = materia;
        this.grupo = grupo;
        this.laboratorio = laboratorio;
        this.dia = dia;
        this.hora = hora;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(int laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
