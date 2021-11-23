package dev.nairnei.amaropairprogramming.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AmaroService {
    private val _amaroApi: AmaroApi
    val service: AmaroApi get() = _amaroApi


    companion object {
        var baseUrl: String = "https://amaro-mobile.s3.amazonaws.com"
        private var INSTANCE: AmaroService? = null

        /**
         * Method that returns the instance
         * @return
         */
        fun getInstance(): AmaroService? {
            if (INSTANCE == null) {
                INSTANCE = AmaroService()
            }
            return INSTANCE
        }
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        _amaroApi = retrofit.create(AmaroApi::class.java)
    }


}