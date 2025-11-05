package data

import entity.Medication

object MemoryDataManagerMedication: IDataManagerMedication {
    private var medicationList = mutableListOf<Medication>()

    override fun add(medication: Medication) {
        medicationList.add(medication)
    }

    override fun remove(id: Int) {
        medicationList.removeIf { it.id == id }
    }

    override fun update(medication: Medication) {
        remove(medication.id)
        add(medication)
    }

    override fun getAll(): List<Medication> = medicationList

    override fun getById(id: Int): Medication? {
        try {
            var result = medicationList.filter {it.id==id}
            return if (result.any()) result[0] else null

        }catch (e: Exception){
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
}