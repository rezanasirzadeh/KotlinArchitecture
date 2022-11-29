package ir.digireza.s1_project.models.home

import com.google.gson.annotations.SerializedName

class ResponseGenresList : ArrayList<ResponseGenresList.ResponseGenresListItem>(){
    data class ResponseGenresListItem(
        @SerializedName("id")
        val id: Int?, // 21
        @SerializedName("name")
        val name: String? // Sport
    )
}