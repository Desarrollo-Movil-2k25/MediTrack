package entity

import com.google.gson.annotations.SerializedName

data class DTOUser(@SerializedName("id") val IdDTO: String,
                   @SerializedName("name") val NameDTO: String="",
                   @SerializedName("fLastName") val FLastNameDTO: String="",
                   @SerializedName("sLastName") val SLastNameDTO: String="",
                   @SerializedName("email") val EmailDTO: String="",
                   @SerializedName("nameUser") val NameUserDTO: String="",
                   @SerializedName("password") val PasswodDTO: String="")
