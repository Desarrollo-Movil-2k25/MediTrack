package entity

data class UserResponse(val data: DTOUser?,
                        val responseCode: Int,
                        val message: String)
