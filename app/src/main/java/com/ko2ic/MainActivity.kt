package com.ko2ic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ko2ic.ui.theme.SpikecomposeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SpikecomposeTheme {
        MyContentView()
      }
    }
  }
}

@Composable
fun MyContentView() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = "main") {
    composable("main") {
      MainScreen {
        navController.navigate("second")
      }
    }
    composable("second") {
      SecondScreen {
        navController.navigateUp()
      }
    }
  }
}

@Composable
private fun MainScreen(toSecond: () -> Unit) {
  SideEffect {
    println("MainScreen")
  }
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("My TopAppBar") },
      )
    }
  ) {
    LazyColumn(
      Modifier
        .fillMaxWidth()
    ) {
      item {
        ListTitle(
          title = "シンプルなカウントアップ",
          onClick = {
            toSecond()
          })
      }
      item { Divider() }
    }
  }
}

@Composable
private fun ListTitle(
  title: String,
  onClick: () -> Unit,
) {
  SideEffect {
    println("ListTitle")
  }
  Row(verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.clickable { onClick() }
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
      Text(text = title, style = TextStyle(fontSize = 14.sp))
    }
  }
}

@Composable
fun SecondScreen(onClick: () -> Unit) {
  SideEffect {
    println("SecondScreen")
  }
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("My TopAppBar") },
        navigationIcon = {
          IconButton(onClick = {
            onClick()
          }) {
            Icon(Icons.Outlined.Close, contentDescription = "Close")
          }
        },
      )
    }
  ) {
    FullScreenDialog {
      onClick()
    }
  }
}

@Composable
fun FullScreenDialog(onClose: () -> Unit) {
  SideEffect {
    println("FullScreenDialog")
  }
  Dialog(onDismissRequest = onClose) {
    Surface(
      modifier = Modifier.fillMaxSize(),
      shape = RoundedCornerShape(0.dp),
      color = Color.White
    ) {
      Box(
        contentAlignment = Alignment.Center
      ) {
        Scaffold(
          topBar = {
            TopAppBar(
              title = { Text("My TopAppBar") },
              navigationIcon = {
                IconButton(onClick = {
                  onClose()
                }) {
                  Icon(Icons.Outlined.Close, contentDescription = "Close")
                }
              },
            )
          }) {
          CountUpScreen(viewModel<MainViewModel>())
        }
      }
    }
  }
}

@Composable
fun CountUpScreen(viewModel: MainViewModel) {
  val count: Int by viewModel.count.collectAsState()
  SideEffect {
    println("CountUpScreen")
  }
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {

    Text(
      text = "$count"
    )
    Button(onClick = {
      viewModel.increaseCount()
    }) {
      Icon(Icons.Outlined.Add, contentDescription = "+")
    }
    
  }
}

@Composable
private fun ACompose(count: Int) {
  SideEffect {
    println("ACompose")
  }

  Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(
      text = "$count"
    )
    ChildACompose()
  }
}

@Composable
private fun ChildACompose() {
  SideEffect {
    println("ChildACompose")
  }
  Text(
    text = "ChildACompose"
  )
}

@Composable
private fun BCompose() {
  SideEffect {
    println("BCompose")
  }
  Text(
    text = "I am composable that will not be recompose"
  )
}

@Composable
private fun CCompose(onClick: () -> Unit) {
  SideEffect {
    println("CCompose")
  }
  Button(onClick = {
    onClick()
  }) {
    Icon(Icons.Outlined.Add, contentDescription = "+")
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  SpikecomposeTheme {
    SecondScreen({})
  }
}