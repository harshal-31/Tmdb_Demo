package com.example.media.myapplication.data.room_database

import com.example.vlcplayer.data.room_database.MovieInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Harshal Chaudhari on 6/3/20.
 */

 interface RoomHelper  {

    suspend fun insertMoviewInfo(videoExtraInfo: MovieInfo)

    fun getVideoInfo(videoName: String): Flow<MovieInfo>

    fun getHistoryVideoInfo(): Flow<List<MovieInfo>>

    suspend fun deleteVideoInfo(videoExtraInfo: MovieInfo)

 }