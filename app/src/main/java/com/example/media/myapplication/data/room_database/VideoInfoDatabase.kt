package com.example.vlcplayer.data.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Harshal Chaudhari on 29/9/19.
 */

@Database(entities = arrayOf(MovieInfo::class), version = 2, exportSchema = false)
abstract class MovieInfoDatabase: RoomDatabase() {

    abstract fun movieExtraInfoDao(): MovieExtraInfoDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MovieInfoDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MovieInfoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, MovieInfoDatabase::class.java, "movie_info_database").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}