package data

import entity.User

object MemoryDataManagerUser: IDataManagerUser {

    private var userList = mutableListOf<User>()

    override fun add(user: User) {
        userList.add(user)
    }
    override fun remove(id: String) {
        userList.removeIf {it.id.trim()==id.trim()}
    }
    override fun update(user: User) {
        remove(user.id)
        add(user)
    }

    override fun getAll() = userList

    override fun getById(id: String): User? {
        try {
            var result = userList.filter {it.id.trim()==id.trim()}
            return if (result.any()) result[0] else null

        }catch (e: Exception){
            throw e
        }
    }

    override fun getByFullName(fullName: String): User? {
        try {
            var result = userList.filter {it.toString().trim()==fullName.trim()}
            return if (result.any()) result[0] else null
        }catch (e: Exception){
            throw e
        }
    }

    override fun getByUserName(userName: String): User? {
        return try {
            userList.firstOrNull { it.nameUser.trim() == userName.trim() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun login(username: String, password: String): User? {
        val user = userList.find { it.nameUser.trim() == username.trim() }
        return when {
            user == null -> throw Exception("User not found") // No tiene cuenta
            user.password != password -> throw Exception("Incorrect password")  // Contraseña incorrecta
            else -> user  // Usuario y contraseña correctos
        }
    }



}