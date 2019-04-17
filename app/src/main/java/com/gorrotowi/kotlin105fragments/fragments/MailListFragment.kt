package com.gorrotowi.kotlin105fragments.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gorrotowi.kotlin105fragments.R
import com.gorrotowi.kotlin105fragments.adapters.AdapterMailList
import com.gorrotowi.kotlin105fragments.entitys.Email
import kotlinx.android.synthetic.main.fragment_mail_list.*


class MailListFragment : Fragment() {

    private val adapterMail by lazy {
        Log.e("Adapter", "Init Adapter")
        AdapterMailList()
    }

    private lateinit var mailListener: (Int, Email) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mail_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcMailList?.adapter = adapterMail

        val dataMailList = getMails()
//        adapterMail.setData(dataMailList)
        adapterMail.dataList = dataMailList

        adapterMail.setListener { position, email ->
            mailListener.invoke(position, email)
        }

    }

    fun setEmailListener(func: (Int, Email) -> Unit) {
        mailListener = func
    }

    private fun getMails(): List<Email> = (0..30).map {
        Email(it, "Title mail $it", "Subject $it", getString(R.string.lorem_mail))
    }

    companion object {
        val instance: MailListFragment
            get() = MailListFragment()
    }

}
