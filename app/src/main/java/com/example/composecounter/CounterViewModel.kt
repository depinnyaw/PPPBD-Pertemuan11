package com.example.composecounter

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel

class CounterViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences(
        "counter_prefs",
        Context.MODE_PRIVATE
    )

    // State counter
    private val _counter = mutableIntStateOf(
        prefs.getInt("counter_value", 0)
    )
    val counter: State<Int> = _counter

    // State untuk input nama
    private val _nameInput = mutableStateOf("")
    val nameInput: State<String> = _nameInput

    // State untuk nama yang sudah disimpan
    private val _savedName = mutableStateOf("")
    val savedName: State<String> = _savedName

    // Fungsi counter
    fun increment() {
        updateCounter(_counter.intValue + 1)
    }

    fun decrement() {
        updateCounter(_counter.intValue - 1)
    }

    fun reset() {
        updateCounter(0)
    }

    private fun updateCounter(newValue: Int) {
        _counter.intValue = newValue

        prefs.edit {
            putInt("counter_value", newValue)
        }
    }

    // Mengubah input nama
    fun updateName(name: String) {
        _nameInput.value = name
    }

    // Menyimpan nama
    fun saveName() {
        _savedName.value = _nameInput.value.trim()
    }
}
