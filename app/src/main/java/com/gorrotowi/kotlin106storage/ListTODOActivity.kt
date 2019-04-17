package com.gorrotowi.kotlin106storage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.gorrotowi.kotlin106storage.roomdatabase.AppDatabase
import kotlinx.android.synthetic.main.activity_list_todo.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListTODOActivity : AppCompatActivity() {

    lateinit var database: AppDatabase
    val adapter = AdapterTasks()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_todo)

        toolbarListTodo?.let { toolbar ->

            val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

            val userName = preferences?.getString("user_name", "")

            toolbar.title = "TODO List de $userName"
            setSupportActionBar(toolbar)
        }

        rcTodoList?.layoutManager = LinearLayoutManager(this@ListTODOActivity, RecyclerView.VERTICAL, false)
        rcTodoList?.adapter = adapter

        adapter.setListener { position, entityTODO ->
            Log.e("Position Item", "$position")
            val intent = Intent(this@ListTODOActivity, EditTaskActivity::class.java)
            intent.putExtra("IdEntity", entityTODO.id)
            startActivity(intent)
        }

        database = Room.databaseBuilder(
            this@ListTODOActivity,
            AppDatabase::class.java,
            "MyRoomDatabase"
        ).build()

        fabAdd?.setOnClickListener {
            val intent = Intent(this@ListTODOActivity, EditTaskActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch(Dispatchers.Main) {
            //            database
//                .todoDAO()
//                .insertAll(
//                    EntityTODO(title = "Task 1", descriptionTask = "Description 1", date = "6 april"),
//                    EntityTODO(title = "Task 2", descriptionTask = "Description 2", date = "6 april"),
//                    EntityTODO(title = "Task 3", descriptionTask = "Description 3", date = "6 april")
//                )

            val tasksList = async(Dispatchers.IO) {
                database.todoDAO().getAll()
            }.await()

            tasksList.map {
                Log.d("Entity", "$it")
            }
//            runOnUiThread {
            adapter.dataList = tasksList
//            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.actionLogOut) {

            val preferences = getSharedPreferences(
                preferencesName,
                Context.MODE_PRIVATE
            )

            preferences?.edit()?.clear()?.apply()

            val intent = Intent(
                this@ListTODOActivity,
                MainActivity::class.java
            )
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
