package com.gorrotowi.kotlin106reaml

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this@MainActivity)
        val realmConfiguration = RealmConfiguration.Builder().apply {
            name("MyRealmDB")
            schemaVersion(1)
            deleteRealmIfMigrationNeeded()
        }.build()

        val realm = Realm.getInstance(realmConfiguration)

        val alumno1 = Alumno(nombre = "Juan", grupo = "KotlinAndroid")
        val alumno2 = Alumno(nombre = "Pepe", grupo = "iOSSwift")
        val alumno3 = Alumno(nombre = "Ramon", grupo = "KotlinAndroid")
        val alumno4 = Alumno(nombre = "Alberto", grupo = "KotlinAndroid")

//        realm.beginTransaction()
//        realm.copyToRealm(alumno1)
//        realm.copyToRealm(alumno2)
//        realm.copyToRealm(alumno3)
//        realm.copyToRealm(alumno4)
//        realm.commitTransaction()

        val alumnoFromRealm = realm.where(Alumno::class.java)
            .equalTo("nombre", "Alberto")
            .findAll()

        val alumnosList = alumnoFromRealm?.toList()

        alumnosList?.map { alumnoRealm ->
            Log.d("Alumno From Realm", "${alumnoRealm?.toString()}")
        }

//        realm?.executeTransaction { realmObj ->
//            alumnoFromRealm?.deleteFirstFromRealm()
//        }

        val alumnListAfterDelete = alumnoFromRealm.toList()
        alumnListAfterDelete.map {
            Log.d("Alumno From Realm", "${it?.toString()}")
        }

    }
}


open class Alumno(
    @PrimaryKey var id: Int = 0,
    var nombre: String? = "",
    var grupo: String? = ""
) : RealmObject()