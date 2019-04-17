package com.gorrotowi.kotlin107locationandmaps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val REQUEST_LOCATION_CODE: Int = 1009
    lateinit var locationManager: LocationManager

    var alertDialog: AlertDialog? = null
    var alertDialogTryAgain: AlertDialog? = null

    val permissionList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val permissionListToRequest = permissionsToRequest(permissionList)

        if (permissionListToRequest.isNotEmpty()) {
            askForPermissions(permissionListToRequest)
        } else {
            enableLocation()
        }

    }

    override fun onDestroy() {
        alertDialog?.dismiss()
        alertDialogTryAgain?.dismiss()
        super.onDestroy()
    }

    private fun askForPermissions(permissionListToRequest: List<String>) {
        alertDialog = AlertDialog.Builder(this@MainActivity)
            .setTitle(getString(R.string.alert_dialog_location_title))
            .setMessage(getString(R.string.alert_dialog_location_message))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.alert_dialog_accept)) { dialog, _ ->
                dialog.dismiss()
                requestLocationPermissions(permissionListToRequest)
            }
            .setNegativeButton(getString(R.string.alert_dialog_location_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog?.show()
    }

    private fun requestLocationPermissions(permissionListToRequest: List<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionListToRequest.toTypedArray(), REQUEST_LOCATION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("onRequestPermissions", "${permissions.map { it }}")
        Log.d("onRequestPermissions", "${grantResults.map { it }}")
        if (requestCode == REQUEST_LOCATION_CODE) {
            val permissionsBoolensResult = grantResults.map {
                it == PackageManager.PERMISSION_GRANTED
            }
            val permissionGranted = permissionsBoolensResult.firstOrNull() ?: false
            if (permissionGranted) {
                enableLocation()
            } else {
                tryPermissionsAgain(permissions)
            }
        }
    }

    private fun tryPermissionsAgain(permissions: Array<out String>) {
        if (permissions.isNotEmpty()) {
            alertDialogTryAgain = AlertDialog.Builder(this@MainActivity)
                .setTitle(getString(R.string.alert_dialog_location_title))
                .setMessage(getString(R.string.alert_dialog_location_message_try_again))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.alert_dialog_accept)) { dialog, _ ->
                    dialog.dismiss()
                    requestLocationPermissions(permissions.toList())
                }
                .setNegativeButton(getString(R.string.alert_dialog_location_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()

            alertDialogTryAgain?.show()
        } else {
            Toast.makeText(
                this@MainActivity,
                "Algo ocurrio mal, intenta nuevamente",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableLocation() {
        val bestProvider = locationManager.getBestProvider(Criteria(), true)
        Log.i("BestPrivider", "--->> $bestProvider")
        val lastKnowLocation = locationManager.getLastKnownLocation(bestProvider)
        Log.e("LASTLOCATION", "$lastKnowLocation")

        locationManager.requestLocationUpdates(
            bestProvider,
            5000,
            10f,
            object : LocationListener {
                override fun onLocationChanged(location: Location?) {
                    Log.e("locationUpdate", "${location?.toString()}")
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    Log.e("provider --->", "$provider status $status bundle ${extras?.toString()}")
                }

                override fun onProviderEnabled(provider: String?) {
                    Log.e("onProviderEnabled", "$provider")
                }

                override fun onProviderDisabled(provider: String?) {
                    Log.e("onProviderDisabled", "$provider")
                }
            })
    }
}
