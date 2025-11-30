package com.example.criminal_intent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.UUID

@Composable
fun CrimeListScreen(
    onCrimeClick: (UUID) -> Unit,
    viewModel: CrimeListViewModel = viewModel()
) {
    val crimes by viewModel.crimes.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(crimes, key = { it.id }) { crime ->
            CrimeListItem(
                crime = crime,
                onClick = { onCrimeClick(crime.id) }
            )
        }
    }
}

@Composable
fun CrimeListItem(
    crime: Crime,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = crime.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = crime.date.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (crime.isSolved) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_solved),
                    contentDescription = "Solved",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}