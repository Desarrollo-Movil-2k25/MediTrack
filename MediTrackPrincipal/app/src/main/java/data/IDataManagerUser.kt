package data

import entity.User

interface IDataManagerUser {
    fun add(user: User)
    fun update(user: User)
    fun remove(id: String)
    fun getAll(): List<User>
    fun getById(id: String): User?
    fun getByFullName(fullName: String): User?
}