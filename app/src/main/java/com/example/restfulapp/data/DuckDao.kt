package com.example.restfulapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DuckDao {
    @Query("SELECT * FROM ducks ORDER BY id ASC")
    fun getAll(): LiveData<List<Duck>>

    @Insert(entity = Duck::class)
    suspend fun insert(duck: Duck)
}