package com.blkxltng.rawgviewer.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.blkxltng.rawgviewer.R
import com.blkxltng.rawgviewer.databinding.FragmentGameDetailsBinding
import com.blkxltng.rawgviewer.models.GameDetails
import com.blkxltng.rawgviewer.network.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentGameDetailsBinding
    val viewModel: DetailsViewModel by viewModels()
    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_details, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.detailsViewModel = viewModel
        binding.executePendingBindings()

        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        getGame()
    }

    fun setupObservers() {

    }

    fun getGame() {
        val restApi = RestApi()
        val gamesResponse = restApi.getGameDetails(args.gameID)

        gamesResponse.enqueue(object: Callback<GameDetails> {
            override fun onResponse(call: Call<GameDetails>, response: Response<GameDetails>) {
                if (response.isSuccessful) {
                    viewModel.gameDetails.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GameDetails>, t: Throwable) {
                Timber.d(t, "error")
            }
        })




    }

}