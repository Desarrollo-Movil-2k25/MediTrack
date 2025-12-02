package Interface

import ApiResponse
import entity.DTOLogin
import entity.DTOUser
import entity.UserResponse
import retrofit2.http.*

interface IUserAPIService {

    @GET("/api/users")
    suspend fun getAll(): ApiResponse

    @GET("/api/users/{id}")
    suspend fun getById(@Path("id") id: String): ApiResponse

    @GET("/api/users/username/{username}")
    suspend fun getByUsername(@Path("username") username: String): ApiResponse

    @Headers("Content-Type: application/json")
    @POST("/api/users")
    suspend fun postUser(@Body user: DTOUser): UserResponse

    @Headers("Content-Type: application/json")
    @POST("/api/users/login")
    suspend fun login(@Body user: DTOLogin): UserResponse

    @Headers("Content-Type: application/json")
    @PUT("/api/users")
    suspend fun updateUser(@Body user: DTOUser): UserResponse

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(@Path("id") userId: String): UserResponse
}
