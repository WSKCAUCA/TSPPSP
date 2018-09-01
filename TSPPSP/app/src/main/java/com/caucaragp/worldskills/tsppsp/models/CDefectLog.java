package com.caucaragp.worldskills.tsppsp.models;

public class CDefectLog{
    //Declaración de variables de la clase CDefectLog
    private int id;
    private String date;
    private String type;
    private String phaseI;
    private String phaseR;
    private int fixTime;
    private String defectD;
    private int project;

    //Constructor vacio
    public CDefectLog() {
    }

    //Función para obtener la variable id
    public int getId() {
        return id;
    }

    //Método para ingresar la variable id
    public void setId(int id) {
        this.id = id;
    }

    //Función para obtener la variable date
    public String getDate() {
        return date;
    }

    //Método para ingresar la variable date
    public void setDate(String date) {
        this.date = date;
    }

    //Función para obtener la variable type
    public String getType() {
        return type;
    }

    //Método para ingresar la variable type
    public void setType(String type) {
        this.type = type;
    }

    //Función para obtener la variable phaseI
    public String getPhaseI() {
        return phaseI;
    }

    //Método para ingresar la variable phaseI
    public void setPhaseI(String phaseI) {
        this.phaseI = phaseI;
    }

    //Función para obtener la variable phaseR
    public String getPhaseR() {
        return phaseR;
    }

    //Método para ingresar la variable phaseR
    public void setPhaseR(String phaseR) {
        this.phaseR = phaseR;
    }

    //Función para obtener la variable fixTime
    public int getFixTime() {
        return fixTime;
    }

    //Método para ingresar la variable fixTime
    public void setFixTime(int fixTime) {
        this.fixTime = fixTime;
    }

    //Función para obtener la variable defectD
    public String getDefectD() {
        return defectD;
    }

    //Método para ingresar la variable defectD
    public void setDefectD(String defectD) {
        this.defectD = defectD;
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
