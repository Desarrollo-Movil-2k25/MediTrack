import com.google.gson.JsonElement

data class ApiResponse(
    val data: JsonElement?,
    val responseCode: Int,
    val message: String
)
