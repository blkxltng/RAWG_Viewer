package com.blkxltng.rawgviewer.network

import com.blkxltng.rawgviewer.models.GameDetails
import com.blkxltng.rawgviewer.models.RAWGDataResponse
import com.blkxltng.rawgviewer.utils.RAWG_BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestApi {

    private val rawgApi: RAWGAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(RAWG_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        rawgApi = retrofit.create(RAWGAPI::class.java)
    }

    fun getAntUpcomingGames(): Call<RAWGDataResponse> {
        return rawgApi.getUpcomingGames()
    }

    fun getGameDetails(gameID: String): Call<GameDetails> {
        return rawgApi.getGameDetails(gameID)
    }
}