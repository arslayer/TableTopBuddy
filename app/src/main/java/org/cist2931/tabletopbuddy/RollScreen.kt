package org.cist2931.tabletopbuddy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.cist2931.tabletopbuddy.ui.theme.TableTopBuddyTheme
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
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = numDice,
            onValueChange = { numDice = it.ifBlank { "1" } },
            label = { Text(stringResource(R.string.number_of_dice))}
        )
        numSides = diceSelectorRadio().toString()
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
    return sum
}

fun getDiceRolled(dice: List<Int>): String {
    val diceString = StringBuilder()
    for(it in dice) {
        diceString.append("$it ")
    }
    return diceString.toString()
}

@Composable
fun diceSelectorRadio(modifier: Modifier = Modifier) : Int {
    val diceOptions = listOf("4", "6", "8", "10", "12", "20", "100")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(diceOptions[0]) }
    Column(modifier.selectableGroup(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(R.string.number_of_sides))
        diceOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
    return selectedOption.toInt()
}

@Preview(showBackground = true)
@Composable
fun RollScreenPreview() {
    TableTopBuddyTheme {
        RollScreen(navController = rememberNavController())
    }
}

