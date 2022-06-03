package com.example.ladm_u4_app_bluetooth_maestro.Util

import android.app.AlertDialog
import android.content.ContentValues
import android.database.sqlite.SQLiteException
import com.example.ladm_u4_app_bluetooth_maestro.MainActivity_menu_Maestro

class Asistencia(o: MainActivity_menu_Maestro) {
    var nocontrol = ""
    var fecha = ""
    var o=o
    var error = ""

    fun insertar():Boolean{
        val baseDatos = BaseDatos(o,"LISTAHOY",null,1)
        error = ""
        try {
            val tabla = baseDatos.writableDatabase
            var datos = ContentValues()

            datos.put("NOCONTROL",nocontrol)
            datos.put("FECHA",fecha)

            var respuesta = tabla.insert("ASISTENCIA",null,datos)
            if(respuesta == -1L){
                return false
            }

        }catch (e: SQLiteException){
            this.error = e.message!!
            AlertDialog.Builder(o)
                .setTitle("Error")
                .setMessage(error)
                .show()
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }

    fun asistenciaXFecha(fecha: String): ArrayList<Asistencia>{
        val baseDatos = BaseDatos(o,"LISTAHOY",null,1)
        var arreglo = ArrayList<Asistencia>()
        try {
            val tabla = baseDatos.readableDatabase
            var cursor = tabla.query("ASISTENCIA",arrayOf("*"),"FECHA=?",arrayOf(fecha),null,null,null)
            if(cursor.moveToFirst()){//Si hubo o no resultados
                do {
                    val asis = Asistencia(o)
                    asis.nocontrol = cursor.getString(0)
                    asis.fecha = cursor.getString(1)
                    arreglo.add(asis)
                }while (cursor.moveToNext())
            }

        }catch (e: SQLiteException){
            AlertDialog.Builder(o)
                .setTitle("Error")
                .setMessage(e.message!!)
                .show()
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
}