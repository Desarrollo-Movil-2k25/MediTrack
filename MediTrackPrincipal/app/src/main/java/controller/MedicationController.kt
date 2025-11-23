package controller

import android.content.Context
import com.example.meditrackprincipal.R
import data.IDataManagerMedication
import data.MemoryDataManagerMedication
import entity.Medication
import entity.User

class MedicationController {
    private var dataManager: IDataManagerMedication = MemoryDataManagerMedication
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    // Resgistrar Medicamento
    fun addMedication(medication: Medication) {
        try {
            dataManager.add(medication)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }
    // Cambiar datos del Mediacamento
    fun updateMedication(medication: Medication) {
        try {
            dataManager.update(medication)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    //Eliminar Usurio
    fun removeMedication(id: Int, username: String){
        try {
            val result = dataManager.getByIdAndUser(id, username)
            if (result == null) {
                throw Exception(context.getString(R.string.MsgDataNotFound))
            }
            dataManager.remove(id,username)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }

    fun getAllMedications(): List<Medication> {
        try {
            return dataManager.getAll()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }
    fun getMedicationById(id: Int, username: String): Medication? {
        try {
            return dataManager.getByIdAndUser(id, username)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetEspecificData))
        }
    }

}