package pe.idat.proyecto.iu

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.idat.proyecto.R
import pe.idat.proyecto.SetupViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritosScreen(navController: NavController, initialIndex: Int, viewModel: SetupViewModel) {
    val productos by viewModel.lista.collectAsState()

    Scaffold(
        topBar = { FavoritosCabecera(navController) },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productos) { product ->
                    ProductCardColumn(product = product)
                }
            }
        },
        bottomBar = { HomePie(navController) },
        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosCabecera(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Favoritos",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.teal_200),
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.Red
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "",
                    tint = Color.Red
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = "",
                    tint = Color.Red
                )
            }
        }
    )
}
