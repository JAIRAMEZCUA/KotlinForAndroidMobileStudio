package com.gorrotowi.androidkt102

import android.content.Intent
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var TAG = MainActivity::class.java.simpleName
    var statusLyfe = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        statusLyfe = "OnCreate"
        Log.i(TAG, statusLyfe)

//        val txt = findViewById<TextView>(R.id.txtMain)
//        val currentText = txtMain?.text
//        txtMain?.let {
//            with(it) {
//                text = "akjdfhasjfbasd"
//            }
//        }
//        Log.e(TAG, "$currentText")
//
//        txt.text = getString(R.string.hello_android_for_kotlin)

//        txt.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                Log.i(TAG, "Texto Clickeado")
//                finish()
//            }
//        })

//        txt.setOnClickListener {
//            Log.i(TAG, "Texto Clickeado")
//            finish()
//        }

    }

    override fun onResume() {
        super.onResume()
        statusLyfe = "onResume"
        Log.i(TAG, statusLyfe)

        btnShowName?.setOnClickListener {
            val edtxtNameStr = edtxName?.text?.toString()
//            Toast.makeText(this@MainActivity, "Hola $edtxtNameStr", Toast.LENGTH_LONG).show()
            val intent = Intent(this@MainActivity, MasterActivity::class.java)
            intent.putExtra("nombre", edtxtNameStr)
            intent.putExtra("edad", 3)
            intent.putExtra("esHumano", true)
            startActivity(intent)
        }

    }

    override fun onPause() {
        super.onPause()
        statusLyfe = "onPause"
        Log.i(TAG, statusLyfe)
    }

    override fun onDestroy() {
        statusLyfe = "onDestroy"
        Log.i(TAG, statusLyfe)
        super.onDestroy()

    }
}
