package com.dia.androidlearndia.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dia.androidlearndia.R
import com.dia.androidlearndia.databinding.ActivityHomeBinding
import com.dia.androidlearndia.fragment.FavoriteFragment
import com.dia.androidlearndia.fragment.HomeFragment
import com.dia.androidlearndia.fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeMenu -> {
                    showFragment(binding.container.id, HomeFragment.newInstance("", ""))
                    true
                }
                R.id.profileMenu -> {
                    showFragment(binding.container.id, ProfileFragment.newInstance("", ""))
                    true
                }
                R.id.favoriteMenu -> {
                    showFragment(binding.container.id, FavoriteFragment.newInstance("", ""))
                    true
                }
                else -> {false}
            }
        }
    }

    private fun showFragment(id: Int, fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(id, fragment)
            .commitAllowingStateLoss()
    }
}