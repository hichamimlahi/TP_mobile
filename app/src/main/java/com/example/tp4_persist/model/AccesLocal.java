package com.example.tp4_persist.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccesLocal {
    private String nomDB = "DBGEmploye";
    private Integer vesionDB = 1;
    private MySqlLite mySqlLite;
    private SQLiteDatabase db;

    public AccesLocal(Context context){
        mySqlLite = new MySqlLite(context,nomDB,null,vesionDB);
    }

    public void ajouEmp(Employe emp){
        db = mySqlLite.getWritableDatabase();
        String req = "INSERT INTO EMPLOYE (NOM,PRENOM,AGE,CIN,FONCTION,DOTI) values";
        req += "(\""+emp.getNom()+"\",\""+emp.getPrenom()+"\","+emp.getAge()+",\""+emp.getCin()+"\",\""+emp.getFonction()+"\","+emp.getDoti()+")";
        db.execSQL(req);
    }

    public Employe recupLastEmp(){
        db = mySqlLite.getReadableDatabase();
        String req = "SELECT * FROM EMPLOYE";
        Cursor cursor = db.rawQuery(req, null);
        Employe employe=null;
        cursor.moveToLast();
        if (!cursor.isAfterLast()){
            String nm = cursor.getString(1);
            String pnm = cursor.getString(2);
            Integer age = cursor.getInt(3);
            String cin = cursor.getString(4);
            String fonc = cursor.getString(5);
            Integer doti = cursor.getInt(6);
            employe = new Employe(nm,pnm,cin,fonc,age,doti);
        }
        cursor.close();
        return employe;
    }

}