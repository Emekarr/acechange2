package com.example.acechange20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.acechange20.databinding.ActivityMainBinding
import com.example.acechange20.repository.Repository
import com.example.acechange20.screens.fragments.AllRatesFragment
import com.example.acechange20.screens.fragments.ChartsFragment
import com.example.acechange20.screens.fragments.ConvertFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView:  BottomNavigationView

    private val allRatesFragment = get<AllRatesFragment>()
    private val chartsFragment = get<ChartsFragment>()
    private val convertFragment = get<ConvertFragment>()

    private val fragmentManager = supportFragmentManager

    private var active :Fragment = allRatesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //bottomNavView
        bottomNavView = binding.bottomNavView

        //set up toolbar
        setSupportActionBar(binding.toolBar)

        bottomNavigation()
        setUpFragments()
    }

    private fun setUpFragments(){
        fragmentManager.beginTransaction().add(R.id.fragment, chartsFragment, "chartsFragment").hide(chartsFragment).commit()
        fragmentManager.beginTransaction().add(R.id.fragment, convertFragment, "convertFragment").hide(convertFragment).commit()
        fragmentManager.beginTransaction().add(R.id.fragment, allRatesFragment, "allRatesFragment").commit()
    }

    private fun bottomNavigation(){
        bottomNavView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.allRatesFragment ->  {
                    fragmentManager.beginTransaction().hide(active).show(allRatesFragment).commit()
                    active = allRatesFragment
                }
                R.id.convertFragment ->  {
                    fragmentManager.beginTransaction().hide(active).show(convertFragment).commit()
                    active = convertFragment
                }
                R.id.chartsFragment ->  {
                    fragmentManager.beginTransaction().hide(active).show(chartsFragment).commit()
                    active = chartsFragment
                }
            }
            true
        }
    }
}