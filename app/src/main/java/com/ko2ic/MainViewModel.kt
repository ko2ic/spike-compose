package com.ko2ic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

  init {
    println("MainViewModel")
  }

  // こっちでやると無駄にincreaseCount()を呼び出したコンポーネントが再コンポジションしちゃう
//  private val _count: MutableStateFlow<Int> = MutableStateFlow(0)
//  val count: StateFlow<Int> = _count.asStateFlow()

  private val _count: MutableState<Int> = mutableStateOf(0)
  val count: State<Int> = _count
  
  fun increaseCount() {
    _count.value++
  }
}