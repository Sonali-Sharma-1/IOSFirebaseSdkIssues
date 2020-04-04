package com.example.firebasesdkproject.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://api.github.com/repos/firebase/firebase-ios-sdk/"

    val service: IssueListDataService
    get() {
        if (retrofit == null) {
           retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(IssueListDataService::class.java)
    }
}