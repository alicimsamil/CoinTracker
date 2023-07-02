package com.alicimsamil.cointracker.feature.listing.presentation.screen

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
        viewModel.getPagingData()
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
                        pagingAdapter.submitData(data)
                    }
                }
            }
        }
    }

    private fun setRecyclerAdapter() {
        pagingAdapter = ListingAdapter(ListingAdapter.CoinComparator)
        binding.rvListing.adapter = pagingAdapter
    }
}