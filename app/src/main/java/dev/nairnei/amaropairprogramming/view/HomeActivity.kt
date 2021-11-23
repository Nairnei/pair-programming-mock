package dev.nairnei.amaropairprogramming.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.nairnei.amaropairprogramming.databinding.ActivityMainBinding
import dev.nairnei.amaropairprogramming.viewModel.HomeViewModel
import dev.nairnei.amaropairprogramming.viewModelAdapter.HomeAdapter


class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeAdapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.setContext(this)
        homeViewModel.listShop()

        binding.recycleViewStores.apply {
            homeAdapter = HomeAdapter()
            this.layoutManager = LinearLayoutManager(
                this@HomeActivity, LinearLayoutManager.VERTICAL, false
            )
        }

        homeViewModel.liveDataStoreModel.observe(this, {
            if (it != null) {
                homeAdapter.storeModelList = it
                binding.recycleViewStores.adapter = homeAdapter
            }

        })

        homeViewModel.liveDataLoading.observe(this, {
            if (it)
                binding.progressLoading.visibility = View.VISIBLE
            else
                binding.progressLoading.visibility = View.GONE
        })

        homeViewModel.liveDataError.observe(this, {
            Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_LONG).show()
        })


    }
}