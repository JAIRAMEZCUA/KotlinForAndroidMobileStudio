package com.gorrotowi.kotlin105fragments.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gorrotowi.kotlin105fragments.MainActivity
import com.gorrotowi.kotlin105fragments.R
import kotlinx.android.synthetic.main.fragment_fragment_one.*

class FragmentOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtFragmentOne?.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    companion object {
        val instance: FragmentOne
            get() = FragmentOne()
    }

}
