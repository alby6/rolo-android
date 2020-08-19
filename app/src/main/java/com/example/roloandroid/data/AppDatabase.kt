package com.example.roloandroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object {

        private const val databaseName = "rolo_android"

        fun buildDatabase(context : Context) : AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java, databaseName
            ).build()
        }


    }

}
