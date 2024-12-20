package com.example.viewmodel1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterScreen()
        }
    }
}
class CounterViewModel : ViewModel() {
    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    fun increase() {
        _counter.value = (_counter.value ?: 0) + 1
    }

    fun decrease() {
        _counter.value = (_counter.value ?: 0) - 1
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {
    val counterValue = viewModel.counter.observeAsState(0)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Counter: ${counterValue.value}",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = { viewModel.increase() }) {
                    Text("Kasvata")
                }
                Button(onClick = { viewModel.decrease() }) {
                    Text("Pienennä")
                }
            }
        }
    }
}
