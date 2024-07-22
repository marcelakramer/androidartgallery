package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGallery()
                }
            }
        }
    }
}

data class Art(
    val imageId: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtGallery() {
    var currentArtIndex by remember { mutableIntStateOf(0) }

    val arts = listOf(
        Art(R.drawable.es, "eternal sunshine", "Ariana Grande", "2024"),
        Art(R.drawable.eics, "emails i can't send", "Sabrina Carpenter", "2023"),
        Art(R.drawable.folk, "folklore", "Taylor Swift", "2020")
    )

    fun previousArt() {
        currentArtIndex = (currentArtIndex - 1 + arts.size) % arts.size
    }

    fun nextArt() {
        currentArtIndex = (currentArtIndex + 1) % arts.size
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtImage(arts[currentArtIndex].imageId)
        Spacer(modifier = Modifier.height(16.dp))
        ArtDescription(arts[currentArtIndex].title, arts[currentArtIndex].artist, arts[currentArtIndex].year)
        Spacer(modifier = Modifier.height(64.dp))
        Buttons(previousFun = { previousArt() }, nextFun = { nextArt() })
    }
}

@Composable
fun ArtImage(imageId: Int) {
    Image(
        painter = painterResource(imageId),
        contentDescription = null,
        modifier = Modifier.size(300.dp)
    )
}

@Composable
fun ArtDescription(title: String, artist: String, year: String) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = artist,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = year,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Buttons(
    previousFun: () -> Unit,
    nextFun: () -> Unit
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom
    ) {
        NavigationButton(onClick = { previousFun() }, label = stringResource(R.string.previous_button))
        NavigationButton(onClick = { nextFun() }, label = stringResource(R.string.next_button))
    }
}

@Composable
fun NavigationButton(onClick: () -> Unit, label: String) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp).width(128.dp)
    ) {
        Text(text = label, color = Color.White, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    ArtGalleryTheme {
        ArtGallery()
    }
}
