package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var paso by remember { mutableStateOf(1) }
    var contar by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        color = MaterialTheme.colorScheme.background
    ) {
        when (paso) {
            1 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.paso_1,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.img_1,
                    onImageClick = {
                        paso = 2
                        contar = (2..4).random()
                    }
                )
            }
            2 -> {
                 LemonTextAndImage(
                     textLabelResourceId = R.string.paso_2,
                     drawableResourceId = R.drawable.lemon_squeeze,
                     contentDescriptionResourceId = R.string.img_2,
                     onImageClick = {
                         contar--
                         if (contar == 0) {
                             paso = 3
                         }
                     }
                 )
            }

            3 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.paso_3,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.img_3,
                    onImageClick = {
                        paso = 4
                    }
                )
            }

            4 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.paso_4,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.img_4,
                    onImageClick = {
                        paso = 1
                    }
                )
            }
        }
    }
}


@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId)
                )
            }

            Text(
                text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}