package com.example.todolistappincompose


class TodoViewModel : androidx.lifecycle.ViewModel() {
    private val _todoItems = androidx.lifecycle.MutableLiveData<List<ItemData>>(emptyList())
    val todoItems: androidx.lifecycle.LiveData<List<ItemData>> = _todoItems

    fun addTodoItem(item: ItemData) {
        val currentList = _todoItems.value ?: emptyList()
        _todoItems.value = currentList + item
    }

    fun removeTodoItem(item: ItemData) {
        val currentList = _todoItems.value ?: emptyList()
        _todoItems.value = currentList - item
    }
}