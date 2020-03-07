package com.example.vlcplayer.data.room_database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Harshal Chaudhari on 29/9/19.
 */

@Dao
interface MovieExtraInfoDao {

    @Query("SELECT*  FROM movie_info_table WHERE posterPath = :posterPath")
    fun getVideoInfo(posterPath: String): Flow<MovieInfo>

    @Query("SELECT * FROM movie_info_table ORDER BY title ASC")
    fun getAllVideoInfo(): Flow<List<MovieInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoInfo(videoExtraInfo: MovieInfo)

    @Delete
    suspend fun deleteVideoInfo(videoExtraInfo: MovieInfo)

}