package controller

import android.content.Context
import android.util.Log
import com.example.meditrackprincipal.R
import com.google.gson.Gson
import com.google.gson.JsonElement
import entity.DTOMedication
import entity.Medication
import util.MediTrackApiService

class MedicationController(private val context: Context) {

    private val gson = Gson()


    private fun parseMedicationObject(json: JsonElement?): DTOMedication? {
        return try {
            if (json != null && json.isJsonObject)
                gson.fromJson(json, DTOMedication::class.java)
            else null
        } catch (e: Exception) {
            Log.e("API_MED", "Error parsing object: ${e.message}")
            null
        }
    }

    private fun parseMedicationList(json: JsonElement?): List<DTOMedication> {
        return try {
            if (json != null && json.isJsonArray)
                json.asJsonArray.map { gson.fromJson(it, DTOMedication::class.java) }
            else emptyList()
        } catch (e: Exception) {
            Log.e("API_MED", "Error parsing list: ${e.message}")
            emptyList()
        }
    }

    suspend fun addMedication(med: Medication) {
        try {
            val dto = convertToDTO(med)
            val response = MediTrackApiService.apiMedication.postMedication(dto)

            Log.d("API_MED", "POST → code=${response.responseCode}, msg=${response.message}")

            if (response.responseCode !in listOf(200, 201))
                throw Exception(response.message)

        } catch (e: Exception) {
            Log.e("API_MED", "Error addMedication: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    suspend fun updateMedication(med: Medication) {
        try {
            val dto = convertToDTO(med)
            val response = MediTrackApiService.apiMedication.updateMedication(dto)

            if (response.responseCode !in listOf(200, 201))
                throw Exception(response.message)

        } catch (e: Exception) {
            Log.e("API_MED", "Error updateMedication: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    suspend fun removeMedication(id: Int, username: String) {
        try {
            val response = MediTrackApiService.apiMedication.deleteMedication(id, username)

            if (response.responseCode !in listOf(200, 201))
                throw Exception(response.message)

        } catch (e: Exception) {
            Log.e("API_MED", "Error removeMedication: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }


    suspend fun getAllMedications(): List<Medication> {
        try {
            val response = MediTrackApiService.apiMedication.getAll()

            if (response.responseCode !in listOf(200, 201))
                throw Exception(response.message)

            val dtoList = parseMedicationList(response.data)
            return dtoList.map { convertToEntity(it) }

        } catch (e: Exception) {
            Log.e("API_MED", "Error getAllMedications: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }


    suspend fun getMedicationsByUser(username: String): List<Medication> {
        try {
            val response = MediTrackApiService.apiMedication.getByUser(username)

            if (response.responseCode !in listOf(200, 201))
                throw Exception(response.message)

            val dtoList = parseMedicationList(response.data)
            return dtoList.map { convertToEntity(it) }

        } catch (e: Exception) {
            Log.e("API_MED", "Error getMedicationsByUser: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    suspend fun getMedicationById(id: Int, username: String): Medication? {
        try {
            val response = MediTrackApiService.apiMedication.getByIdAndUser(id, username)

            if (response.responseCode !in listOf(200, 201))
                return null

            val obj = parseMedicationObject(response.data)
            if (obj != null) return convertToEntity(obj)

            val list = parseMedicationList(response.data)
            return list.firstOrNull()?.let { convertToEntity(it) }

        } catch (e: Exception) {
            Log.e("API_MED", "Error getMedicationById: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }

    private fun convertToEntity(dto: DTOMedication): Medication {
        val med = Medication()
        med.id = dto.idDTO
        med.name = dto.nameDTO
        med.dose = dto.doseDTO
        med.description = dto.descriptionDTO
        med.frequency = dto.frequencyDTO
        med.time = dto.timeDTO
        med.startDate = dto.startDateDTO
        med.endDate = dto.endDateDTO
        med.reminderActive = dto.reminderActiveDTO
        med.image = null
        med.taken = dto.takenDTO
        med.ownerUser = dto.ownerUserDTO
        return med
    }
    private fun convertToDTO(med: Medication): DTOMedication {
        return DTOMedication(
            idDTO = med.id,
            nameDTO = med.name,
            doseDTO = med.dose,
            descriptionDTO = med.description,
            frequencyDTO = med.frequency,

            timeDTO = med.time.toString(),
            startDateDTO = med.startDate.toString(),
            endDateDTO = med.endDate?.toString(),

            reminderActiveDTO = med.reminderActive,
            imageDTO = null,
            takenDTO = med.taken,
            ownerUserDTO = med.ownerUser
        )
    }


}
