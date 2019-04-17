package com.gorrotowi.kotlin105fragments.entitys

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Email(
    val id: Int,
    val title: String,
    val subject: String,
    val body: String
) : Parcelable