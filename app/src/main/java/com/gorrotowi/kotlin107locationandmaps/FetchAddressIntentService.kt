package com.gorrotowi.kotlin107locationandmaps

import android.app.IntentService
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import java.io.IOException
import java.util.*

const val LOCATION_DATA_EXTRA = "LocationService.LOCATION_DATA_EXTRA"
const val RESULT_DATA_KEY = "LocationService.LOCATION_DATA_KEY"
const val RECEIVER_LOCATION: String = "LocationService.RECEIVER"
const val FAULURE_DATA_RESULT: Int = 2001
const val SUCCESS_DATA_RESULT: Int = 2002

class FetchAddressIntentService : IntentService("FetchAddressIntentService") {

    private var receiver: ResultReceiver? = null

    override fun onHandleIntent(intent: Intent?) {
        val geocoder = Geocoder(this, Locale.getDefault())
        var errorMessage = ""

        val location: Location? = intent?.getParcelableExtra(LOCATION_DATA_EXTRA)
        receiver = intent?.getParcelableExtra(RECEIVER_LOCATION)

        var address: List<Address> = emptyList()

        try {
            if (location != null) {
                address = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )
                Log.e("addressList", " --- ${address?.toString()}")
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            errorMessage = "Servicio no disponible"
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            errorMessage = "LatLng es invalido, intenta de nuevo"
        }

        if (address.isNotEmpty()) {
            val singleAddress = address[0]
            val addressFragments = with(singleAddress) {
                (0..maxAddressLineIndex).map { getAddressLine(it) }
            }

            val singleAddressLineJoined = addressFragments.joinToString(separator = "\n")
            deliveryResultToReceiver(SUCCESS_DATA_RESULT, singleAddressLineJoined)
        } else {
            if (errorMessage.isEmpty()) {
                errorMessage = "Sin dirección disponible"
                deliveryResultToReceiver(FAULURE_DATA_RESULT, errorMessage)
            }
        }

    }

    private fun deliveryResultToReceiver(resultCode: Int, message: String) {
        val bundle = Bundle().apply {
            putString(RESULT_DATA_KEY, message)
        }
        receiver?.send(resultCode, bundle)
    }

}