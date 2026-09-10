package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.data.model.TvShow
import com.example.myapplication.viewmodel.TvShowUiState
import com.example.myapplication.viewmodel.TvShowViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TvShowScreen()
        }
    }
}


@Composable
fun TvShowScreen(
    viewModel: TvShowViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var selectedShow by remember {
        mutableStateOf<TvShow?>(null)
    }

    when (val state = uiState) {

        is TvShowUiState.Loading -> {
            LoadingScreen()
        }

        is TvShowUiState.Success -> {

            if (selectedShow == null) {
                ShowList(
                    shows = state.shows,
                    onShowClick = {
                        selectedShow = it
                    }
                )
            } else {
                DetailScreen(
                    show = selectedShow!!,
                    onBack = {
                        selectedShow = null
                    }
                )
            }
        }

        is TvShowUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = {
                    viewModel.getShows()
                }
            )
        }
    }
}


@Composable
fun LoadingScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ShowList(
    shows: List<TvShow>,
    onShowClick: (TvShow) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "TV Shows",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(shows) { show ->

                TvShowCard(
                    show = show,
                    onClick = {
                        onShowClick(show)
                    }
                )
            }
        }
    }
}


@Composable
fun TvShowCard(
    show: TvShow,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(12.dp)
        ) {

            AsyncImage(
                model = show.image?.medium,
                contentDescription = show.name,
                modifier = Modifier
                    .width(100.dp)
                    .height(140.dp)
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column {

                Text(
                    text = show.name
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "⭐ ${show.rating?.average ?: "N/A"}"
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = show.genres.joinToString(", ")
                )
            }
        }
    }
}


@Composable
fun DetailScreen(
    show: TvShow,
    onBack: () -> Unit
) {

    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        IconButton(
            onClick = {

                val shareIntent = Intent(
                    Intent.ACTION_SEND
                ).apply {

                    type = "text/plain"

                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Lihat acara TV ini: ${show.name}"
                    )
                }

                context.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        "Bagikan melalui"
                    )
                )
            }
        ) {

            Text("📤")
        }


        AsyncImage(
            model = show.image?.original
                ?: show.image?.medium,

            contentDescription = show.name,

            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )


        Text(
            text = show.name,
            modifier = Modifier.padding(top = 16.dp)
        )


        Text(
            text = "⭐ ${show.rating?.average ?: "N/A"}"
        )


        Text(
            text = "Genre: ${show.genres.joinToString(", ")}"
        )


        Text(
            text = show.summary ?: "Tidak ada sinopsis.",
            modifier = Modifier.padding(top = 8.dp)
        )


        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 16.dp)
        ) {

            Text("← Kembali")
        }
    }
}


@Composable
fun ErrorScreen(
    message: String,
    onRetry: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Gagal memuat data"
            )

            Text(
                text = message,
                modifier = Modifier.padding(8.dp)
            )

            Button(
                onClick = onRetry
            ) {

                Text("Coba Lagi")
            }
        }
    }
}