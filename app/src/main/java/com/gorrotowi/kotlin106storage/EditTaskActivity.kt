package com.gorrotowi.kotlin106storage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.gorrotowi.kotlin106storage.roomdatabase.AppDatabase
import com.gorrotowi.kotlin106storage.roomdatabase.EntityTODO
import kotlinx.android.synthetic.main.activity_edit_task.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditTaskActivity : AppCompatActivity() {

    lateinit var database: AppDatabase
    var entityTODOTask: EntityTODO? = null
    var idEntity = Int.MIN_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        database = Room.databaseBuilder(
            this@EditTaskActivity,
            AppDatabase::class.java,
            "MyRoomDatabase"
        ).build()

        intent?.extras?.let { bundle ->
            idEntity = bundle.getInt("IdEntity", Int.MIN_VALUE)
        }

        if (idEntity == Int.MIN_VALUE) {
            btnDeleteTask?.visibility = View.GONE
        } else {
            fillTask(idEntity)
        }

        btnDeleteTask?.setOnClickListener {
            GlobalScope.launch {
                entityTODOTask?.let { it1 ->
                    database.todoDAO().deleteEntity(it1)
                }
                finish()
            }
        }

        btnSaveTask?.setOnClickListener {
            val titleTask = edtxTitleTask?.text?.toString() ?: ""
            val descrTask = edtxDescriptionTask?.text?.toString() ?: ""
            if (idEntity == Int.MIN_VALUE) {
                entityTODOTask = EntityTODO(title = titleTask, descriptionTask = descrTask)
                GlobalScope.launch {
                    entityTODOTask?.let { it1 ->
                        database.todoDAO().insertAll(it1)
                    }
                }
            } else {
                entityTODOTask?.title = titleTask
                entityTODOTask?.descriptionTask = descrTask
                GlobalScope.launch {
                    entityTODOTask?.let { et1 ->
                        database.todoDAO().updateTask(et1)
                    }
                }
            }

            finish()
        }

    }

    private fun fillTask(idEntity: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = withContext(Dispatchers.IO) {
                database.todoDAO().findTaskById(idEntity)
            }
//            entityTODOTask = database.todoDAO().findTaskById(idEntity)

//            Looper.getMainLooper()?.queue?.addIdleHandler {
            edtxTitleTask?.setText(data.title ?: "")
            edtxDescriptionTask?.setText(data.descriptionTask ?: "")
//                return@addIdleHandler false
//            }
        }
    }
}
