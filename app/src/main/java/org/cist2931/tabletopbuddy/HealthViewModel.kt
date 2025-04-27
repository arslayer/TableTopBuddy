package org.cist2931.tabletopbuddy

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


