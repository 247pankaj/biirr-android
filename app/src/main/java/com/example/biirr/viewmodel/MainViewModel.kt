package com.example.biirr.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biirr.model.BeerAPIResponse
import com.example.biirr.network.RetrofitInstance
import com.example.biirr.network.RetrofitService
import com.example.biirr.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {


    val beerList = MutableLiveData<List<BeerAPIResponse>>()
    val errorMessage = MutableLiveData<String>()

     fun getAllData() {

        val response = repository.getAllBeers()
        response.enqueue(object : Callback<List<BeerAPIResponse>> {
            override fun onResponse(call: Call<List<BeerAPIResponse>>, response: Response<List<BeerAPIResponse>>) {
                beerList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<BeerAPIResponse>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }


}