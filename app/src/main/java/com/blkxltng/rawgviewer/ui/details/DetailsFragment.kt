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

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentGameDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

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

    private fun setupObservers() {
        viewModel.loadGameDetails(args.gameID)
    }
}