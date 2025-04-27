package org.cist2931.tabletopbuddy

/**
 * TableTopBuddy: A Health Tracker, Dice roller, and coin flipper for Android
 *     Copyright (C) 2025  Davin Asiala, Philip Duong, and Jesus Alvarez
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *     Reach us at our github page: https://github.com/arslayer/TableTopBuddy
 */

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.cist2931.tabletopbuddy.ui.theme.TableTopBuddyTheme

// Screen for coinflip
@Composable
fun CoinFlipScreen(navController: NavController) {
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
//            Button(onClick = {
//                val side = Random.nextInt(1..2)
//                flipResult = if (side == 1) {
//                    "Heads"
//                } else {
//                    "Tails"
//                }
//            }) { Text(text = stringResource(R.string.flip_coin)) }
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