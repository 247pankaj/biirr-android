package com.example.biirr

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.biirr.databinding.DetailFragmentBinding
import com.example.biirr.model.BeerAPIResponse
import com.example.biirr.network.RetrofitInstance
import com.example.biirr.network.RetrofitService
import com.example.biirr.repository.MainRepository
import com.example.biirr.viewmodel.DetailViewModel
import com.example.biirr.viewmodel.MainViewModel
import com.example.biirr.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()
    companion object {lateinit var binding: DetailFragmentBinding }

    private val  service: RetrofitService? = RetrofitInstance.getInstance().create(RetrofitService::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



       // requireActivity().title = getString(R.string.app_name)
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(service!!))).get(
            DetailViewModel::class.java)


        binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.imageClose.setOnClickListener{ NavHostFragment.findNavController(this@DetailFragment).navigateUp() }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    NavHostFragment.findNavController(this@DetailFragment).navigateUp()                }
            }
        )

        viewModel.getBeerById(args.beerId)




        return binding.root
    }

}