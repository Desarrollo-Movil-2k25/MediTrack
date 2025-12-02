package entity

data class MedicationResponse(val data: DTOMedication?,
                              val responseCode: Int,
                              val message: String)
