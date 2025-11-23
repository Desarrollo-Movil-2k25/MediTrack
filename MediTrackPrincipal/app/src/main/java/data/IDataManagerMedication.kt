package data

import entity.Medication

interface IDataManagerMedication {
    fun add(medication: Medication)
    fun remove(id: Int, username: String)
    fun update(medication: Medication)
    fun getAll(): List<Medication>
    fun getByIdAndUser(id: Int,username: String): Medication?
    fun getByName(name: String): Medication?

    fun getAllByUser(username: String): List<Medication>

}