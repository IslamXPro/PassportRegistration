package com.example.passregistr.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passregistr.Dao.UserDao
import com.example.passregistr.Entity.User

@Database(entities = [User::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: DataBase? = null

        @Synchronized
        fun getInstance(context: Context): DataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, DataBase::class.java, "db_user")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}