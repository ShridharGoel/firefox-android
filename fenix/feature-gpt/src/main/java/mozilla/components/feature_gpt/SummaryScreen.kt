package mozilla.components.feature_gpt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SummaryScreen(textState: MutableState<String>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        val scroll = rememberScrollState(0)

        Text(
            text = stringResource(id = R.string.summary),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = textState.value,
            color = Color.Black,
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.verticalScroll(scroll)
        )
    }
}
