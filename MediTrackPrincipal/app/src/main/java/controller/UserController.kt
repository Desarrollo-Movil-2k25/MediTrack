package controller

import android.content.Context
import com.example.meditrackprincipal.R
import data.IDataManagerUser
import data.MemoryDataManagerUser
import entity.User
import kotlin.collections.remove

class UserController {
    private var dataManager: IDataManagerUser = MemoryDataManagerUser
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    // Resgistrar Usuario
    fun addUser(user: User) {
        try {
            dataManager.add(user)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    // Cambiar datos del Usuario
    fun updateUser(user: User) {
        try {
            dataManager.update(user)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    //Eliminar Usurio
    fun removePerson(id: String){
        try {
            val result = dataManager.getById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.MsgDataNotFound))
            }
            dataManager.remove(id)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }

    // ******
    fun getAllUsers(): List<User> {
        try {
            return dataManager.getAll()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    // Busacar Usuario por ID
    fun getUserById(id: String): User ?{
        try {
            return dataManager.getById(id)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }

    // ******
    fun getUserByFullName(fullName: String): User ? {
        try {
            return dataManager.getByFullName(fullName)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }
    fun getUserByUserName(username: String): User ?{
        try {
            return dataManager.getByUserName(username)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }
    fun login(username: String, password: String): User {
        try {
            return dataManager.login(username, password)!!
        } catch (e: Exception) {
            when (e.message) {
                "User not found" -> throw Exception(context.getString(R.string.MsgIsNorRegister))
                "Incorrect password" -> throw Exception(context.getString(R.string.MsgIncorrectPassword))
                else -> throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
            }
        }
    }

}