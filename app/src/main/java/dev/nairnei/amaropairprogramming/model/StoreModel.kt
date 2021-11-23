package dev.nairnei.amaropairprogramming.model

import com.google.gson.annotations.SerializedName

class StoreModel : ArrayList<StoreModel.StoreItem>() {


    data class StoreItem(
        @SerializedName("address")
        var address: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("location")
        var location: Location,
        @SerializedName("name")
        var name: String
    ) {
        data class Location(
            @SerializedName("latitude")
            var latitude: Int,
            @SerializedName("longitude")
            var longitude: Int
        )
    }
}