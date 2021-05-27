package com.karine.tvshow.ui.mainpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karine.tvshow.R
import com.karine.tvshow.databinding.ActivityMainBinding
import com.karine.tvshow.ui.favorites.FavoritesFragment
import com.karine.tvshow.ui.mostpopular.MostPopularFragment

class MainPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        configureToolbar()

        //for navigation view
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                MostPopularFragment()).commit()
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_popular -> selectedFragment = MostPopularFragment()
            R.id.nav_Favorites -> selectedFragment = FavoritesFragment()
        }
        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit()
        }
        true
    }

    /**
     * For toolbar
     */
    private fun configureToolbar() {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }


}