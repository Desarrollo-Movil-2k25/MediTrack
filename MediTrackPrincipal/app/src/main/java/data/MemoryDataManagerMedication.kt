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
        return medicationList.find { it.id == id }
    }

    override fun getByName(name: String): Medication? {
        return medicationList.find { it.name.trim().equals(name.trim(), ignoreCase = true) }
    }
}