package com.gorrotowi.androidkt102

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MasterActivity : AppCompatActivity() {

    val TAG = MasterActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)

        intent?.extras?.let { bundle ->
            val nombre = bundle.getString("nombre", "")
            val edad = bundle.getInt("edad", 0)
            val esHumano = bundle.getBoolean("esHumano", false)

            Log.e(TAG, "Nombre $nombre")
            Log.e(TAG, "edad $edad")
            Log.e(TAG, "Es Humano $esHumano")
        } ?: kotlin.run {
            finish()
            Toast.makeText(
                this@MasterActivity,
                "Intenta de nuevo, algo salio mal",
                Toast.LENGTH_LONG
            ).show()
        }

    }
}
