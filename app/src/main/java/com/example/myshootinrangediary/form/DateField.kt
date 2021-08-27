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
import com.afollestad.materialdialogs.datetime.datePicker
import com.example.myshootinrangediary.utils.DateTimeConverter
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateField(
    value: String,
    onValueChange: (String) -> Unit
)
{
    val dialog = MaterialDialog(LocalContext.current)
    val textState = remember { mutableStateOf(TextFieldValue(text = value)) }

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
                dialog.show {
                    datePicker { dialog, date ->
                        textState.value = TextFieldValue(
                            DateTimeConverter
                                .dateToDateString(
                                    LocalDate.of(date.year, date.month, date.dayOfMonth)
                                )
                        )
                    }
                }
            },
            label = {
                Text(text = "Date")
            }
        )
    }
}