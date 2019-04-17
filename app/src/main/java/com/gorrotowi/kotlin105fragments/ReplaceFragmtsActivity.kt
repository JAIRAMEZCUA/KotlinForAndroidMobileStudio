package com.gorrotowi.kotlin105fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.gorrotowi.kotlin105fragments.fragments.FragmentOne
import com.gorrotowi.kotlin105fragments.fragments.FragmentThree
import com.gorrotowi.kotlin105fragments.fragments.FragmentTwo
import kotlinx.android.synthetic.main.activity_replace_fragmts.*

class ReplaceFragmtsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replace_fragmts)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, FragmentOne.instance)
                .commit()
        }

        btn1?.setOnClickListener(this)
        btn2?.setOnClickListener(this)
        btn3?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
//                Toast.makeText(this@ReplaceFragmtsActivity, "asdfasf", Toast.LENGTH_SHORT).show()
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragmentContainer, FragmentOne.instance)
//                    .addToBackStack(null)
//                    .commit()
                FragmentOne.instance.replace()
            }
            R.id.btn2 -> {
                supportFragmentManager.transaction(true) {
                    replace(R.id.fragmentContainer, FragmentTwo.instance)
                }
            }
            R.id.btn3 -> {
                FragmentThree.instance.replace()
            }
        }
    }

    private fun Fragment.replace(@IdRes container: Int = R.id.fragmentContainer) {
        supportFragmentManager
            .beginTransaction()
            .replace(container, this)
            .addToBackStack(null)
            .commit()

    }

}
