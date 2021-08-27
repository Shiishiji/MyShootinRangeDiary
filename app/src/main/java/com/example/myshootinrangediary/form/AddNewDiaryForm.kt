package com.example.myshootinrangediary.form

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.myshootinrangediary.repository.Entry
import com.example.myshootinrangediary.repository.EntryRepository
import com.example.myshootinrangediary.repository.Weapon
import com.example.myshootinrangediary.utils.DateTimeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNewEntryForm()
{
    var form_firstname by rememberSaveable { mutableStateOf("") }
    var form_lastname by rememberSaveable { mutableStateOf("") }
    var form_license by rememberSaveable { mutableStateOf("") }
    var form_weaponType by rememberSaveable { mutableStateOf("") }
    var form_weaponCaliber by rememberSaveable { mutableStateOf("") }
    var form_weaponFirstName by rememberSaveable { mutableStateOf("") }
    var form_weaponLastName by rememberSaveable { mutableStateOf("") }
    var form_signature by rememberSaveable { mutableStateOf("") }

    var dateTimeStart by rememberSaveable { mutableStateOf(
        DateTimeConverter.dateTimeToString(
            LocalDateTime.now()
        )
    ) }
    var dateTimeEnd by rememberSaveable { mutableStateOf("") }

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
            border = BorderStroke(Dp(1.0F), Color.DarkGray)
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
            border = BorderStroke(Dp(1.0F), Color.DarkGray)
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
            border = BorderStroke(Dp(1.0F), Color.DarkGray)
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
            border = BorderStroke(Dp(1.0F), Color.DarkGray)
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
                        border = BorderStroke(Dp(1.0F), Color.DarkGray)
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(Dp(5.0F))),
            elevation = Dp(5.0F),
            border = BorderStroke(Dp(1.0F), Color.DarkGray)
        )
        {
            TextField(
                value = form_signature,
                onValueChange = { form_signature = it },
                label = { Text(text = "Podpis") },
                singleLine = true
            )
        }

        Button(
            onClick = {
                val dateStart = if (dateTimeStart.isNotBlank()) {
                    LocalDateTime.parse(dateTimeStart, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                } else {
                    LocalDateTime.now()
                }
                val dateEnd = if (dateTimeEnd.isNotBlank()) {
                    LocalDateTime.parse(dateTimeEnd, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                } else {
                    null
                }

                EntryRepository.addEntry(Entry(
                    form_firstname,
                    form_lastname,
                    dateStart,
                    form_license,
                    form_signature,
                    dateEnd,
                    arrayListOf(Weapon(
                      form_weaponFirstName,
                      form_weaponLastName,
                      form_weaponType,
                      form_weaponCaliber
                    ))
                ))
            },
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = "Zapisz wpis")
        }
    }
}
