package controller

import android.content.Context
import com.example.meditrackprincipal.R
import data.IDataManagerUser
import data.MemoryDataManagerUser
import entity.User

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

    // ******
    fun getAllUsers(): List<User> {
        try {
            return dataManager.getAll()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    // Busacar Usuario por ID
    fun getUserById(id: String): User {
        try {
            val result = dataManager.getById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
            }
            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }

    // ******
    fun getUserByFullName(fullName: String): User {
        try {
            val result = dataManager.getByFullName(fullName)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
            }
            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }
}