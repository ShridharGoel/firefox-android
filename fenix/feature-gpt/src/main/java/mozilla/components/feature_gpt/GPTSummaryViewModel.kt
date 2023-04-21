package mozilla.components.feature_gpt

import android.provider.Settings.Global
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.chatgpt.models.ChatRequest
import com.example.chatgpt.models.RequestMessage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GPTSummaryViewModel: ViewModel() {

    val gptFlow = MutableStateFlow("")

    @OptIn(DelicateCoroutinesApi::class)
    fun getContent(url: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val prompt = "$url\n\ntl;dr"
            val response = try {
                RetrofitService.gptApi.getChatResponse(
                    ChatRequest(
                        modelName = "gpt-3.5-turbo",
                        messages = listOf(
                            RequestMessage(
                                role = "user",
                                content = prompt
                            )
                        )
                    )
                )
            } catch (e: Exception) {
                gptFlow.emit("Error: ${e.message}")
                return@launch
            }

            gptFlow.emit(response.choices.first().message?.content?.trim() ?: "")
        }
    }
}
