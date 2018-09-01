package com.caucaragp.worldskills.tsppsp.models;

import java.util.List;

public class Constants {
    //Declaración de constantes para la creación de base de datos
    public static final String DATABASE_NAME= "tsppsp.db";
    public static final int DATABASE_VERSION= 1;
    public static final String TABLE_PROJECT= "CREATE TABLE PROJECT(IDPROJECT INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME INTEGER);";
    public static final String TABLE_TIMELOG= "CREATE TABLE TIMELOG(IDTIMELOG INTEGER PRIMARY KEY AUTOINCREMENT, PHASE TEXT, " +
            "START TEXT, INTERRUPTION INTEGER, STOP TEXT, DELTA INTEGER, COMMENTS TEXT, PROJECT INTEGER, " +
            "FOREIGN KEY (PROJECT) REFERENCES PROJECT(IDPROJECT));";
    public static final String TABLE_DEFECTLOG= "CREATE TABLE DEFECTLOG(IDDEFECTLOG INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, " +
            "TYPE TEXT, PHASEI TEXT, PHASER TEXT, FIXTIME INTEGER, DEFECTD TEXT, PROJECT INTEGER, " +
            "FOREIGN KEY (PROJECT) REFERENCES PROJECT(IDPROJECT));";

    //Vector constante para las fases;
    public static final String[] LIST_PHASES={"PLAN","DLD","CODE","COMPILE","UT","PM"};

}
