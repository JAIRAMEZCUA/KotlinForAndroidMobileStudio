package com.gorrotowi.kotlin105fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gorrotowi.kotlin105fragments.entitys.Email
import com.gorrotowi.kotlin105fragments.fragments.MailDetailFragment

class DetailMailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mail)

        intent?.extras?.let { bundle ->
            val email = bundle.getParcelable<Email>("email")
            if (email != null) {
                val detailFragment = supportFragmentManager
                    .findFragmentById(R.id.fragmentMailDetail) as? MailDetailFragment
                detailFragment?.showEmailDetail(email)
            } else {
                Toast.makeText(
                    this@DetailMailActivity,
                    "Algo ocurrio mal, intenta nuevamente",
                    Toast.LENGTH_SHORT
                )
                    .show()
                finish()
            }
        }

    }
}
