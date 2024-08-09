package pe.idat.proyecto.iu

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import pe.idat.proyecto.R
import pe.idat.proyecto.SetupViewModel
import pe.idat.proyecto.data.network.response.Promocion


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritosScreen(navController: NavController, viewModel: SetupViewModel) {
    val promociones by viewModel.listaPromociones.collectAsState()

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
                items(promociones) { promo ->
                    PromoCardColumn(promo)
                }
            }
        },
        bottomBar = { HomePie(navController) },
        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())

    )
}
fun Promocion.imageUrl(): String = "http://10.0.2.2:8080/promociones/uploads/$image"
@Composable
fun PromoCardColumn(promocion: Promocion) {
    val imageUrl = promocion.imageUrl() // Asumiendo que ya tienes la función imageUrl()

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),modifier = Modifier
            .fillMaxWidth()
            .padding()
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.teal_200),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = promocion.nombre,
                    style = MaterialTheme.typography.bodyLarge, // Título grande
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = promocion.descripcion, // Agrega la descripción aquí
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Distribuye elementos
                ) {
                    Text(
                        text = "$${promocion.precio}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Row { // Agrupa los iconos
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favorito",
                            tint = MaterialTheme.colorScheme.primary,modifier = Modifier.clickable { /*TODO*/ }
                        )

                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Agregar al carrito",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { /*TODO*/ }
                        )
                    }
                }
            }
        }
    }
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
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "",
                    tint = Color.Red
                )
            }
        }
    )
}
