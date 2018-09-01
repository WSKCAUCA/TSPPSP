package com.caucaragp.worldskills.tsppsp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ManageDB {
    //Declaraciónde variables
    Context context;
    GestorDB gestorDB;
    SQLiteDatabase db;


    public ManageDB(Context context) {
        this.context = context;
    }

    //Método para abrir la base de datos en modo Lectura
    public void openReadB(){
        gestorDB = new GestorDB(context);
        db = gestorDB.getReadableDatabase();
    }

    //Método para abrir la base de datos en modo Escritura
    public void openWriteB(){
        gestorDB = new GestorDB(context);
        db = gestorDB.getWritableDatabase();
    }

    //Método para cerrar la base de datos
    public void closeDB(){
        db.close();
    }

    //Método para ingresar valores a la tabla PROJECT
    public void insertProject(CProyecto proyecto){
        openWriteB();
        ContentValues values = new ContentValues();
        values.put("NAME",proyecto.getName());
        values.put("TIME",proyecto.getTime());
        db.insert("PROJECT",null,values);
        closeDB();
    }

    //Método para actualizar valores a la tabla PROJECT
    public void updateProject(CProyecto proyecto){
        openWriteB();
        ContentValues values = new ContentValues();
        values.put("TIME",proyecto.getTime());
        db.update("PROJECT",values,"IDPROJECT ="+proyecto.getId(),null);
        closeDB();
    }

    //Método para ingresar valores a la tabla TIMELOG
    public void insertTimeLog(CTimeLog timeLog){
        openWriteB();
        ContentValues values = new ContentValues();
        values.put("PHASE",timeLog.getPhase());
        values.put("START",timeLog.getStart());
        values.put("INTERRUPTION",timeLog.getInterruption());
        values.put("STOP",timeLog.getStop());
        values.put("DELTA",timeLog.getDelta());
        values.put("COMMENTS",timeLog.getComments());
        values.put("PROJECT",timeLog.getProject());
        db.insert("TIMELOG",null,values);
        closeDB();
    }

    //Método para ingresar valores a la tabla DEFECTLOG
    public void insertDefectLog(CDefectLog defectLog){
        openWriteB();
        ContentValues values = new ContentValues();
        values.put("DATE",defectLog.getDate());
        values.put("TYPE",defectLog.getType());
        values.put("PHASEI",defectLog.getPhaseI());
        values.put("PHASER",defectLog.getPhaseR());
        values.put("FIXTIME",defectLog.getFixTime());
        values.put("DEFECTD",defectLog.getDefectD());
        db.insert("DEFECTLOG",null,values);
        closeDB();
    }

    //Función para listar los proyectos ingresados a la base datos
    public List<CProyecto> proyectoList(){
        openReadB();
        List<CProyecto> results = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM PROJECT;",null);
        if (cursor.moveToFirst()){
            do {
                CProyecto proyecto = new CProyecto();
                proyecto.setId(cursor.getInt(0));
                proyecto.setName(cursor.getString(1));
                proyecto.setTime(cursor.getInt(2));
                results.add(proyecto);
            }while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();

        return results;
    }

    //Función para obtener la información que va en Time in Phase
    public List<Results> timeInPhase(int project){
        openReadB();
        List<Results> results = new ArrayList<>();
        Cursor cursorP = db.rawQuery("SELECT * FROM PROJECT WHERE IDPROJECT="+project+";",null);
        int total=0;
        if (cursorP.moveToFirst()){
            do {
                total=cursorP.getInt(2);
            }while (cursorP.moveToNext());
        }
        cursorP.close();

        for (int i=0;i<Constants.LIST_PHASES.length;i++){
            Results tmpResults = new Results();
            String phase = Constants.LIST_PHASES[i];
            int tiempo=0;
            Cursor cursor = db.rawQuery("SELECT DELTA FROM TIMELOG WHERE PROJECT="+project+" AND PHASE='"+phase+"'; ",null);
            if (cursor.moveToFirst()){
                do {
                    tiempo+=cursor.getInt(0);
                }while (cursor.moveToNext());
            }
            cursor.close();
            tmpResults.setPhase(phase);
            if (total==0){
                tmpResults.setTime(0);
                tmpResults.setPercent(0);
            }else {
                if (tiempo==0){
                    tmpResults.setTime(0);
                    tmpResults.setPercent(0);
                }else {
                    float tmp1 = tiempo; float tmp2 = total;
                    double tmpP= (tmp1/tmp2)*100;
                    BigDecimal bigDecimal = new BigDecimal(tmpP);
                    tmpP = bigDecimal.setScale(3, RoundingMode.HALF_UP).doubleValue();
                    tmpResults.setTime(tiempo);
                    tmpResults.setPercent(tmpP);
                }
            }

            results.add(tmpResults);

        }

        closeDB();

        return results;
    }

    //Función para obtener la información que va en Defects Injected in Phase
    public List<Results> defectsInjected(int project){
        openReadB();
        List<Results> results = new ArrayList<>();
        Cursor cursorP = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT="+project+";",null);
        int total=0;
        if (cursorP.moveToFirst()){
            do {
                total++;
            }while (cursorP.moveToNext());
        }
        cursorP.close();

        for (int i=0;i<Constants.LIST_PHASES.length;i++){
            Results tmpResults = new Results();
            String phase = Constants.LIST_PHASES[i];
            int veces=0;
            Cursor cursor = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT="+project+" AND PHASEI='"+phase+"'; ",null);
            if (cursor.moveToFirst()){
                do {
                    veces++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            tmpResults.setPhase(phase);
            if (total==0){
                tmpResults.setTime(0);
                tmpResults.setPercent(0);
            }else {
                if (veces==0){
                    tmpResults.setTime(0);
                    tmpResults.setPercent(0);
                }else {
                    float tmp1 = veces; float tmp2 = total;
                    double tmpP= (tmp1/tmp2)*100;
                    BigDecimal bigDecimal = new BigDecimal(tmpP);
                    tmpP = bigDecimal.setScale(3, RoundingMode.HALF_UP).doubleValue();
                    tmpResults.setTime(veces);
                    tmpResults.setPercent(tmpP);
                }
            }

            results.add(tmpResults);

        }

        closeDB();

        return results;
    }

    //Función para obtener la información que va en Defects Removed in Phase
    public List<Results> defectsRemoved(int project){
        openReadB();
        List<Results> results = new ArrayList<>();
        Cursor cursorP = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT="+project+";",null);
        int total=0;
        if (cursorP.moveToFirst()){
            do {
                total++;
            }while (cursorP.moveToNext());
        }
        cursorP.close();

        for (int i=0;i<Constants.LIST_PHASES.length;i++){
            Results tmpResults = new Results();
            String phase = Constants.LIST_PHASES[i];
            int veces=0;
            Cursor cursor = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT="+project+" AND PHASER='"+phase+"'; ",null);
            if (cursor.moveToFirst()){
                do {
                    veces++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            tmpResults.setPhase(phase);
            if (total==0){
                tmpResults.setTime(0);
                tmpResults.setPercent(0);
            }else {
                if (veces==0){
                    tmpResults.setTime(0);
                    tmpResults.setPercent(0);
                }else {
                    float tmp1 = veces; float tmp2 = total;
                    double tmpP= (tmp1/tmp2)*100;
                    BigDecimal bigDecimal = new BigDecimal(tmpP);
                    tmpP = bigDecimal.setScale(3, RoundingMode.HALF_UP).doubleValue();
                    tmpResults.setTime(veces);
                    tmpResults.setPercent(tmpP);
                }
            }

            results.add(tmpResults);

        }

        closeDB();

        return results;
    }

    //Función que devuelve el tiempo del proyecto

}
