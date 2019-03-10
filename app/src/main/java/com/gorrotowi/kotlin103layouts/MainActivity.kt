package com.gorrotowi.kotlin103layouts

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import kotlinx.android.synthetic.main.login_view.*
import android.util.Pair as UtilPair

class MainActivity : AppCompatActivity() {

    private var set = false

    private val constraint1 = ConstraintSet()
    private val constraint2 = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)

        constraint1.clone(rootLogin)
        constraint2.clone(this, R.layout.login_view_expanded)

        imgLoginLogo?.setOnClickListener {
            TransitionManager.beginDelayedTransition(rootLogin)
            val constraintToAssing = if (set) constraint1 else constraint2
            constraintToAssing.applyTo(rootLogin)
            set = !set
        }

        btnLogin?.setOnClickListener {
            val intent = Intent(this@MainActivity, MasterActivity::class.java)
//            val activityOptions = ActivityOptionsCompat
//                .makeSceneTransitionAnimation(
//                    this@MainActivity,
//                    imgLoginLogo,
//                    getString(R.string.transition_name_logo)
//                ).toBundle()

            val pair1 = UtilPair.create(imgLoginLogo as View, imgLoginLogo?.transitionName)
            val pair2 = UtilPair.create(txtLogo as View, txtLogo?.transitionName)
            val optionsMultipleView =
                ActivityOptions.makeSceneTransitionAnimation(this, pair1, pair2)
                    .toBundle()
            startActivity(intent, optionsMultipleView)
        }

    }
}
