package com.example.vlcplayer.data.room_database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Created by Harshal Chaudhari on 29/9/19.
 */

@Entity(tableName = "movie_info_table")
@Parcelize
class MovieInfo(
    @PrimaryKey val movieId: Int,
    val posterPath: String,
    val backPath: String = "",
    var title: String = "",
    var likeOrNot: Int = 0,
    var totalVote: Int
) : Parcelable
