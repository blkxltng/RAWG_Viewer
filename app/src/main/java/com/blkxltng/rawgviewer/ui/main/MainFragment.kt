package com.blkxltng.rawgviewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.blkxltng.rawgviewer.R
import com.blkxltng.rawgviewer.databinding.MainFragmentBinding
import com.blkxltng.rawgviewer.items.GameListingItem
import com.blkxltng.rawgviewer.models.Game
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private var positionIndex: Int = -1
    private var topView: Int = -1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.mainViewModel = viewModel
        binding.executePendingBindings()
        setupObservers()
    }

    override fun onPause() {
        super.onPause()

        // Save the scroll position of the recyclerView onPause()
        positionIndex = layoutManager.findFirstVisibleItemPosition()
        val startView: View = recyclerView.getChildAt(0)
        topView = startView.top - recyclerView.paddingTop
    }

    override fun onResume() {
        super.onResume()

        // Restore the scroll position of the recyclerView onResume()
        if (positionIndex != -1) {
            layoutManager.scrollToPositionWithOffset(positionIndex, topView)
        }
    }

    private fun setupObservers() {

        viewModel.listGames.observe(viewLifecycleOwner, Observer {
            loadGames(it)
        })

        viewModel.gameClickedEvent.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "You clicked ${it.name}!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(it.id.toString()))
        })

    }

    fun loadGames(list: List<Game>) {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = groupAdapter

        list.forEach { groupAdapter.add(GameListingItem(GameViewModel(viewModel).apply { game.postValue(it) })) }
    }

}
