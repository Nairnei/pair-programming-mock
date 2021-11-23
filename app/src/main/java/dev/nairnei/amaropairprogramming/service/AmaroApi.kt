package dev.nairnei.amaropairprogramming.service

import dev.nairnei.amaropairprogramming.model.StoreModel
import retrofit2.Call
import retrofit2.http.GET

interface AmaroApi {
    @GET("/pair-programing-mock/guideShops.json")
    fun listShops(): Call<StoreModel>

    @GET("/pair-programingx/guideShops.json")
    fun listShopsError(): Call<StoreModel>

}