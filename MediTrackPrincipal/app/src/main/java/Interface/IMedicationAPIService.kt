package Interface

import ApiResponse
import entity.DTOMedication
import entity.MedicationResponse
import retrofit2.http.*

interface IMedicationAPIService {

    @GET("/api/medications")
    suspend fun getAll(): ApiResponse

    @GET("/api/medications/{username}")
    suspend fun getByUser(@Path("username") username: String): ApiResponse

    @GET("/api/medications/{id}/{username}")
    suspend fun getByIdAndUser(
        @Path("id") id: Int,
        @Path("username") username: String
    ): ApiResponse

    @Headers("Content-Type: application/json")
    @POST("/api/medications")
    suspend fun postMedication(@Body medication: DTOMedication): MedicationResponse

    @Headers("Content-Type: application/json")
    @PUT("/api/medications")
    suspend fun updateMedication(@Body medication: DTOMedication): MedicationResponse

    @DELETE("/api/medications/{id}/{username}")
    suspend fun deleteMedication(
        @Path("id") id: Int,
        @Path("username") username: String
    ): MedicationResponse
}
