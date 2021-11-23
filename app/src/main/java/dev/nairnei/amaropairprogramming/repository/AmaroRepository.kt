package dev.nairnei.amaropairprogramming.repository

import com.google.gson.Gson
import dev.nairnei.amaropairprogramming.model.StoreModel
import dev.nairnei.amaropairprogramming.service.AmaroService
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls

class AmaroRepository {

    fun getStore(): Call<StoreModel> {
        return AmaroService().service.listShops()
    }

    fun getFromCache(): Call<StoreModel> {
        return createAMockResponseBasedOnDB()
    }
}

/// A function to create a fake call to simulate a cached store
private fun createAMockResponseBasedOnDB(): Call<StoreModel> {


    val cachedResponse = """[
                {
                    "id": 1,
                    "name": "GS Oscar Freire",
                    "location": {
                    "longitude": 1231,
                    "latitude": 1231
                },
                    "address": "Rua Oscar Freire, SP"
                },
                {
                    "id": 3,
                    "name": "GS Ipanema",
                    "location": {
                    "longitude": 1231,
                    "latitude": 1231
                },
                    "address": "Ipanema, RJ"
                },
                {
                    "id": 4,
                    "name": "GS Iguatemi Campinas",
                    "location": {
                    "longitude": 1231,
                    "latitude": 1231
                },
                    "address": "Campinas, SP"
                }
        ]"""

    val gson = Gson()
    val fakeCache: StoreModel = gson.fromJson(cachedResponse, StoreModel::class.java)
    return Calls.response(Response.success(205, fakeCache))


}
