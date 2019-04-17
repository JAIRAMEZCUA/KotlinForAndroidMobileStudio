package com.gorrotowi.kotlin106storage.roomdatabase

import androidx.room.*

@Dao
interface TodoDAO {

    @Query("SELECT * FROM tasksEntity")
    fun getAll(): List<EntityTODO>

    @Insert
    fun insertAll(vararg tasks: EntityTODO)

    @Query("SELECT * FROM tasksEntity WHERE id LIKE :idEntity")
    fun findTaskById(idEntity: Int): EntityTODO

    @Delete
    fun deleteEntity(entityTODOTask: EntityTODO)

    @Update
    fun updateTask(vararg et1: EntityTODO)

}