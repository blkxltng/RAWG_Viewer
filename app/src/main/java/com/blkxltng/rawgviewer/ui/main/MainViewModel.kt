package com.blkxltng.rawgviewer.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blkxltng.rawgviewer.models.Game
import com.blkxltng.rawgviewer.models.RAWGDataResponse
import com.blkxltng.rawgviewer.network.RestApi
import com.blkxltng.rawgviewer.utils.LiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainViewModel : ViewModel() {

    val gameClickedEvent = LiveEvent<Game>()
    val listGames = MutableLiveData<List<Game>>()
    val progressVisibility = MutableLiveData(View.VISIBLE)

    init {
        loadUpcomingGames()
    }

    private fun loadUpcomingGames() {
        val restApi = RestApi()
        val gamesResponse = restApi.getAntUpcomingGames()

        gamesResponse.enqueue(object: Callback<RAWGDataResponse> {
            override fun onResponse(call: Call<RAWGDataResponse>, response: Response<RAWGDataResponse>) {
                if (response.isSuccessful) {
                    progressVisibility.postValue(View.GONE)
                    listGames.postValue(response.body()?.results)
                }
            }

            override fun onFailure(call: Call<RAWGDataResponse>, t: Throwable) {
                progressVisibility.postValue(View.GONE)
                Timber.d(t, "error retrieving games")
            }
        })
    }
}
