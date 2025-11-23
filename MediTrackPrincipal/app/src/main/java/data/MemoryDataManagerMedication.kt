package data

import entity.Medication

object MemoryDataManagerMedication: IDataManagerMedication {
    private var medicationList = mutableListOf<Medication>()

    override fun add(medication: Medication) {
        medicationList.add(medication)
    }

    override fun remove(id: Int, username: String) {
        medicationList.removeIf { it.id == id && it.ownerUser == username }
    }

    override fun update(medication: Medication) {
        remove(medication.id, medication.ownerUser)
        add(medication)
    }

    override fun getAll(): List<Medication> = medicationList

    override fun getByIdAndUser(id: Int, username: String): Medication? {
        return try {
            medicationList.firstOrNull {
                it.id == id && it.ownerUser == username
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByName(name: String): Medication? {
        try {
            var result = medicationList.filter {it.name.trim()==name.trim()}
            return if (result.any()) result[0] else null
        }catch (e: Exception){
            throw e
        }
    }

    override fun getAllByUser(username: String): List<Medication> {
        return medicationList.filter { it.ownerUser == username }
    }
}