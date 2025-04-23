package org.cist2931.tabletopbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.cist2931.tabletopbuddy.ui.theme.TableTopBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TableTopBuddyTheme {
        MainApp()
    }
}