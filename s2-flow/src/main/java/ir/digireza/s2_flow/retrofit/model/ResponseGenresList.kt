package ir.digireza.s2_flow.retrofit.model

import com.google.gson.annotations.SerializedName

class ResponseGenresList : ArrayList<ResponseGenresList.ResponseGenresListItem>(){
    data class ResponseGenresListItem(
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("name")
        val name: String? // Sport
    )
}