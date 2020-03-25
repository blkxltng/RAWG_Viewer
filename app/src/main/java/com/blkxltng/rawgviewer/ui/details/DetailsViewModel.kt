package com.blkxltng.rawgviewer.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blkxltng.rawgviewer.models.GameDetails

class DetailsViewModel : ViewModel() {

    val gameDetails = MutableLiveData<GameDetails>(null)
}