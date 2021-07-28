package com.example.biirr.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.biirr.DetailFragment
import com.example.biirr.DetailFragmentArgs
import com.example.biirr.model.BeerAPIResponse
import com.example.biirr.repository.MainRepository
import kotlinx.coroutines.currentCoroutineContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel constructor(private val repository: MainRepository)  : ViewModel() {

 //   val beer = MutableLiveData<BeerAPIResponse>()

    val beer: MutableLiveData<BeerAPIResponse> by lazy {
        MutableLiveData<BeerAPIResponse>()
    }
    private lateinit var taste: String



    val errorMessage = MutableLiveData<String>()

    fun getBeerById(beerId: Int) {

        val response = repository.getBeerById(beerId)
        response.enqueue(object : Callback<List<BeerAPIResponse>> {
            override fun onResponse(call: Call<List<BeerAPIResponse>>, response: Response<List<BeerAPIResponse>>) {
                beer.postValue(response.body()!!.get(0))
                updateUI(response.body()!!.get(0))
            }

            override fun onFailure(call: Call<List<BeerAPIResponse>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }

    private fun updateUI(apiResponse: BeerAPIResponse) {
        DetailFragment.binding.name.text = apiResponse.name
        DetailFragment.binding.tagline.text = apiResponse.tagline

        Glide.with(DetailFragment.binding.beerImage.context).load(apiResponse.imageURL).into(DetailFragment.binding.beerImage)
        DetailFragment.binding.alcoholPercent.text = "Alcohol Percent : " + apiResponse.abv.toString()
        var ibu = apiResponse.ibu
        if (ibu <= 20)
            taste =  "Taste: Smooth"
        else if (ibu<=50 && ibu>20)
            taste =  "Taste: Bitter"
        else if (ibu>50)
            taste =  "Taste: Hipster Plus"
        else
            taste =  "Taste unavailable"

        DetailFragment.binding.bitter.text = taste

    }


}