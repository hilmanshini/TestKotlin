package app.view

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.common.ModelResult
import app.domain.model.UserModel

//
//
@Composable
fun UserScreen(
    viewModel: UserScreenViewModel = hiltViewModel(),
) {


    val state: ModelResult<List<UserModel>> by viewModel.userData
    Surface( color = MaterialTheme.colorScheme.background
    ) {
        if (state.isLoading()) {
            UserLoadingScreen()
        } else if (state.isFailure()) {
            UserFailureScreen {
                viewModel.loadGenre()
            }
        } else {
            UserSuccessScreen(
                pageCount = {
                    state.asSuccess().data.size
                }, getItem = { index ->
                    state.asSuccess().data[index]
                })
        }
    }

}

@Composable
@Preview
fun UserFailureScreen(
    onRefresh: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = LocalIndication.current,
                    onClick = {
                        println()
                        onRefresh()
                    }
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.SignalWifiOff, "No Network")
            Text(
                "Failed to connect to the server\n tap here to retry",

                modifier = Modifier.padding(top = 10.dp), textAlign = TextAlign.Center
            )

        }
    }
}

@Composable
@Preview
fun UserLoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(), content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    progress = { 10f },
                    modifier = Modifier
                        .size(40.dp)
                )
                Text("tunggu sebentar ", Modifier.padding(top = 10.dp))
            }
        })
}


@Composable
@Preview
fun UserSuccessScreen(
    pageCount: () -> Int = { 20 },
    getItem: (Int) -> UserModel = { UserModel("", "") },
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
//        contentAlignment = Alignment.Center

    ) {


//        StaticBottomSheetScaffold(Modifier.align(Alignment.BottomCenter))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn (
//                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(18.dp),
//                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(pageCount()) { index ->
                    UserSuccessCard(getItem(index))
                }
            }
        }
//        StaticBottomSheetScaffold(
//            Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter)
//        )
//        Surface(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter),
//            color = MaterialTheme.colorScheme.surface,
//            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
//            tonalElevation = 6.dp
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text("Bottom overlay", style = MaterialTheme.typography.titleMedium)
//                Text("Sits above the full-screen content")
//            }
//        }
    }
}

@Composable
@Preview
fun UserSuccessCard(
    genre: UserModel = UserModel("test", "test"),
) {
    Card(
        onClick = {
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        content = {
            Column {
                Text(
                    genre.name,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(12f, type = TextUnitType.Sp)
                )
                Text(
                    genre.email,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(12f, type = TextUnitType.Sp)
                )
            }

        }
    )
}