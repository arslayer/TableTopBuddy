package org.cist2931.tabletopbuddy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

// Health tracker screen
@Composable
fun HealthScreen(navController: NavController){
    var healthInit by rememberSaveable { mutableStateOf("") }
    var healthAdjust by rememberSaveable { mutableStateOf("") }
    var totalHealth by rememberSaveable { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            OutlinedTextField(value = healthInit, onValueChange = { healthInit = it }, label = {
                Text(
                    stringResource(R.string.total_health)
                )
            })
            Button(
                onClick = { totalHealth = healthInit }
            ) { Text("Submit")}
        }
        OutlinedTextField(
            value = healthAdjust,
            onValueChange = {healthAdjust = it},
            label = {Text(stringResource(R.string.health_adjustment))}
        )
        Row {
            Button(
                onClick = { totalHealth = addHealth(totalHealth, healthAdjust) }
            ) { Text(stringResource(R.string.add))}
            Button(
                onClick = { totalHealth = subtractHealth(totalHealth, healthAdjust)}
            ) { Text(stringResource(R.string.subtract))}
        }
        Text(stringResource(R.string.total_health_display, totalHealth))
        Button(
            onClick = { navController.navigate(MainNames.Start.name) }
        ) { Text(stringResource(R.string.home))}
    }
}

fun addHealth(totalHealth: String, healthAdjust: String): String {
    return (totalHealth.toInt() + healthAdjust.toInt()).toString()
}

fun subtractHealth(totalHealth: String, healthAdjust: String): String {
    return (totalHealth.toInt() - healthAdjust.toInt()).toString()
}


