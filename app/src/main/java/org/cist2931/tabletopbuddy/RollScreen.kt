package org.cist2931.tabletopbuddy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kotlin.random.Random
import kotlin.random.nextInt

// RollScreen
@Composable
fun RollScreen(navController: NavController){
    var numDice by rememberSaveable { mutableStateOf("") }
    var numSides by rememberSaveable { mutableStateOf("") }
    var diceModifier by rememberSaveable { mutableStateOf("0") }
    var allDice: SnapshotStateList<Int>
    var diceTotal by rememberSaveable { mutableIntStateOf(0)}
    var diceRolled by rememberSaveable { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = numDice,
            onValueChange = {numDice = it},
            label = { Text(stringResource(R.string.number_of_dice))}
        )
        OutlinedTextField(
            value = numSides,
            onValueChange = {numSides = it
                            if (it =="") {
                                numSides = "0";
                            }},
            label = { Text(stringResource(R.string.number_of_sides))}
        )
        OutlinedTextField(
            value = diceModifier,
            onValueChange = { diceModifier = it},
            label = {Text(stringResource(R.string.modifier))}
        )
        Row {
            Button(onClick = {
                allDice = rollEm(numSides, numDice)
                diceTotal = getTotal(allDice, toInt = diceModifier.toInt())
                diceRolled = getDiceRolled(allDice)
            }) { Text(stringResource(R.string.roll_dice)) }
            Button(onClick = {navController.navigate(MainNames.Start.name)}) {
                Text(stringResource(R.string.home))
            }
        }
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.die_total, diceTotal))
            Text(stringResource(R.string.die_rolled, diceRolled))
        }
    }
}

fun rollEm(numSides: String = "6", numDice: String = "1"): SnapshotStateList<Int> {
    val dice = mutableListOf<Int>()
    for (i in 0..<numDice.toInt()) {
        dice.add(Random.nextInt(1..numSides.toInt()))
    }
    return dice.toMutableStateList()
}

fun getTotal(dice: List<Int>, toInt: Int = 0): Int {
    var sum = 0
    for(it in dice) {
        sum += it
    }
    sum += toInt
    return sum;
}

fun getDiceRolled(dice: List<Int>): String {
    val diceString = StringBuilder()
    for (it in dice) {
        if (it != dice.lastOrNull()) {
            diceString.append("$it, ")
        }
        else diceString.append("$it")
    }
    return diceString.toString()
}

