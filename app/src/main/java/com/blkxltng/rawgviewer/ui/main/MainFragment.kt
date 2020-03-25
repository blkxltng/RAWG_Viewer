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
import com.blkxltng.rawgviewer.models.RAWGDataResponse
import com.blkxltng.rawgviewer.network.RestApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainFragment : Fragment() {

    lateinit var layoutManager: LinearLayoutManager

    private lateinit var binding: MainFragmentBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        binding.mainViewModel = viewModel
        binding.executePendingBindings()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        getGames()
    }

    fun setupObservers() {

        viewModel.gameClickedEvent.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "You clicked ${it.name}!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(it.id.toString()))
        })

    }

    fun getGames() {
        val restApi = RestApi()
        val gamesResponse = restApi.getAntUpcomingGames()
        var list: List<Game>?

        val groupAdapter = GroupAdapter<GroupieViewHolder>()

        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = groupAdapter

        gamesResponse.enqueue(object: Callback<RAWGDataResponse> {
            override fun onResponse(call: Call<RAWGDataResponse>, response: Response<RAWGDataResponse>) {
                if (response.isSuccessful) {
                    list = response.body()!!.results

                    if(list != null) {
                        list?.forEach { groupAdapter.add(GameListingItem(GameViewModel(viewModel).apply { game.postValue(it) })) }
                    }
                }
            }

            override fun onFailure(call: Call<RAWGDataResponse>, t: Throwable) {
                Timber.d(t, "error")
            }
        })




    }

}
