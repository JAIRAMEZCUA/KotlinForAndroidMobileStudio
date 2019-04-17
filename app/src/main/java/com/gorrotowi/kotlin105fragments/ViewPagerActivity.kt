package com.gorrotowi.kotlin105fragments

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gorrotowi.kotlin105fragments.adapters.VPAdapter
import com.gorrotowi.kotlin105fragments.fragments.FragmentOne
import com.gorrotowi.kotlin105fragments.fragments.FragmentThree
import com.gorrotowi.kotlin105fragments.fragments.FragmentTwo
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val listFragments = mutableListOf<Fragment>()
        listFragments.add(FragmentOne.instance)
        listFragments.add(FragmentTwo.instance)
        listFragments.add(FragmentThree.instance)
        listFragments.add(FragmentOne.instance)
        listFragments.add(FragmentTwo.instance)
        listFragments.add(FragmentThree.instance)

        val iconList = mutableListOf<Int>()
        iconList.add(android.R.drawable.alert_dark_frame)
        iconList.add(android.R.drawable.arrow_down_float)
        iconList.add(android.R.drawable.alert_dark_frame)
        iconList.add(android.R.drawable.arrow_down_float)
        iconList.add(android.R.drawable.alert_dark_frame)
        iconList.add(android.R.drawable.arrow_down_float)


        val adapterVP = VPAdapter(supportFragmentManager, listFragments)

        viewPagerFragmts?.adapter = adapterVP

        tabLayoutVP.setupWithViewPager(viewPagerFragmts)

        listFragments.mapIndexed { index, _ ->
            val tab = tabLayoutVP?.getTabAt(index)
            tab?.setCustomView(R.layout.custom_tab)
            val txtTab = tab?.customView?.findViewById<TextView>(R.id.txtCustomTab)
            val imgTab = tab?.customView?.findViewById<ImageView>(R.id.imgCustomTab)
            txtTab?.text = "$index"
            imgTab?.setImageResource(iconList[index])
//            tab?.text = "miTab $index"
//            tab?.setIcon(iconList[index])
        }

    }
}
