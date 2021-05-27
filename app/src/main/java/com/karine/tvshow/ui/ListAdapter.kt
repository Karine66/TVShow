package com.karine.tvshow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.karine.tvshow.databinding.FragmentCellItemBinding
import com.karine.tvshow.models.ResultsItem

class ListAdapter (
    private val moviesResult: MutableList<ResultsItem>,
//    private val favoritesList : RealmResults<Movies>,
    private val glide: RequestManager,
    ) :
    RecyclerView.Adapter<ListViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ListViewHolder {
            val fragmentCellItemBinding =
                FragmentCellItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ListViewHolder(fragmentCellItemBinding)
        }

        override fun onBindViewHolder(
            holder: ListViewHolder,
            position: Int,
        ) {

            holder.updateWithPopular(this.moviesResult[position], glide)
//            this.favoritesList[position]?.let { holder.clickFavorites(it) }

        }

        override fun getItemCount(): Int {
            return moviesResult.size

        }


    }

