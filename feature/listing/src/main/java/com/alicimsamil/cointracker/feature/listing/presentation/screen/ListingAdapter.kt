package com.alicimsamil.cointracker.feature.listing.presentation.screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.cointracker.core.common.extension.loadUrl
import com.alicimsamil.cointracker.core.common.extension.setTextColor
import com.alicimsamil.cointracker.core.ui.R
import com.alicimsamil.cointracker.feature.listing.databinding.ItemListingBinding
import com.alicimsamil.cointracker.feature.listing.presentation.custom.LineColor

class ListingAdapter(diffCallback: CoinComparator) :
    PagingDataAdapter<ListingModel, ListingAdapter.ListingViewHolder>(diffCallback) {

    var context: Context? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListingViewHolder {
        context = parent.context
        return ListingViewHolder(
            ItemListingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object CoinComparator : DiffUtil.ItemCallback<ListingModel>() {
        override fun areItemsTheSame(oldItem: ListingModel, newItem: ListingModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListingModel, newItem: ListingModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class ListingViewHolder(private val binding: ItemListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listingModel: ListingModel?) {
            binding.apply {
                listingModel?.let { data ->
                    context?.let { itContext ->
                        ivCoinIcon.loadUrl(data.icon, itContext)
                    }
                    tvCoinName.text = data.name
                    tvCoinPrice.text = data.price
                    tvCoinSymbol.text = data.symbol
                    tvCoinPercentage.text = data.percentage
                    if ((data.percentageDouble ?: 0.0) > 0) {
                        lcvListing.setLineColor(LineColor.GREEN)
                        context?.let {
                            context?.let {
                                binding.tvCoinPercentage.setTextColor(it, R.color.green)
                            }
                        }
                    } else {
                        lcvListing.setLineColor(LineColor.RED)
                        context?.let {
                            binding.tvCoinPercentage.setTextColor(it, R.color.red)
                        }
                    }
                    data.sparkline?.let {
                        lcvListing.setChartData(it)
                    }
                }
            }
        }

    }
}