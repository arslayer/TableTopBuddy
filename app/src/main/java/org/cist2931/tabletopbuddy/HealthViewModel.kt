package org.cist2931.tabletopbuddy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// Health tracker screen
//@Composable
//fun HealthScreen(navController: NavController){
//    var healthInit by rememberSaveable { mutableStateOf("") }
//    var healthAdjust by rememberSaveable { mutableStateOf("") }
//    var totalHealth by rememberSaveable { mutableStateOf("") }
//
//    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//        Row {
//            OutlinedTextField(value = healthInit, onValueChange = { healthInit = it }, label = {
//                Text(
//                    stringResource(R.string.current_health)
//                )
//            })
//            Button(
//                onClick = { totalHealth = healthInit }
//            ) { Text("Submit")}
//        }
//        OutlinedTextField(
//            value = healthAdjust,
//            onValueChange = {healthAdjust = it},
//            label = {Text(stringResource(R.string.health_adjustment))}
//        )
//        Row {
//            Button(
//                onClick = { totalHealth = addHealth(totalHealth, healthAdjust) }
//            ) { Text(stringResource(R.string.add))}
//            Button(
//                onClick = { totalHealth = subtractHealth(totalHealth, healthAdjust)}
//            ) { Text(stringResource(R.string.subtract))}
//        }
//        Text(stringResource(R.string.current_health_display, totalHealth))
//        Button(
//            onClick = { navController.navigate(MainNames.Start.name) }
//        ) { Text(stringResource(R.string.home))}
//    }
//}

class HealthViewModel : ViewModel() {
    var totalHealth by mutableStateOf("0")
        private set

    var healthAdjust by mutableStateOf("0")
        private set

    fun updateAdjustment(healthAdjust: String) {
        this.healthAdjust = healthAdjust
    }

    fun updateHealth(healthSet: String) {
        totalHealth = healthSet
    }

    fun addHealth() {
        totalHealth = (totalHealth.toInt() + healthAdjust.toInt()).toString()
    }

    fun subtractHealth() {
        totalHealth = (totalHealth.toInt() - healthAdjust.toInt()).toString()
    }
}


