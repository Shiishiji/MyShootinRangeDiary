package com.example.myshootinrangediary.form

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

@Composable
fun AddNewEntryForm()
{
    var text by rememberSaveable { mutableStateOf("") }

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
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "Imie") },
                    singleLine = true
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = { text = it },
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
                Text(text = "Godzina zakończenia")
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
                value = text,
                onValueChange = { text = it },
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
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "Broń") },
                    singleLine = true
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = { text = it },
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
                                value = text,
                                onValueChange = { text = it },
                                label = { Text(text = "Imie") },
                                singleLine = true
                            )
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = text,
                                onValueChange = { text = it },
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
                value = text,
                onValueChange = { text = it },
                label = { Text(text = "Podpis") },
                singleLine = true
            )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = "Zapisz wpis")
        }
    }
}