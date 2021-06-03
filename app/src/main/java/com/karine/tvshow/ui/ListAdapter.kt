package com.karine.tvshow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.karine.tvshow.Listener
import com.karine.tvshow.databinding.FragmentCellItemBinding
import com.karine.tvshow.models.Movies
import com.karine.tvshow.models.ResultsItem

class ListAdapter(
    private val moviesResult: MutableList<ResultsItem>,
    private val favoritesList: MutableList<Movies>,
    private val glide: RequestManager, private val callBack: Listener
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

        holder.updateWithPopular(this.moviesResult[position], glide, this.callBack)

    }

    override fun getItemCount(): Int {
        return moviesResult.size

    }


}

