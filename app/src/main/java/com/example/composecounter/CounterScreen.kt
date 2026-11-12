package com.example.composecounter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecounter.ui.theme.ComposeCounterTheme

@Composable
fun CounterApp(
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel()
) {
    val counter by viewModel.counter
    val nameInput by viewModel.nameInput
    val savedName by viewModel.savedName

    DataDiriScreen(
        counter = counter,
        nameInput = nameInput,
        savedName = savedName,
        onIncrement = { viewModel.increment() },
        onDecrement = { viewModel.decrement() },
        onReset = { viewModel.reset() },
        onNameChange = { viewModel.updateName(it) },
        onSaveName = { viewModel.saveName() },
        modifier = modifier
    )
}

@Composable
fun DataDiriScreen(
    counter: Int,
    nameInput: String,
    savedName: String,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onReset: () -> Unit,
    onNameChange: (String) -> Unit,
    onSaveName: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Counter App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tampilan counter
        CounterDisplay(
            value = counter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // Nama yang sudah disimpan tampil di bawah counter
        if (savedName.isNotBlank()) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Nama: $savedName",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol counter
        CounterButtons(
            onDecrement = onDecrement,
            onIncrement = onIncrement,
            onReset = onReset
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Form input nama
        OutlinedTextField(
            value = nameInput,
            onValueChange = onNameChange,
            label = { Text("Nama") },
            placeholder = { Text("Masukkan nama Anda") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tombol simpan
        Button(
            onClick = onSaveName,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {
            Text("SIMPAN")
        }
    }
}

@Composable
fun CounterDisplay(
    value: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(
            color = Color(0xFFF5F5F5),
            shape = RoundedCornerShape(16.dp)
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Nilai Counter",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        Text(
            text = value.toString(),
            fontSize = 120.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CounterButtons(
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onDecrement,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF44336)
                )
            ) {
                Text("KURANG")
            }

            Button(
                onClick = onIncrement,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text("TAMBAH")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onReset,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {
            Text("RESET")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DataDiriScreenPreview() {
    ComposeCounterTheme {
        DataDiriScreen(
            counter = 7,
            nameInput = "Devina Aulia Tri Kurnia",
            savedName = "Devina Aulia Tri Kurnia",
            onIncrement = {},
            onDecrement = {},
            onReset = {},
            onNameChange = {},
            onSaveName = {}
        )
    }
}
