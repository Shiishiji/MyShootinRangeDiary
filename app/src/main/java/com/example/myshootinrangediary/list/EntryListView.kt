package com.example.myshootinrangediary.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.myshootinrangediary.form.DateField
import com.example.myshootinrangediary.repository.EntryRepository
import com.example.myshootinrangediary.utils.DateTimeConverter.dateTimeToDateString
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EntryListView()
{
    val entries = EntryRepository.entries

    var date by rememberSaveable { mutableStateOf(dateTimeToDateString(LocalDateTime.now())) }
    val checkedState = remember { mutableStateOf(true) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(Dp(10.0F))
    )
    {

        Row {
            Checkbox(
                modifier = Modifier.align(Alignment.CenterVertically),
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )

            DateField(
                value = date,
                onValueChange = { date = it }
            )
        }

        entries.forEach {
            EntryView(it)
        }
    }
}