package com.karine.tvshow.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.karine.tvshow.Listener
import com.karine.tvshow.R
import com.karine.tvshow.databinding.FragmentCellItemBinding
import com.karine.tvshow.models.ResultsItem
import com.karine.tvshow.utils.DatesConverter
import java.lang.ref.WeakReference


class ListViewHolder(private val fragmentCellItemBinding: FragmentCellItemBinding) :
    RecyclerView.ViewHolder(fragmentCellItemBinding.root), View.OnClickListener {

    private lateinit var callBackWeakRef: WeakReference<Listener>

    //for update with mostPopular Movies
    fun updateWithPopular(resultsItem: ResultsItem, glide: RequestManager, callBack: Listener) {
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
        //for listener on click favorites
        fragmentCellItemBinding.favorites.setOnClickListener(this)
        callBackWeakRef = WeakReference<Listener>(callBack)
    }

    //for callback on favorite
    override fun onClick(view: View) {
        var callBack: Listener? = callBackWeakRef.get()
        if (callBack != null) {
            callBack.clickFavorites(adapterPosition)
        }
    }
}
