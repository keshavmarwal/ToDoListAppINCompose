package com.example.todolistappincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.todolistappincompose.ui.theme.ToDoListAppINComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoviewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        enableEdgeToEdge()
        setContent {
            ToDoListAppINComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                     ToDoListPage(modifier = Modifier.padding(innerPadding),viewModel = todoviewModel)
                }
            }
        }
    }
}
