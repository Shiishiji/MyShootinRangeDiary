package com.example.myshootinrangediary.form

import android.graphics.Bitmap
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.unit.Dp
import androidx.core.graphics.applyCanvas
import androidx.navigation.compose.rememberNavController
import com.example.myshootinrangediary.navigation.DrawerScreens
import com.example.myshootinrangediary.repository.Entry
import com.example.myshootinrangediary.repository.EntryRepository
import com.example.myshootinrangediary.repository.Weapon
import com.example.myshootinrangediary.utils.DateTimeConverter
import java.io.File
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNewEntryForm()
{
    val entryId: UUID = UUID.randomUUID()

    var form_firstname by rememberSaveable { mutableStateOf("") }
    var form_lastname by rememberSaveable { mutableStateOf("") }
    var form_license by rememberSaveable { mutableStateOf("") }
    var form_weaponType by rememberSaveable { mutableStateOf("") }
    var form_weaponCaliber by rememberSaveable { mutableStateOf("") }
    var form_weaponFirstName by rememberSaveable { mutableStateOf("") }
    var form_weaponLastName by rememberSaveable { mutableStateOf("") }

    var dateTimeStart by rememberSaveable { mutableStateOf(
        DateTimeConverter.dateTimeToString(
            LocalDateTime.now()
        )
    ) }
    var dateTimeEnd by rememberSaveable { mutableStateOf("") }

    var form_submitStatus: Boolean? by rememberSaveable { mutableStateOf(null) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()))
    {
        Text(text = "Dane osobowe", style = MaterialTheme.typography.h6)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(Dp(5.0F))),
            elevation = Dp(5.0F),
            border = BorderStroke(Dp(1.0F), DarkGray)
        )
        {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = form_firstname,
                    onValueChange = { form_firstname = it },
                    label = { Text(text = "Imie") },
                    singleLine = true
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = form_lastname,
                    onValueChange = { form_lastname = it },
                    label = { Text(text = "Nazwisko") },
                    singleLine = true
                )
            }
        }

        Text(text = "Godziny", style = MaterialTheme.typography.h6)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(Dp(5.0F))),
            elevation = Dp(5.0F),
            border = BorderStroke(Dp(1.0F), DarkGray)
        )
        {
            Column(modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Godzina rozpoczęcia")

                DateTimeField(
                    value = dateTimeStart,
                    onValueChange = { dateTimeStart = it }
                )
                Text(text = "Godzina zakończenia")

                DateTimeField(
                    value = dateTimeEnd,
                    onValueChange = { dateTimeEnd = it }
                )
            }
        }

        Text(text = "Pozwolenie", style = MaterialTheme.typography.h6)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(Dp(5.0F))),
            elevation = Dp(5.0F),
            border = BorderStroke(Dp(1.0F), DarkGray)
        )
        {
            TextField(
                value = form_license,
                onValueChange = { form_license = it },
                label = { Text(text = "Nr pozwolenia na broń albo adres i dokument potwierdzający tożsamość") },
                singleLine = true
            )
        }

        Text(text = "Broń", style = MaterialTheme.typography.h6)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(Dp(5.0F))),
            elevation = Dp(5.0F),
            border = BorderStroke(Dp(1.0F), DarkGray)
        )
        {
            Column(modifier = Modifier.fillMaxWidth())
            {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = form_weaponType,
                    onValueChange = { form_weaponType = it },
                    label = { Text(text = "Broń") },
                    singleLine = true
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = form_weaponCaliber,
                    onValueChange = { form_weaponCaliber = it },
                    label = { Text(text = "Kaliber") },
                    singleLine = true
                )
                Column(modifier = Modifier.fillMaxWidth())
                {
                    Text(text = "Właściciel broni", style = MaterialTheme.typography.h6)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(PaddingValues(Dp(5.0F))),
                        elevation = Dp(5.0F),
                        border = BorderStroke(Dp(1.0F), DarkGray)
                    )
                    {
                        Column()
                        {
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = form_weaponFirstName,
                                onValueChange = { form_weaponFirstName = it },
                                label = { Text(text = "Imie") },
                                singleLine = true
                            )
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = form_weaponLastName,
                                onValueChange = { form_weaponLastName = it },
                                label = { Text(text = "Nazwisko") },
                                singleLine = true
                            )
                        }
                    }
                }
            }
        }

        Text(text = "Podpis", style = MaterialTheme.typography.h6)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(Dp(5.0F))),
            elevation = Dp(5.0F),
            border = BorderStroke(Dp(1.0F), DarkGray)
        )
        {
            SignatureField(entryId)
        }

        Button(
            onClick = {
                val dateStart = if (dateTimeStart.isNotBlank()) {
                    LocalDateTime.parse(
                        dateTimeStart,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                    )
                } else {
                    LocalDateTime.now()
                }
                val dateEnd = if (dateTimeEnd.isNotBlank()) {
                    LocalDateTime.parse(
                        dateTimeEnd,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                    )
                } else {
                    null
                }

                try {
                    EntryRepository.addEntry(
                        Entry(
                            entryId,
                            form_firstname,
                            form_lastname,
                            dateStart,
                            form_license,
                            dateEnd,
                            arrayListOf(
                                Weapon(
                                    form_weaponFirstName,
                                    form_weaponLastName,
                                    form_weaponType,
                                    form_weaponCaliber
                                )
                            )
                        )
                    )

                    form_submitStatus = true
                } catch (e: Exception) {
                    form_submitStatus = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = "Zapisz wpis")

            if (true == form_submitStatus) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        form_submitStatus = null // it will close the dialog
                    },
                    text = {
                           Text("Dodano wpis.", style = MaterialTheme.typography.h6)
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                form_submitStatus = null // it will close the dialog
                                // Navigate to home?
                            }
                        )
                        {
                            Text("Ok")
                        }
                    }
                )
            }
        }
    }
}
