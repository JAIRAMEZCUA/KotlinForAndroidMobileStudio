package com.gorrotowi.kotlin108googlemaps

import android.app.Application
import com.google.android.libraries.places.api.Places

class KotlinMapsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Places.initialize(this@KotlinMapsApplication, "")

    }

}