package com.gorrotowi.kotlin106storage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_login_activirty.*

class LoginActivirty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activirty)

        btnLogin?.setOnClickListener {
            val name = edtxLogin?.text?.toString()
            if (!name.isNullOrBlank()) {
                val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

                preferences?.edit {
                    putString("user_name", name)
                    putBoolean("isLogged", true)
                }

                val intent = Intent(
                    this@LoginActivirty,
                    ListTODOActivity::class.java
                )
                startActivity(intent)
                finish()
            }
        }
    }
}
