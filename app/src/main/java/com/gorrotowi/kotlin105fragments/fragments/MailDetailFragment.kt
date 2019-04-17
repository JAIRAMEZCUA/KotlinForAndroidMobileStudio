package com.gorrotowi.kotlin105fragments.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gorrotowi.kotlin105fragments.R
import com.gorrotowi.kotlin105fragments.entitys.Email
import kotlinx.android.synthetic.main.fragment_mail_detail.*

class MailDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mail_detail, container, false)
    }

    fun showEmailDetail(email: Email) {
        txtMailTitle?.text = email.title
        txtMailSubject?.text = email.subject
        txtMailBody?.text = email.body
    }

    companion object {
        val instance: MailDetailFragment
            get() = MailDetailFragment()
    }

}
