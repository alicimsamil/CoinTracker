package com.alicimsamil.cointracker.feature.favorites.presentation

import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.alicimsamil.cointracker.core.firebase.model.FavoriteModel
import com.alicimsamil.cointracker.core.ui.base.BaseFragment
import com.alicimsamil.cointracker.feature.favorites.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(FragmentFavoritesBinding::inflate) {
    override val viewModel by viewModels<FavoritesViewModel>()
    override var state = FavoritesUiState()

    private lateinit var adapter: FavoritesAdapter


    override fun initialize() {
        super.initialize()
        viewModel.onEvent(FavoritesEvents.GetFavorites)
        observeUiState()
        setAdapter()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    state = it
                    handleFailure()
                    handleLoading()
                    it.favorites?.let {
                        adapter.favorites = it.toCollection(ArrayList())
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        adapter = FavoritesAdapter{
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.alicimsamil.cointracker/detail_fragment/$it".toUri())
                .build()
            findNavController().navigate(request)
        }
        binding.rvFavorites.adapter = adapter
    }

}