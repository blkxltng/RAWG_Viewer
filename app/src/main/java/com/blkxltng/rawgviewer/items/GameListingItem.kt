package com.blkxltng.rawgviewer.items

import com.blkxltng.rawgviewer.R
import com.blkxltng.rawgviewer.databinding.ItemGameListingBinding
import com.blkxltng.rawgviewer.models.Game
import com.blkxltng.rawgviewer.ui.main.GameViewModel
import com.xwray.groupie.databinding.BindableItem

class GameListingItem(val gameViewModel: GameViewModel) : BindableItem<ItemGameListingBinding>() {

    override fun getLayout(): Int = R.layout.item_game_listing

    override fun bind(viewBinding: ItemGameListingBinding, position: Int) {
        viewBinding.gameViewModel = gameViewModel
    }
}