package com.example.restfulapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Duck::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun duckDao(): DuckDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ducks"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}