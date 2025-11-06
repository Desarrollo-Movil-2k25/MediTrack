package data

import entity.Medication

interface IDataManagerMedication {
    fun add(medication: Medication)
    fun remove(id: Int)
    fun update(medication: Medication)
    fun getAll(): List<Medication>
    fun getById(id: Int): Medication?
    fun getByName(name: String): Medication?

}