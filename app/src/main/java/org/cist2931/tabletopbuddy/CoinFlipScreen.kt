package org.cist2931.tabletopbuddy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.cist2931.tabletopbuddy.ui.theme.TableTopBuddyTheme
import kotlin.random.Random
import kotlin.random.nextInt

// Screen for coinflip
@Composable
fun CoinFlipScreen(navController: NavController) {
    var flipResult by rememberSaveable { mutableStateOf("") }
    val imageResource = when(flipResult) {
        "Heads" -> R.drawable.heads
        else -> R.drawable.tails
    }
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            painter = painterResource(imageResource),
//            contentDescription = flipResult
//        )
        CoinFlipper(
            modifier = Modifier.size(100.dp),
            maxJumpHeight = 400.dp,
            frontSide = { isRunning, flip ->
                Coin(
                    symbolShape = TShape,
                    enabled = !isRunning,
                    onClick = flip
                )
            },
            backSide = { isRunning, flip ->
                Coin(
                    symbolShape = FShape,
                    enabled = !isRunning,
                    onClick = flip
                )
            }
        )
//        Text(flipResult)
        Spacer(Modifier.height(20.dp))
        Row {
            Button(onClick = {
                val side = Random.nextInt(1..2)
                flipResult = if (side == 1) {
                    "Heads"
                } else {
                    "Tails"
                }
            }) { Text(text = stringResource(R.string.flip_coin)) }
            Button(onClick = { navController.navigate(MainNames.Start.name) }) {
                Text("Back")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinFlipScreenPreview() {
    TableTopBuddyTheme {
        CoinFlipScreen(navController = rememberNavController())
    }
}