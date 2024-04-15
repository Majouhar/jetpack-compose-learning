package com.majou.composelearning


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majou.composelearning.ui.theme.ComposeLearningTheme



class BasicLearningActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme {
                // A surface container using the 'background' color from the theme
                BasicMainUI()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingBasic(
    name: String,
    nameList: List<String>,
    inputValue: String,
    buttonClick: () -> Unit,
    inputChange: (value: String) -> Unit

) {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize(1f)) {
        Surface(
            color = Color.DarkGray, modifier = Modifier.wrapContentSize(align = Alignment.BottomEnd)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(1f)
            ) {


                for (s in nameList) {
                    listContentBasic(text = s)
                }

                TextField(value = inputValue, onValueChange = { newInput ->
                    inputChange(newInput)
                }, modifier = Modifier.padding(10.dp))

                Text(text = "Hello $name!",
                    modifier = Modifier
                        .clickable { }
                        .padding(24.dp),
                    style = TextStyle(
                        color = Color.Blue, fontWeight = FontWeight.W500, fontSize = 24.sp
                    ))
                Button(onClick = { buttonClick() }) {
                    Text(
                        text = "Click",
                        modifier = Modifier.wrapContentWidth(align = Alignment.End),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }

}

@Composable
fun BasicMainUI(viewModel: MainViewModel = MainViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(1f), color = Color.White
    ) {
        val greetingListState = remember { mutableStateListOf("Muhammed") }
        val newNameStateContent = viewModel.textFieldState.observeAsState("")
        GreetingBasic("Android",
            greetingListState,
            newNameStateContent.value,
            { greetingListState.add(newNameStateContent.value);viewModel.onTextChanged("") }) { string ->
            viewModel.onTextChanged(string)
        }
    }
}

@Composable
fun listContentBasic(text: String) {
    Text(text = text, style = TextStyle(color = Color.White))
}



@Preview(showBackground = true)
@Composable
fun GreetingBasicPreview() {

    BasicMainUI()

}