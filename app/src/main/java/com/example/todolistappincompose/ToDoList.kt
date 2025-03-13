package com.example.todolistappincompose



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import java.text.SimpleDateFormat
import java.util.*



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToDoListPagePreview() {
    ToDoListPage(modifier = Modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListPage(
    modifier: Modifier,
    viewModel: TodoViewModel = TodoViewModel()
) {
    val todolist by viewModel.todoItems.observeAsState(emptyList())
    val inputTxt = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8D55E5),
                        Color(0xFFBF4600)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 20.dp, 10.dp, 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputTxt.value,
                onValueChange = { inputTxt.value = it },
                modifier = Modifier.weight(.7f),
                label = { Text(text = "Enter Task") },
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Button(
                onClick = {
                    if (inputTxt.value.isNotBlank()) {
                        viewModel.addTodoItem(
                            ItemData(txt = inputTxt.value, date =System.currentTimeMillis())
                        )
                        inputTxt.value = ""
                    }
                },
                modifier = Modifier
                    .weight(.3f)
                    .padding(10.dp)
            ) {
                Text(text = "Add")
            }
        }
        LazyColumn(
            content = {
                itemsIndexed(todolist) { index: Int, item: ItemData ->
                    TodoItems(item = item, onDelete = { viewModel.removeTodoItem(it) })
                }
            },
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun TodoItems(item: ItemData, onDelete: (ItemData) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Transparent)
            .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp))
            .border(1.dp, Color.Black, shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(10.dp).weight(1f)) {
            Text(
                text = item.txt,
                modifier = Modifier.padding(bottom = 10.dp),
                color = Color.Green,
                fontSize = 20.sp
            )
            Text(
                text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(item.date),
                color = Color.Black,
                fontSize = 12.sp
            )
        }
        IconButton(
            onClick = { onDelete(item) },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_delete_24),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}