package com.caucaragp.worldskills.tsppsp.models;

public class Results {
    //Declaración de variables
    private String phase;
    private int time;
    private double percent;

    public Results() {
    }

    //Función para obtener la variable phase
    public String getPhase() {
        return phase;
    }

    //Método para ingresar la variable phase
    public void setPhase(String phase) {
        this.phase = phase;
    }

    //Función para obtener la variable time
    public int getTime() {
        return time;
    }

    //Método para ingresar la variable time
    public void setTime(int time) {
        this.time = time;
    }

    //Función para obtener la variable percent
    public double getPercent() {
        return percent;
    }

    //Método para ingresar la variable percent
    public void setPercent(double percent) {
        this.percent = percent;
    }
}
