package com.alicimsamil.cointracker.feature.detail.presentation

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alicimsamil.cointracker.core.common.extension.gone
import com.alicimsamil.cointracker.core.common.extension.loadUrl
import com.alicimsamil.cointracker.core.common.extension.setTextColor
import com.alicimsamil.cointracker.core.ui.base.BaseFragment
import com.alicimsamil.cointracker.core.ui.R
import com.alicimsamil.cointracker.feature.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailViewModel>(FragmentDetailBinding::inflate) {
    override val viewModel by viewModels<DetailViewModel>()
    override var state = DetailUiState()
    private val nav by navArgs<DetailFragmentArgs>()

    override fun initialize() {
        super.initialize()
        viewModel.onEvent(DetailEvents.GetCoinDetails(nav.id))
        initListeners()
        observeUiState()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    state = it
                    handleFailure()
                    handleLoading()
                    setUi()
                }
            }
        }
    }

    private fun setUi() {
        state.detailModel?.let { data ->
            binding.apply {
                tvCoinName.text = data.name
                tvCoinAbbr.text = data.symbol
                tvHashDesc.text = data.hashingAlgorithm
                tvCoinDesc.text = data.description
                tvCoinPercentage.text = data.percentage
                tvCoinPrice.text = data.price
                ivCoinIcon.loadUrl(data.icon, requireContext())
                if (data.description.isNullOrEmpty()) {
                    tvCoinDesc.gone()
                    tvCoinDescTitle.gone()
                }
                if ((data.percentageDouble ?: 0.0) > 0) {
                    tvCoinPercentage.setTextColor(requireContext(), R.color.green)
                } else {
                    tvCoinPercentage.setTextColor(requireContext(), R.color.red)
                }
                state.marketChartData?.let {
                    lcvDetail.setChartData(it)
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnOk.setOnClickListener {
            viewModel.onEvent(DetailEvents.RefreshCoin(binding.etTimeInterval.text.toString().toLongOrNull()))
            it.isEnabled = false
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}