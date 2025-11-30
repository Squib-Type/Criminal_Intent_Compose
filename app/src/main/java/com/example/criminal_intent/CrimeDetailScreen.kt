package com.example.criminal_intent

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.UUID

@Composable
fun CrimeDetailScreen(
    crimeId: UUID,
    onBack: () -> Unit,
    viewModel: CrimeDetailViewModel = viewModel(
        factory = CrimeDetailViewModelFactory(crimeId)
    )
) {
    val crime by viewModel.crime.collectAsStateWithLifecycle()
    var showToast by remember { mutableStateOf(false) }

    BackHandler {
        if (crime?.title?.isBlank() == true) {
            showToast = true
        } else {
            onBack()
        }
    }

    crime?.let { currentCrime ->
        CrimeDetailContent(
            crime = currentCrime,
            onTitleChange = { newTitle ->
                viewModel.updateCrime { it.copy(title = newTitle) }
            },
            onSolvedChange = { isSolved ->
                viewModel.updateCrime { it.copy(isSolved = isSolved) }
            }
        )
    }

    if (showToast) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(2000)
            showToast = false
        }
        Snackbar(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Missing Crime Title")
        }
    }
}

@Composable
fun CrimeDetailContent(
    crime: Crime,
    onTitleChange: (String) -> Unit,
    onSolvedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Crime Title",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = crime.title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter a crime title") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Crime Details",
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        ) {
            Text(text = crime.date.toString())
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Checkbox(
                checked = crime.isSolved,
                onCheckedChange = onSolvedChange
            )
            Text(
                text = "Solved",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}