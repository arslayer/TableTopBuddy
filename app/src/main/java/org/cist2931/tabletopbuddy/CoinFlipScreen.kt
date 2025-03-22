package org.cist2931.tabletopbuddy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.random.Random
import kotlin.random.nextInt

// Screen for coinflip
@Composable
fun CoinFlipScreen(navController: NavController){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        var flipResult by rememberSaveable { mutableStateOf("") }
        Text(text = flipResult)
        Spacer(Modifier.height(20.dp))
        Row {
            Button(onClick = {
                val side = Random.nextInt(1..2)
                if (side == 1) {
                    flipResult = "Heads"
                } else {
                    flipResult = "Tails"
                }
            }) { Text(text = stringResource(R.string.flip_coin)) }
            Button(onClick = {navController.navigate(MainNames.Start.name)}) {
                Text("Back")
            }
        }
    }
}

//@Preview
//@Composable
//fun CoinFlipScreenPreview(){
//    TableTopBuddyTheme {
//        CoinFlipScreen(navController = NavController)
//    }
//}