package mozilla.components.feature_gpt

import com.example.chatgpt.models.ChatRequest
import com.example.chatgpt.models.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptAPI {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer API_KEY"
    )
    @POST("v1/chat/completions")
    suspend fun getChatResponse(@Body chatRequest: ChatRequest): ChatResponse
}
