package com.blkxltng.rawgviewer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blkxltng.rawgviewer.models.Game
import timber.log.Timber

class GameViewModel(val mainViewModel: MainViewModel) : ViewModel() {
    
    val game = MutableLiveData<Game>(null)

    fun gameClicked() {
        Timber.d("You clicked Game: ${game.value?.name} and ID: ${game.value?.id}")
        mainViewModel.gameClickedEvent.postValue(game.value)
    }
}