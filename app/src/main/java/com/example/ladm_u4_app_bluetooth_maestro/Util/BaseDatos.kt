package com.example.ladm_u4_app_bluetooth_maestro.Util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE ASISTENCIA(NOCONTROL VARCHAR(8), HORA INTEGER, FECHA DATE)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //03/06/2022/03:00/40/30
        db!!.execSQL("CREATE TABLE ASISTENCIA2(NOCONTROL VARCHAR(8), FECHA VARCHAR(30), PRIMARY KEY (NOCONTROL, FECHA))")
        db!!.execSQL("INSERT INTO ASISTENCIA2 (NOCONTROL,FECHA) SELECT NOCONTROL,FECHA FROM ASISTENCIA")
        db!!.execSQL("DROP TABLE ASISTENCIA")
        db!!.execSQL("ALTER TABLE ASISTENCIA2 RENAME TO ASISTENCIA")
    }
}