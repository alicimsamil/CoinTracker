package com.alicimsamil.cointracker.feature.favorites.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.core.common.extension.loadUrl
import com.alicimsamil.cointracker.core.firebase.model.FavoriteModel
import com.alicimsamil.cointracker.feature.favorites.databinding.ItemFavoriteListingBinding

class FavoritesAdapter(val click: (String) -> Unit):
    RecyclerView.Adapter<FavoritesAdapter.FavoriteListingViewHolder>() {

    var favorites = ArrayList<FavoriteModel>()

    var context: Context? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteListingViewHolder {
        context = parent.context
        return FavoriteListingViewHolder(
            ItemFavoriteListingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: FavoriteListingViewHolder, position: Int) {
        val item = favorites[position]
        holder.bind(item)
    }

    inner class FavoriteListingViewHolder(private val binding: ItemFavoriteListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listingModel: FavoriteModel?) {
            binding.apply {
                listingModel?.let { data ->
                    context?.let { itContext ->
                        ivCoinIcon.loadUrl(data.coinImage, itContext)
                    }
                    tvCoinName.text = data.coinName
                    tvCoinPrice.text = data.coinPrice
                    tvCoinSymbol.text = data.coinSymbol
                    root.setOnClickListener {
                        click(data.coinId ?: String.EMPTY)
                    }
                }
            }
        }

    }
}