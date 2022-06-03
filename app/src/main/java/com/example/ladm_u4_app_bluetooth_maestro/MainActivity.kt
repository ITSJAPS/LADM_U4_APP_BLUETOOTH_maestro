package com.example.ladm_u4_app_bluetooth_maestro

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.ladm_u4_app_bluetooth_maestro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_ENABLE_BT:Int=1
    private val REQUEST_CODE_DISCOVEREABLE_BT:Int=2

    lateinit var binding: ActivityMainBinding

    //adapter
    val bAdapter= BluetoothAdapter.getDefaultAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //check if bluetooth is available or not
        //set image according to bluetooth status(on/off)
        if (bAdapter.isEnabled) {
            binding.bluetoothIcono.setImageResource(R.drawable.onblue)
            binding.status.text="Bluetooth esta Activo"
        } else {
            binding.bluetoothIcono.setImageResource(R.drawable.offblue)
            binding.status.text="Bluetooth no  esta Activo"
        }
        this.setTitle("MAESTRO, Bienvenido!")

        //on btn click
        binding.encender.setOnClickListener {
            if (bAdapter.isEnabled) {
                //esta ya encendido
                Toast.makeText(this, "El bluetooth esta Enecendido YA ", Toast.LENGTH_LONG).show()
            } else {
                //encender BLUETOOTH
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                }
                val intent= Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent,REQUEST_CODE_ENABLE_BT)

                binding.bluetoothIcono.setImageResource(R.drawable.onblue)
                binding.status.text="Bluetooth esta Activo"
            }
        }

        //off btn click
        binding.apagar.setOnClickListener {
            if (!bAdapter.isEnabled) {
                //esta ya apagado
                Toast.makeText(this, "El bluetooth esta  apagado YA", Toast.LENGTH_LONG).show()
            } else {
                //apagar
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                }
                bAdapter.disable()
                binding.bluetoothIcono.setImageResource(R.drawable.offblue)
                binding.status.text="Bluetooth No  esta Activo"

                Toast.makeText(this, "El bluetooth apagado ", Toast.LENGTH_LONG).show()

            }
        }

        binding.visible.setOnClickListener {
            if (!bAdapter.isDiscovering){
                Toast.makeText(this, "Haciendo la lista de dispositivos disponibles", Toast.LENGTH_LONG).show()
                val intent= Intent(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
                startActivityForResult(intent,REQUEST_CODE_DISCOVEREABLE_BT)

            }
        }


        binding.dispositivosDis.setOnClickListener {
            if (bAdapter.isEnabled) {
                binding.listaDispositivos.text="Dispositivos emparejados"
                //obtenemos la lista
                val devices = bAdapter.bondedDevices
                for (device in devices){
                    val deviceName=device.name
                    binding.listaDispositivos.append("\nDispositivo : $deviceName , $device" )
                }

            }else{
                Toast.makeText(this, "Enciende el Bluetooth primero", Toast.LENGTH_LONG).show()
            }
        }

        binding.activitynuevo.setOnClickListener {
            val intent = Intent(this, MainActivity_menu_Maestro::class.java)
            startActivity(intent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_CODE_ENABLE_BT->
                if(requestCode == Activity.RESULT_OK){
                    binding.bluetoothIcono.setImageResource(R.drawable.onblue)
                    Toast.makeText(this, "El bluetooth esta encendido ", Toast.LENGTH_LONG).show()
                }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menuoculto,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.acerca->{
                Toast.makeText(this,"Ahora eres Tecnologia",Toast.LENGTH_LONG).show()
            }
            R.id.salir->{
                finish()
            }
        }

        return true
    }

}