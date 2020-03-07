package com.example.vlcplayer.data.room_database

import android.app.Application
import com.example.media.myapplication.data.room_database.RoomHelper


/**
 * Created by Harshal Chaudhari on 29/9/19.
 */
class RoomRepository(application: Application): RoomHelper {

    var movieExtraInfoDao: MovieExtraInfoDao

    init {
        movieExtraInfoDao = MovieInfoDatabase.getDatabase(application.applicationContext).movieExtraInfoDao()
    }

    override suspend fun insertMoviewInfo(videoExtraInfo: MovieInfo) {
        movieExtraInfoDao.insertVideoInfo(videoExtraInfo)
    }

    override suspend fun deleteVideoInfo(videoExtraInfo: MovieInfo) {
        movieExtraInfoDao.deleteVideoInfo(videoExtraInfo)
    }


    override fun getVideoInfo(videoName: String) = movieExtraInfoDao.getVideoInfo(videoName)

    override fun getHistoryVideoInfo() = movieExtraInfoDao.getAllVideoInfo()

}