package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
// import androidx.activity.enableEdgeToEdge // Tidak apa-apa ada di sini
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // ⭐️ PENTING: Import untuk 'items(items)'
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface // ⭐️ PENTING: Import untuk 'Surface'
// import androidx.compose.material3.Scaffold // Tidak digunakan, tapi tidak apa-apa
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
// import androidx.compose.ui.res.stringResource // Saya ganti dengan hardcoded text untuk sementara
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Here, we use setContent instead of setContentView
        setContent {
            //Here, we wrap our content with the theme
            //You can check out the LAB_WEEK_09Theme inside Theme.kt
            LAB_WEEK_09Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //We use Modifier.fillMaxSize() to make the surface fill the whole screen
                    modifier = Modifier.fillMaxSize(),
                    //We use MaterialTheme.colorScheme.background to get the background color
                    //and set it as the color of the surface
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list = listOf("Tanu", "Tina", "Tono")
                    //Here, we call the Home composable
                    Home(list)

                }
            }
        }
    }
}

@Composable
fun Home(
    //Here, we define a parameter called items
    items: List<String>,
) {
    //Here, we use LazyColumn to lazily display a list of items horizontally
    //LazyColumn is more efficient than Column
    //because it only composes and lays out the currently visible items
    //much like a RecyclerView
    //You can also use LazyRow to lazily display a list of items horizontally
    LazyColumn {
        //Here, we use item to display an item inside the LazyColumn
        item {
            Column(
                // Baris 'Column' dan 'vertical = 8.dp' yang error sudah dihapus dari sini
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(), // Menghapus titik koma (;) yang tidak perlu
                //Alignment.CenterHorizontally is used to align the Column horizontally
                //You can also use verticalArrangement = Arrangement.Center to align the Column vertically
                horizontalAlignment = Alignment.CenterHorizontally // Mengaktifkan (uncomment) ini
            ) { // Mengaktifkan (uncomment) ini
                // Menggunakan teks langsung agar pasti bisa di-compile,
                // karena R.string.enter_item mungkin belum ada di file strings.xml Anda
                Text(text = "Enter item")

                //Here, we use TextField to display a text input field
                TextField(
                    //Set the value of the input field
                    value = "", // CATATAN: Ini harus dihubungkan ke state (remember)
                    //Set the keyboard type of the input field
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    //Set what happens when the value of the input field changes
                    onValueChange = {
                    }
                )
                //Here, we use Button to display a button
                //the onClick parameter is used to set what happens when the
                //button is clicked
                Button(onClick = { }) {
                    //Set the text of the button
                    // Menggunakan teks langsung
                    Text(text = "Click Me")
                }
            }
        }
        //Here, we use items to display a list of items inside the LazyColumn
        //This is the RecyclerView replacement
        items(items) { item -> // Mengaktifkan (uncomment) blok ini
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = item)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    // Membungkus preview dengan Theme agar konsisten
    LAB_WEEK_09Theme {
        Home(listOf("Tanu", "Tina", "Tono"))
    }
}