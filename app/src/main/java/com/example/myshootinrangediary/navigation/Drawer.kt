package com.example.myshootinrangediary.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myshootinrangediary.ui.theme.MyShootinRangeDiaryTheme

sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Ekran główny", "home")
    object AddEntry : DrawerScreens("Dodaj wpis", "Add entry")
    object ListOfEntries : DrawerScreens("Lista wpisów", "List of entries")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.AddEntry,
    DrawerScreens.ListOfEntries,
)
@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    MyShootinRangeDiaryTheme {
        Drawer{}
    }
}

