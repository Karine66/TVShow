package com.karine.tvshow.ui.mostpopular

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.karine.tvshow.Listener
import com.karine.tvshow.databinding.FragmentMostPopularBinding
import com.karine.tvshow.models.Movies
import com.karine.tvshow.models.ResultsItem
import com.karine.tvshow.utils.GlideApp
import org.json.JSONException


class MostPopularFragment : Fragment(), Listener {
    private var _fragmentMostPopularBinding: FragmentMostPopularBinding? = null
    private val fragmentMostPopularBinding get() = _fragmentMostPopularBinding!!
    private lateinit var requestQueue: RequestQueue
    private var resultMovies = mutableListOf<ResultsItem>()
    private var favoritesList = mutableListOf<Movies>()
    private lateinit var adapter: com.karine.tvshow.ui.ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentMostPopularBinding = FragmentMostPopularBinding.inflate(inflater, container, false)
        val view = fragmentMostPopularBinding.root

        //for recyclerView
        fragmentMostPopularBinding.rvMostPopular.setHasFixedSize(true)
        fragmentMostPopularBinding.rvMostPopular.layoutManager = LinearLayoutManager(context)
        //for volley
        requestQueue = Volley.newRequestQueue(context)
        //for toolbar title
        activity?.title = "Most Popular"

        requestForPopular()
        //for SearchView
        setHasOptionsMenu(true);
        return view
    }
    // for search bar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(com.karine.tvshow.R.menu.menu_search, menu)
        val item = menu.findItem(com.karine.tvshow.R.id.actionSearch)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)

        val searchView: SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    resultMovies.clear()
                    requestForSearch(newText)
                }
                resultMovies.clear()
                requestForPopular()
                return true
            }
        })
    }

    //for request with Volley for most popular
    private fun requestForPopular() {

        val url: String =
            "https://api.themoviedb.org/3/tv/popular?api_key=${getString(com.karine.tvshow.R.string.api_key)}"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("results")

                    for (i in 0 until jsonArray.length()) {

                        val results = jsonArray.getJSONObject(i)

                        val date = results.getString("first_air_date")
                        val overview = results.getString("overview")
                        val name = results.getString("name")
                        val imageUrl = results.getString("poster_path")
                        val showImage = "https://image.tmdb.org/t/p/w500$imageUrl"
                        Log.d("showImage", showImage)
                        resultMovies.add(ResultsItem(date, overview, name, showImage))
                        adapter = com.karine.tvshow.ui.ListAdapter(
                            resultMovies,
                            favoritesList,
                            GlideApp.with(this),
                            this
                        )
                        fragmentMostPopularBinding.rvMostPopular.adapter = adapter
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        requestQueue.add(request)
    }

    //for request with Volley for search
    private fun requestForSearch(newText: String) {

        val url: String =
            "https://api.themoviedb.org/3/search/tv?api_key=${getString(com.karine.tvshow.R.string.api_key)}&query=$newText"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("results")

                    for (i in 0 until jsonArray.length()) {

                        val results = jsonArray.getJSONObject(i)

                        val date = results.getString("first_air_date")
                        val overview = results.getString("overview")
                        val name = results.getString("name")
                        val imageUrl = results.getString("poster_path")
                        val showImage = "https://image.tmdb.org/t/p/w500$imageUrl"
                        Log.d("showImage", showImage)
                        resultMovies.add(ResultsItem(date, overview, name, showImage))
                        val adapter = com.karine.tvshow.ui.ListAdapter(
                            resultMovies,
                            favoritesList,
                            GlideApp.with(this),
                            this
                        )
                        fragmentMostPopularBinding.rvMostPopular.adapter = adapter
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        requestQueue.add(request)
    }

    //for click on favorites
    override fun clickFavorites(position: Int) {

        Log.d("clickFavorites", "clickFavorites$position")
    }
}