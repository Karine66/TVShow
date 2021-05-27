package com.karine.tvshow.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.karine.tvshow.R
import com.karine.tvshow.databinding.FragmentCellItemBinding

import com.karine.tvshow.models.ResultsItem
import com.karine.tvshow.utils.DatesConverter

class ListViewHolder(private val fragmentCellItemBinding: FragmentCellItemBinding) :
    RecyclerView.ViewHolder(fragmentCellItemBinding.root) {



    //for update with mostPopular Movies
    fun updateWithPopular(resultsItem: ResultsItem, glide: RequestManager) {
        //for update popular cell
        fragmentCellItemBinding.date.text =
            DatesConverter.dateConvertForMovies(resultsItem.firstAirDate)
        fragmentCellItemBinding.overview.text = resultsItem.overview
        fragmentCellItemBinding.name.text = resultsItem.name
        //for photo
        if (!resultsItem.posterPath.isNullOrEmpty()) {
            glide.load(resultsItem.posterPath).centerCrop().into(fragmentCellItemBinding.imageView)
        } else {
            fragmentCellItemBinding.imageView.setImageResource(R.drawable.no_image);
        }

    }

//    fun clickFavorites(movies: Movies) {
//        fragmentCellItemBinding.favorites.setOnClickListener() {
//
//            Log.d("Click Favorites", "favorites click")
//
//        }
//    }
}
