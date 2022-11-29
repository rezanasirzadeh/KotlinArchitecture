package ir.digireza.s1_retrofit.model

import com.google.gson.annotations.SerializedName

data class BodyUserRegister(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("name")
    val name: String?
)
