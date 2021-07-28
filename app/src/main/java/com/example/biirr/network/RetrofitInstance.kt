package com.example.biirr.network

import com.example.biirr.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {




    companion object{

        var retrofit: Retrofit? = null
        fun getInstance() : Retrofit {

            if (retrofit == null) {
                 retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                //retrofitInstance = retrofit.create(RetrofitInstance::class.java)
            }
            return retrofit!!
        }


    }
}