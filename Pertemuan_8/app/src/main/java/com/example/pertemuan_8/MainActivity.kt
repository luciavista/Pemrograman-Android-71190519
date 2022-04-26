package com.example.pertemuan_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan_8.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var  viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        with(binding){
            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager){tab, position ->
                when(position){
                    0 -> tab.text = "Home"
                    1 -> tab.text = "Status"
                    2 -> tab.text = "panggilan"
                }
            }.attach()
        }
    }
}