package com.caucaragp.worldskills.tsppsp.models;

public class CTimeLog {
    //Declaración de variables de la clase CTimeLog
    private int id;
    private String phase;
    private String start;
    private int interruption;
    private String stop;
    private int delta;
    private String comments;
    private int project;

    //Constructor vacio
    public CTimeLog() {
    }

    //Función para obtener la variable id
    public int getId() {
        return id;
    }

    //Método para ingresar la variable id
    public void setId(int id) {
        this.id = id;
    }

    //Función para obtener la variable phase
    public String getPhase() {
        return phase;
    }

    //Método para ingresar la variable phase
    public void setPhase(String phase) {
        this.phase = phase;
    }

    //Función para obtener la variable start
    public String getStart() {
        return start;
    }

    //Método para ingresar la variable start
    public void setStart(String start) {
        this.start = start;
    }

    //Función para obtener la variable interruption
    public int getInterruption() {
        return interruption;
    }

    //Método para ingresar la variable interruption
    public void setInterruption(int interruption) {
        this.interruption = interruption;
    }

    //Función para obtener la variable stop
    public String getStop() {
        return stop;
    }

    //Método para ingresar la variable stop
    public void setStop(String stop) {
        this.stop = stop;
    }

    //Función para obtener la variable delta
    public int getDelta() {
        return delta;
    }

    //Método para ingresar la variable delta
    public void setDelta(int delta) {
        this.delta = delta;
    }

    //Función para obtener la variable comments
    public String getComments() {
        return comments;
    }

    //Método para ingresar la variable comments
    public void setComments(String comments) {
        this.comments = comments;
    }

    //Función para obtener la variable project
    public int getProject() {
        return project;
    }

    //Método para ingresar la variable project
    public void setProject(int project) {
        this.project = project;
    }


}
