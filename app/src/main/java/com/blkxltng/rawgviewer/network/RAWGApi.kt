package com.blkxltng.rawgviewer.network

import com.blkxltng.rawgviewer.models.RAWGDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface  RAWGAPI {
    @GET("games?dates=2019-10-10,2020-10-10&ordering=-added")
    fun getUpcomingGames(): Call<RAWGDataResponse>
}