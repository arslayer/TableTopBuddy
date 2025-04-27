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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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


