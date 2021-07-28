package com.example.biirr

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biirr.adapter.BeerListAdapter
import com.example.biirr.databinding.MainFragmentBinding
import com.example.biirr.model.BeerAPIResponse
import com.example.biirr.network.RetrofitInstance
import com.example.biirr.network.RetrofitService
import com.example.biirr.repository.MainRepository
import com.example.biirr.viewmodel.MainViewModel
import com.example.biirr.viewmodel.ViewModelFactory
import androidx.navigation.fragment.findNavController


class MainFragment : Fragment(),
    BeerListAdapter.ListItemListener {



    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    private val  service:RetrofitService? = RetrofitInstance.getInstance().create(RetrofitService::class.java)

    val adapter = BeerListAdapter(this@MainFragment)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        requireActivity().title = getString(R.string.app_name)

        binding = MainFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar!!.show()




        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(service!!))).get(MainViewModel::class.java)

        binding.recyclerView.adapter = adapter

        viewModel.beerList.observe(viewLifecycleOwner, Observer {
            Log.d("MainFragment", "onCreate: $it")
            adapter.setBeerList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {})
        viewModel.getAllData()

        return binding.root
    }

    override fun openBeer(beer: BeerAPIResponse) {
        Log.i("MainFragment", "onItemClick: received data id $beer")
        var action = MainFragmentDirections.actionOpenDetailFragment(beer.id.toInt())
        findNavController().navigate(action)
    }
}