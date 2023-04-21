package mozilla.components.feature_gpt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GptFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val url = arguments?.getString("url")
        val viewModel = ViewModelProvider(this)[GPTSummaryViewModel::class.java]

        viewModel.getContent(url ?: "")

        return ComposeView(requireContext()).apply {
            setContent {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    val textState = remember { mutableStateOf("") }
                    LaunchedEffect(key1 = Unit) {
                        viewModel.gptFlow.collect {
                            textState.value = it
                        }
                    }
                    if (textState.value == "") {
                        CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally).padding(top = 16.dp))
                    } else {
                        SummaryScreen(textState, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(url: String) =
            GptFragment().apply {
                arguments = Bundle().apply {
                    putString("url", url)
                }
            }

        fun show(fragmentManager: FragmentManager, url: String) {
            val instance = newInstance(url)

            fragmentManager.beginTransaction().apply {
                add(instance, "GPT")
                commitAllowingStateLoss()
            }
        }
    }
}
