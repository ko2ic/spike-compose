package com.ko2ic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

  init {
    println("MainViewModel")
  }

  private val _count: MutableStateFlow<Int> = MutableStateFlow(0)

  val count: StateFlow<Int> = _count.asStateFlow()

  fun increaseCount() {
    _count.value++
  }
}