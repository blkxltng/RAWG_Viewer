package com.blkxltng.rawgviewer.ui.main

import androidx.lifecycle.ViewModel
import com.blkxltng.rawgviewer.models.Game
import com.blkxltng.rawgviewer.utils.LiveEvent

class MainViewModel : ViewModel() {

    val gameClickedEvent = LiveEvent<Game>()
}
