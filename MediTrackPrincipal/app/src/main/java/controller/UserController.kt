package controller

import android.content.Context
import android.util.Log
import com.example.meditrackprincipal.R
import com.google.gson.Gson
import com.google.gson.JsonElement
import entity.DTOLogin
import entity.DTOUser
import entity.User
import util.MediTrackApiService

class UserController(private val context: Context) {

    private val gson = Gson()

    private fun parseUserObject(json: JsonElement?): DTOUser? {
        return try {
            if (json != null && json.isJsonObject) {
                gson.fromJson(json, DTOUser::class.java)
            } else null
        } catch (e: Exception) {
            Log.e("API_User", "Error parsing object: ${e.message}")
            null
        }
    }
    private fun parseUserList(json: JsonElement?): List<DTOUser> {
        return try {
            if (json != null && json.isJsonArray) {
                json.asJsonArray.map { gson.fromJson(it, DTOUser::class.java) }
            } else emptyList()
        } catch (e: Exception) {
            Log.e("API_User", "Error parsing list: ${e.message}")
            emptyList()
        }
    }

    suspend fun addUser(user: User) {
        try {
            val response = MediTrackApiService.apiUser.postUser(convertToDTO(user))
            Log.d("API_DEBUG", "RESPONSE → code=${response.responseCode} | message=${response.message} | data=${response.data}")
            if (response.responseCode !in 200..201) throw Exception(response.message)

        } catch (e: Exception) {
            Log.e("API_User", "Error addUser: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    suspend fun updateUser(user: User) {
        try {
            val response = MediTrackApiService.apiUser.updateUser(convertToDTO(user))
            if (response.responseCode != 200) throw Exception(response.message)

        } catch (e: Exception) {
            Log.e("API_User", "Error updateUser: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    suspend fun getAllUsers(): List<User> {
        try {
            val response = MediTrackApiService.apiUser.getAll()

            if (response.responseCode != 200) throw Exception(response.message)

            val dtoList = parseUserList(response.data)
            return dtoList.map { convertToEntity(it) }

        } catch (e: Exception) {
            Log.e("API_User", "Error getAllUsers: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    suspend fun getUserById(id: String): User? {
        try {
            val response = MediTrackApiService.apiUser.getById(id)

            if (response.responseCode != 200) return null

            // Puede retornar objeto o array
            val userObj = parseUserObject(response.data)
            if (userObj != null) return convertToEntity(userObj)

            val userList = parseUserList(response.data)
            return userList.firstOrNull()?.let { convertToEntity(it) }

        } catch (e: Exception) {
            Log.e("API_User", "Error getUserById: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }

    suspend fun getUserByUsername(username: String): User? {
        try {
            val response = MediTrackApiService.apiUser.getByUsername(username)

            if (response.responseCode != 200) return null

            // Puede venir objeto
            val userObj = parseUserObject(response.data)
            if (userObj != null) return convertToEntity(userObj)

            // O lista
            val list = parseUserList(response.data)
            return list.firstOrNull()?.let { convertToEntity(it) }

        } catch (e: Exception) {
            Log.e("API_User", "Error getUserByUsername: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }

    // ============================
    // LOGIN
    // ============================

    suspend fun login(username: String, password: String): User {
        try {
            val dto = DTOLogin(username, password)

            val response = MediTrackApiService.apiUser.login(dto)

            when (response.responseCode) {

                200 -> {
                    val dtoUser = response.data
                        ?: throw Exception("No data returned from login")
                    return convertToEntity(dtoUser)
                }

                401 -> {
                    when (response.message) {
                        "Incorrect password" ->
                            throw Exception(context.getString(R.string.MsgIncorrectPassword))

                        "User not found" ->
                            throw Exception(context.getString(R.string.MsgIsNorRegister))

                        else ->
                            throw Exception(response.message)
                    }
                }

                else -> throw Exception(response.message)
            }

        } catch (e: Exception) {
            Log.e("API_User", "Login error: ${e.message}")
            throw e
        }
    }

    suspend fun removeUser(id: String) {
        try {
            val response = MediTrackApiService.apiUser.deleteUser(id)
            if (response.responseCode != 200)
                throw Exception(response.message)

        } catch (e: Exception) {
            Log.e("API_User", "Error removeUser: ${e.message}")
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }

    // ============================
    // MAPPERS
    // ============================

    private fun convertToEntity(dto: DTOUser): User {
        val user = User()
        user.id = dto.IdDTO
        user.name = dto.NameDTO
        user.fLastName = dto.FLastNameDTO
        user.sLastName = dto.SLastNameDTO
        user.email = dto.EmailDTO
        user.nameUser = dto.NameUserDTO
        user.password = dto.PasswodDTO
        return user
    }

    private fun convertToDTO(user: User): DTOUser {
        return DTOUser(
            IdDTO = user.id,
            NameDTO = user.name,
            FLastNameDTO = user.fLastName,
            SLastNameDTO = user.sLastName,
            EmailDTO = user.email,
            NameUserDTO = user.nameUser,
            PasswodDTO = user.password
        )
    }
}
