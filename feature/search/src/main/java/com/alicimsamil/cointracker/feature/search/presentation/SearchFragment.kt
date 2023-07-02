package com.alicimsamil.cointracker.feature.search.presentation

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alicimsamil.cointracker.core.common.extension.visible
import com.alicimsamil.cointracker.core.common.extension.invisible
import com.alicimsamil.cointracker.core.ui.base.BaseFragment
import com.alicimsamil.cointracker.feature.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {
    override val viewModel by viewModels<SearchViewModel>()
    override var state = SearchUiState()
    private lateinit var pagingAdapter: SearchAdapter


    override fun initialize() {
        super.initialize()
        observeUiState()
        setRecyclerAdapter()
        initListeners()

    }

    private fun initListeners(){
        binding.svCoin.doAfterTextChanged {
            val text = it.toString()
            if (text.isNotEmpty()){
                viewModel.onEvent(SearchEvents.SearchCoins(text))
            } else {
                viewModel.onEvent(SearchEvents.ClearData)
            }
        }

        binding.svCoin.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.emptyLayout.root.invisible()

            } else {
                binding.emptyLayout.root.visible()
            }
        }
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
        pagingAdapter = SearchAdapter(SearchAdapter.CoinComparator)
        binding.rvSearch.adapter = pagingAdapter
    }

}