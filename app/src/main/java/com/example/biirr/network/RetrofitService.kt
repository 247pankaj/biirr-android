package com.example.biirr.network

import com.example.biirr.model.BeerAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("beers")
     fun getBeersData() : Call<List<BeerAPIResponse>>

    @GET("beers/{id}")
    fun getBeerById(@Path("id") id :Int) : Call<List<BeerAPIResponse>>

}