package com.example.ladm_u4_app_bluetooth_allumno
import android.location.Location
import android.location.LocationListener
import com.example.bluetoothchatapp.interfaces.OnLocationListener
import androidx.annotation.NonNull

import android.os.Bundle
import com.example.ladm_u4_app_bluetooth_maestro.MainActivity_menu_Maestro


class MyLocationListener(private var onLocationListener: MainActivity_menu_Maestro):LocationListener {
    override fun onLocationChanged(location: Location) {
        //onLocationListener.onLocationListener(location)
    }



    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

}