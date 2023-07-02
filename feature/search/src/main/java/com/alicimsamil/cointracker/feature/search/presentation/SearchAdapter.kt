package com.alicimsamil.cointracker.feature.search.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.cointracker.feature.search.databinding.ItemSearchListingBinding

class SearchAdapter(diffCallback: CoinComparator) :
    PagingDataAdapter<SearchModel, SearchAdapter.ListingViewHolder>(diffCallback) {

    var context: Context? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListingViewHolder {
        context = parent.context
        return ListingViewHolder(
            ItemSearchListingBinding.inflate(
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

    object CoinComparator : DiffUtil.ItemCallback<SearchModel>() {
        override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem.coinId == newItem.coinId
        }

        override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class ListingViewHolder(private val binding: ItemSearchListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listingModel: SearchModel?) {
            binding.apply {
                listingModel?.let { data ->
                    tvCoinName.text = data.name
                    tvCoinSymbol.text = data.symbol
                }
            }
        }

    }
}