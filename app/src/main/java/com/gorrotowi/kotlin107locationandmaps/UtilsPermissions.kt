package com.gorrotowi.kotlin107locationandmaps

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.hasPermission(permission: String): Boolean {
//    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        this.checkSelfPermission(permission) ==
//                PackageManager.PERMISSION_GRANTED
//    } else {
//        true
//    }
    return ContextCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

fun Context.permissionsToRequest(wantedPermissions: Array<String>): List<String> {
    val resultPermissions = ArrayList<String>()
    for (permission in wantedPermissions) {
        if (!this.hasPermission(permission)) {
            resultPermissions.add(permission)
        }
    }

    return resultPermissions
}