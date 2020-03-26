package com.blkxltng.rawgviewer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blkxltng.rawgviewer.models.Game

class GameViewModel(private val mainViewModel: MainViewModel) : ViewModel() {

    val game = MutableLiveData<Game>(null)

    fun gameClicked() {
        mainViewModel.gameClickedEvent.postValue(game.value)
    }
}