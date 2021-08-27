package com.example.myshootinrangediary.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.myshootinrangediary.repository.Entry
import com.example.myshootinrangediary.utils.DateTimeConverter.dateTimeToString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EntryView(entry: Entry)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(Dp(5.0F))),
        elevation = Dp(5.0F),
        border = BorderStroke(Dp(1.0F), Color.DarkGray)
    )
    {
        Column() {
            Text(text = String.format("%s %s", entry.firstName, entry.lastName))
            Text(text = String.format("%s - %s",
                dateTimeToString(entry.startDate),
                dateTimeToString(entry.endDate)
            ))
        }
    }
}
