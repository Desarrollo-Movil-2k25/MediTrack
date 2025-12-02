package entity

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalTime

data class DTOMedication(
    @SerializedName("id") val idDTO: Int,
    @SerializedName("name") val nameDTO: String,
    @SerializedName("dose") val doseDTO: String,
    @SerializedName("description") val descriptionDTO: String?,
    @SerializedName("frequency") val frequencyDTO: String,
    @SerializedName("time") val timeDTO: String,
    @SerializedName("startDate") val startDateDTO: String,
    @SerializedName("endDate") val endDateDTO: String?,
    @SerializedName("reminderActive") val reminderActiveDTO: Boolean?,
    @SerializedName("image") val imageDTO: String?,
    @SerializedName("taken") val takenDTO: Boolean?,
    @SerializedName("ownerUser") val ownerUserDTO: String)
