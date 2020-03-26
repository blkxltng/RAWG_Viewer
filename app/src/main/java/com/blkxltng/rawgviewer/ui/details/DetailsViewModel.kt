package com.blkxltng.rawgviewer.ui.details

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blkxltng.rawgviewer.models.GameDetails
import com.blkxltng.rawgviewer.network.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class DetailsViewModel : ViewModel() {

    val gameDetails = MutableLiveData<GameDetails>(null)
    val progressVisibility = MutableLiveData(View.VISIBLE)

    fun loadGameDetails(gameID: String) {
        val restApi = RestApi()
        val gamesResponse = restApi.getGameDetails(gameID)

        gamesResponse.enqueue(object: Callback<GameDetails> {
            override fun onResponse(call: Call<GameDetails>, response: Response<GameDetails>) {
                if (response.isSuccessful) {
                    progressVisibility.postValue(View.GONE)
                    gameDetails.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GameDetails>, t: Throwable) {
                progressVisibility.postValue(View.GONE)
                Timber.d(t, "error")
            }
        })
    }
}