package com.example.myshootinrangediary.form

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.afollestad.date.dayOfMonth
import com.afollestad.date.month
import com.afollestad.date.year
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.example.myshootinrangediary.utils.DateTimeConverter
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateTimeField(
    value: String,
    onValueChange: (String) -> Unit
)
{
    val textState = remember { mutableStateOf(TextFieldValue(text = value)) }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        ReadonlyTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                if (value != it.text) {
                    onValueChange(it.text)
                }
            },
            onClick = {
                val dialog = MaterialDialog(context)
                dialog.show {
                    dateTimePicker(requireFutureDateTime = true) { _, dateTime ->
                        textState.value = TextFieldValue(
                            DateTimeConverter
                                .dateTimeToString(
                                    LocalDateTime.of(
                                        dateTime.year,
                                        dateTime.month,
                                        dateTime.dayOfMonth,
                                        dateTime.time.hours,
                                        dateTime.time.minutes,
                                        dateTime.time.seconds
                                    )
                                )
                        )
                    }
                }
            },
            label = {
                Text(text = "DateTime")
            }
        )
    }
}