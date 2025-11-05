package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row // ⭐️ Import baru
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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
// ⭐️ Import baru untuk Navigasi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme
import com.example.lab_week_09.ui.theme.OnBackgroundItemText
import com.example.lab_week_09.ui.theme.OnBackgroundTitleText
import com.example.lab_week_09.ui.theme.PrimaryTextButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09Theme {
                // Sesuai instruksi nomor 5: Root diubah ke App()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    App(
                        navController = navController
                    )
                }
            }
        }
    }
}

// Sesuai instruksi nomor 4: Composable App() baru
@Composable
fun App(navController: NavHostController) {
    //Here, we use NavHost to create a navigation graph
    //... (Komentar instruksi) ...
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        //Here, we create a route called "home"
        //... (Komentar instruksi) ...
        composable("home") {
            //Here, we pass a lambda function that navigates to "resultContent"
            //... (Komentar instruksi) ...
            Home { navController.navigate(
                "resultContent/?listData=$it")
            }
        }
        //Here, we create a route called "resultContent"
        //... (Komentar instruksi) ...
        composable(
            "resultContent/?listData={listData}",
            arguments = listOf(navArgument("listData") {
                type = NavType.StringType }
            )
        ) {
            //Here, we pass the value of the argument to the ResultContent composable
            ResultContent(
                it.arguments?.getString("listData").orEmpty()
            )
        }
    }
}

// Sesuai instruksi nomor 6: Home() diperbarui
@Composable
fun Home(
    navigateFromHomeToResult: (String) -> Unit
) {
    val listData = remember {
        mutableStateListOf(
            Student("Tanu"),
            Student("Tina"),
            Student("Tono")
        )
    }
    var inputField = remember { mutableStateOf(Student("")) }

    // Sesuai instruksi nomor 8: HomeContent() diperbarui
    HomeContent(
        listData,
        inputField.value,
        // Ini adalah onInputValueChange
        { input -> inputField.value = inputField.value.copy(name = input) },
        // Ini adalah onButtonClick
        {
            if (inputField.value.name.isNotBlank()) {
                listData.add(inputField.value)
                inputField.value = Student("")
            }
        },
        // Ini adalah navigateFromHomeToResult
        { navigateFromHomeToResult(listData.toList().toString()) }
    )
}

// Sesuai instruksi nomor 7 & 9: HomeContent() diperbarui
@Composable
fun HomeContent(
    listData: SnapshotStateList<Student>,
    inputField: Student,
    onInputValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    navigateFromHomeToResult: () -> Unit // Parameter baru
) {
    // Sesuai instruksi nomor 9: LazyColumn diperbarui
    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnBackgroundTitleText(text = stringResource(
                    id = R.string.enter_item)
                )
                TextField(
                    value = inputField.name,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    onValueChange = {
                        onInputValueChange(it)
                    }
                )
                // Tombol dibungkus dengan Row
                Row {
                    PrimaryTextButton(text = stringResource(id =
                        R.string.button_click)) {
                        onButtonClick()
                    }
                    // Tombol navigasi baru
                    PrimaryTextButton(text = stringResource(id =
                        R.string.button_navigate)) {
                        navigateFromHomeToResult()
                    }
                }
            }
        }
        items(listData) { item ->
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnBackgroundItemText(text = item.name)
            }
        }
    }
}

// Sesuai instruksi nomor 10: ResultContent() baru
//Here, we create a composable function called ResultContent
//... (Komentar instruksi) ...
@Composable
fun ResultContent(listData: String) {
    Column(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here, we call the OnBackgroundItemText UI Element
        OnBackgroundItemText(text = listData)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    LAB_WEEK_09Theme {
        // Memberi lambda kosong agar preview tetap jalan
        Home(navigateFromHomeToResult = {})
    }
}

// Preview tambahan untuk layar baru
@Preview(showBackground = true)
@Composable
fun PreviewResultContent() {
    LAB_WEEK_09Theme {
        ResultContent(listData = "[Student(name=Tanu), Student(name=Tina)]")
    }
}

data class Student(
    var name: String
)