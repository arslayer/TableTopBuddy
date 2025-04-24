package org.cist2931.tabletopbuddy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.cist2931.tabletopbuddy.ui.theme.TableTopBuddyTheme

enum class MainNames {
    Start,
    CoinFlip,
    Roll,
    Health
}


@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to TableTopBuddy!")
        Button(onClick = {navController.navigate(MainNames.CoinFlip.name)}) { Text(text = stringResource(
            R.string.flip_a_coin
        )
        ) }
        Button(onClick = {navController.navigate(MainNames.Roll.name)}){ Text(text = stringResource(
            R.string.roll_dice
        )
        )}
        Button(onClick = {navController.navigate(MainNames.Health.name)}){ Text(text = stringResource(
            R.string.track_health
        )
        )}
    }
}





@Composable
fun MainApp(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MainNames.Start.name) {
        composable(route = MainNames.Start.name) {
            MainScreen(
                navController
            )
        }
        composable(route = MainNames.CoinFlip.name) {
            CoinFlipScreen(navController)
        }
        composable(route = MainNames.Roll.name) {
            RollScreen(navController)
        }
        composable(route = MainNames.Health.name) {
            HealthScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    TableTopBuddyTheme { 
        MainApp()
    }
}