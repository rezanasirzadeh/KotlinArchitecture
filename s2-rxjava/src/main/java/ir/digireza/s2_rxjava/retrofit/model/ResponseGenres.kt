package ir.digireza.s2_rxjava.retrofit.model

import com.google.gson.annotations.SerializedName

class ResponseGenres : ArrayList<ResponseGenres.ResponseGenresItem>(){
    data class ResponseGenresItem(
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("name")
        val name: String? // Crime
    )
}