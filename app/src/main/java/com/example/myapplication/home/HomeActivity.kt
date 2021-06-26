package com.example.myapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initComponents()
    }

    private fun initComponents() {
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        binding.apply{
            viewModel = homeViewModel
            lifecycleOwner = this@HomeActivity
            val adapter = HomeAdapter()
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
            subscribeUi(adapter)
        }
    }

    private fun subscribeUi(adapter: HomeAdapter) {
        homeViewModel.getImages().observe(this) { img ->
            adapter.submitList(img)
        }
    }
}