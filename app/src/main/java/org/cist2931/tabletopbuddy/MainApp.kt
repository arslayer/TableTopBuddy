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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.cist2931.tabletopbuddy.ui.theme.TableTopBuddyTheme

enum class MainNames {
    Start,
    CoinFlip,
    Roll,
}

@Composable
fun MainScreen(navController: NavController, vm: HealthViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(color = MaterialTheme.colorScheme.primary, text = "Welcome to TableTopBuddy!")
        OutlinedTextField(
            value = vm.totalHealth,
            onValueChange = { value ->
                vm.updateHealth(value.filter { it.isDigit() })
                if (vm.totalHealth.isBlank()) vm.updateHealth("0")
            },
            label = {
                Text(stringResource(R.string.current_health))
            })
        OutlinedTextField(
            value = vm.healthAdjust,
            onValueChange = { value ->
                vm.updateAdjustment(value.filter { it.isDigit() })
                if (vm.healthAdjust.isBlank()) vm.updateAdjustment("0")
            },
            label = { Text(stringResource(R.string.health_adjustment)) }
        )
        Row {
            Button(
                onClick = { vm.addHealth() }
            ) { Text(stringResource(R.string.add)) }
            Button(
                onClick = { vm.subtractHealth() }
            ) { Text(stringResource(R.string.subtract)) }
        }
        Button(onClick = { navController.navigate(MainNames.CoinFlip.name) }) {
            Text(
                text = stringResource(
                    R.string.flip_a_coin
                )
            )
        }
        Button(onClick = { navController.navigate(MainNames.Roll.name) }) {
            Text(
                text = stringResource(
                    R.string.roll_dice
                )
            )
        }
    }
}


@Composable
fun MainApp() {
    val navController = rememberNavController()
    val vm: HealthViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = MainNames.Start.name
    ) {
        composable(route = MainNames.Start.name) {
            MainScreen(
                navController,
                vm
            )
        }
        composable(route = MainNames.CoinFlip.name) {
            CoinFlipScreen(navController)
        }
        composable(route = MainNames.Roll.name) {
            RollScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TableTopBuddyTheme {
        MainApp()
    }
}