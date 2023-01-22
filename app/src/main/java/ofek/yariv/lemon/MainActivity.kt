package ofek.yariv.lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ofek.yariv.lemon.ui.theme.LemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    LemonImageAndText(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
fun LemonImageAndText(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    val resourceImage = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val titleText = when (currentStep) {
        1 -> stringResource(R.string.lemon_tree)
        2 -> stringResource(R.string.lemon_squeeze)
        3 -> stringResource(R.string.lemon_drink)
        else -> stringResource(R.string.lemon_restart)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(fontSize = 24.sp, text = titleText)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = resourceImage),
            contentDescription = titleText,
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    when (currentStep) {
                        1 -> currentStep++
                        2 -> if ((1..5).random() == 5) currentStep++
                        3 -> currentStep++
                        else -> currentStep = 1
                    }
                }
        )
    }
}