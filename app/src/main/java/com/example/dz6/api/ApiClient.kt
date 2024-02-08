package com.example.dz6.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {

    val api: ApiInterface
        get() = retrofit!!.create(
            ApiInterface::class.java)

    init {

        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    companion object {

        private val BASE_URL = "http://denis1re.beget.tech/"

        private var apiClient: ApiClient? = null
        private var retrofit: Retrofit? = null

        val instance: ApiClient?
            @Synchronized get() {

                if (apiClient == null) {

                    apiClient =
                        ApiClient()
                }

                return apiClient


            }

    }
}