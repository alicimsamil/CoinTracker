package com.alicimsamil.cointracker.feature.listing.presentation.screen

import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.alicimsamil.cointracker.core.common.extension.invisible
import com.alicimsamil.cointracker.core.common.extension.visible
import com.alicimsamil.cointracker.core.ui.base.BaseFragment
import com.alicimsamil.cointracker.feature.listing.databinding.FragmentListingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListingFragment : BaseFragment<FragmentListingBinding, ListingViewModel>(FragmentListingBinding::inflate) {
    override val viewModel by viewModels<ListingViewModel>()
    override var state = ListingUiState()

    private lateinit var pagingAdapter: ListingAdapter

    override fun initialize() {
        super.initialize()
        viewModel.onEvent(ListingEvents.GetPagingData)
        setRecyclerAdapter()
        observeUiState()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    state = it
                    handleFailure()
                    handleLoading()
                    state.pagingData?.collectLatest { data ->
                        binding.emptyLayout.root.invisible()
                        pagingAdapter.submitData(data)
                    } ?: run {
                        binding.emptyLayout.root.visible()
                    }
                }
            }
        }
    }

    private fun setRecyclerAdapter() {
        pagingAdapter = ListingAdapter(ListingAdapter.CoinComparator){
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.alicimsamil.cointracker/detail_fragment/$it".toUri())
                .build()
            findNavController().navigate(request)
        }
        binding.rvListing.adapter = pagingAdapter
    }
}