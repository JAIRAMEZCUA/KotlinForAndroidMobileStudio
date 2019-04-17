package com.gorrotowi.kotlin106storage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val preferencesName = "myLoginPreferences"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val preferences = getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
//        val preferences2 = getSharedPreferences("mySharedPreferences2", Context.MODE_PRIVATE)
//
////        preferences?.edit()?.clear()?.commit()
//        val editPreferences = preferences?.edit()
//
//        editPreferences?.putBoolean("isLogged", true)
//        editPreferences?.putInt("Age", 20)
//        editPreferences?.putString("Name", "Sebastian")
//
////        editPreferences?.commit()
//        editPreferences?.apply()
//
//        val name = preferences?.getString("Name", "default")
//        val isLogged = preferences?.getBoolean("isLogged", false)
//
//        Log.d("Values ---------->", "$name")
//        Log.d("Values ---------->", "$isLogged")
//
//        preferences?.edit()?.remove("Age")?.commit()
//
//        if (preferences?.contains("Age") == true) {
//            Log.e("Age contains", "${preferences.getInt("Age", 0)}")
//        } else {
//            Log.e("Age", "Not Exist")
//        }

        val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

        if (preferences?.getBoolean("isLogged", false) == true) {
            //startActivity Logged
            val intent = Intent(this@MainActivity, ListTODOActivity::class.java)
            startActivity(intent)
        } else {
            //startActivity login
            val intent = Intent(this@MainActivity, LoginActivirty::class.java)
            startActivity(intent)
        }

        finish()

    }
}
