package com.gorrotowi.kotlin105fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorrotowi.kotlin105fragments.entitys.Email
import com.gorrotowi.kotlin105fragments.fragments.MailDetailFragment
import com.gorrotowi.kotlin105fragments.fragments.MailListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentList = supportFragmentManager.findFragmentById(R.id.fragmentMailList) as? MailListFragment

        fragmentList?.setEmailListener { position, email ->
            when (position) {
                0 -> {
                    startActivity(Intent(this@MainActivity, ReplaceFragmtsActivity::class.java))
                }
                1 -> {
                    startActivity(Intent(this@MainActivity, ViewPagerActivity::class.java))
                }
                else -> {
                    showEmail(email)
                }
            }
        }

    }

    private fun showEmail(email: Email) {
        val fragmentDetail = supportFragmentManager.findFragmentById(R.id.fragmentMailDetail) as? MailDetailFragment
        val fragmentDetail2 = supportFragmentManager.findFragmentById(R.id.fragmentMailDetail2) as? MailDetailFragment

        fragmentDetail?.let {
            fragmentDetail.showEmailDetail(email)
        } ?: also {
            val intent = Intent(this@MainActivity, DetailMailActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }
}
