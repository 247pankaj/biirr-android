package com.example.biirr.repository

import com.example.biirr.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

     fun getAllBeers() = retrofitService.getBeersData()
     fun getBeerById(beerId: Int) = retrofitService.getBeerById(beerId)

}