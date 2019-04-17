package com.gorrotowi.kotlin107locationandmaps

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_gpslocation.*

class GPSLocationActivity : AppCompatActivity(),
    GoogleApiClient.OnConnectionFailedListener,
    GoogleApiClient.ConnectionCallbacks {

    private lateinit var locationCallback: LocationCallback
    private var locationRequest: LocationRequest? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    lateinit var googleApiClient: GoogleApiClient
    lateinit var resultAddressReceiver: AddressResultReceiver

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpslocation)

        resultAddressReceiver = AddressResultReceiver(Handler())

        resultAddressReceiver.setonFetchAddressListener { address ->
            Log.wtf("AddressGeocoder", address)
        }

        googleApiClient = GoogleApiClient.Builder(this@GPSLocationActivity)
            .enableAutoManage(this@GPSLocationActivity, this)
            .addConnectionCallbacks(this)
            .addApi(LocationServices.API)
            .build()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@GPSLocationActivity)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.locations.map { location ->
                    Log.w("LocationUpdates", "${location?.toString()}")
                }
            }
        }

        btnGetAddress?.setOnClickListener {
            fusedLocationClient.lastLocation?.addOnSuccessListener { lastKnowLocation ->
                if (!Geocoder.isPresent()) {
                    Toast.makeText(
                        this@GPSLocationActivity,
                        "Geocoder no disponible para obtener la dirección",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    startIntentService(lastKnowLocation)
                }
            }
        }

    }

    private fun startIntentService(lastKnowLocation: Location?) {
        val intent = Intent(
            this@GPSLocationActivity,
            FetchAddressIntentService::class.java
        ).apply {
            putExtra(RECEIVER_LOCATION, resultAddressReceiver)
            putExtra(LOCATION_DATA_EXTRA, lastKnowLocation)
        }
        startService(intent)
    }

    override fun onStart() {
        super.onStart()
        if (::googleApiClient.isInitialized) {
            googleApiClient.connect()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    override fun onPause() {
        super.onPause()
        if (::googleApiClient.isInitialized && googleApiClient.isConnected) {
            googleApiClient.disconnect()
        }
    }

    override fun onStop() {
        super.onStop()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onConnected(extraBundle: Bundle?) {
        enableLocation()
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.e("onConnectionSuspended", "$p0")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.e("onconnectionFailed", "$connectionResult")
    }

    @SuppressLint("MissingPermission")
    private fun enableLocation() {

        fusedLocationClient.lastLocation?.addOnSuccessListener { location ->
            Log.d("LastLocation", "--->> ${location?.toString()}")
        }

        locationRequest = createLocationRequest()

        val locationSettingsRequest = locationRequest?.let { locationRq ->
            LocationSettingsRequest.Builder()
                .addLocationRequest(locationRq)
                .build()
        }

        val clientLocation = LocationServices.getSettingsClient(this@GPSLocationActivity)
        val taskClient = clientLocation.checkLocationSettings(locationSettingsRequest)

        taskClient?.addOnSuccessListener {
            Log.e("taskClientSuccest", "---> ${it?.toString()}")
        } ?: also { Log.e("TaskClient", "$taskClient") }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)

    }

    private fun createLocationRequest() = LocationRequest.create().apply {
        interval = 1000L
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

}