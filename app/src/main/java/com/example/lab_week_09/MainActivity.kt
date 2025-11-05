package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme // Button dan Text sudah tidak perlu di-import di sini
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// ⭐️ IMPORT BARU: Mengimpor elemen kustom Anda
import com.example.lab_week_09.ui.theme.OnBackgroundItemText
import com.example.lab_week_09.ui.theme.OnBackgroundTitleText
import com.example.lab_week_09.ui.theme.PrimaryTextButton
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}


@Composable
fun Home() {
    val listData = remember {
        mutableStateListOf(
            Student("Tanu"),
            Student("Tina"),
            Student("Tono")
        )
    }
    var inputField = remember { mutableStateOf(Student("")) }

    HomeContent(
        listData,
        inputField.value,
        { input -> inputField.value = inputField.value.copy(name = input) },
        {
            if (inputField.value.name.isNotBlank()) {
                listData.add(inputField.value)
                inputField.value = Student("")
            }
        }
    )
}

// ⭐️ DIPERBARUI: HomeContent sekarang menggunakan Elemen Kustom
@Composable
fun HomeContent(
    listData: SnapshotStateList<Student>,
    inputField: Student,
    onInputValueChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    //LazyColumn diperbarui sesuai instruksi nomor 3
    LazyColumn {
        //Here, we use item to display an item inside the LazyColumn
        item {
            Column(
                //... (Komentar modifier tetap sama) ...
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                //... (Komentar alignment tetap sama) ...
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Here, we call the OnBackgroundTitleText UI Element
                OnBackgroundTitleText(text = stringResource(
                    id = R.string.enter_item)
                )

                //Here, we use TextField to display a text input field
                TextField(
                    //Set the value of the input field
                    value = inputField.name,
                    //Set the keyboard type of the input field
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    //Set what happens when the value of the input field changes
                    onValueChange = {
                        //Here, we call the onInputValueChange lambda function
                        //... (Komentar onValueChange tetap sama) ...
                        onInputValueChange(it)
                    }
                )
                //Here, we call the PrimaryTextButton UI Element
                PrimaryTextButton(text = stringResource(
                    id = R.string.button_click)
                ) {
                    onButtonClick()
                }
            }
        }
        //Here, we use items to display a list of items inside the LazyColumn
        //... (Komentar items tetap sama) ...
        items(listData) { item ->
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Here, we call the OnBackgroundItemText UI Element
                OnBackgroundItemText(text = item.name)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    LAB_WEEK_09Theme {
        Home()
    }
}

data class Student(
    var name: String
)