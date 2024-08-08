package pe.idat.proyecto.iu

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import pe.idat.proyecto.R
import pe.idat.proyecto.SetupViewModel
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.navigation.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController,viewModel: SetupViewModel) {

    val productos by viewModel.lista.collectAsState()

    Scaffold(
        topBar = { HomeCabecera() },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(productos) { product ->
                        ProductCardRow(product.image)
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(productos) { product ->
                        ProductCardColumn(product)
                    }
                }
            }
        },
        bottomBar = { HomePie(navController) },
        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
    )
}

@Composable
fun HomePie(navController: NavController) {
    val items = listOf("Home", "Favoritos", "Perfil", "Compras")
    val iconos = listOf(
        Icons.Filled.Home,
        Icons.Filled.Favorite,
        Icons.Filled.Person,
        Icons.Filled.ShoppingCart
    )

    // Obtener la ruta actual desde el NavController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determinar el índice basado en la ruta actual
    val itemSeleccionado = when (currentRoute) {
        Routes.Home.route -> 0
        Routes.Favorite.route -> 1
        Routes.Perfil.route -> 2
        Routes.Compras.route -> 3

        else -> 0
    }

    BottomAppBar(
        containerColor = colorResource(id = R.color.teal_200),
        contentColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = itemSeleccionado == index,
                onClick = {
                    // Solo navega si el índice seleccionado no es el actual
                    if (itemSeleccionado != index) {
                        when (item) {
                            "Home" -> {
                                // Navegar a la pantalla de inicio y limpiar el stack
                                navController.navigate(Routes.Home.route) {
                                    popUpTo(Routes.Home.route) { inclusive = true }
                                }
                            }
                            "Favoritos" -> {
                                navController.navigate(Routes.Favorite.createRoute(index))
                            }
                            "Perfil" -> {
                                navController.navigate(Routes.Perfil.route)
                            }
                            "Compras" -> {
                                navController.navigate(Routes.Compras.route)
                            }
                            }
                    }
                },
                icon = {
                    Icon(
                        imageVector = iconos[index],
                        contentDescription = "",
                        tint = Color.Red
                    )
                },
                label = {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCabecera() {
    TopAppBar(
        title = {
            Text(
                text = "Fashion Hub",
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
            IconButton(onClick = { /*TODO*/ }) {
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

@Composable
fun ProductCardRow(imageUrl: String) {
    val fullImageUrl = "http://10.0.2.2:8080/productos/uploads/$imageUrl"
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = fullImageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ProductCardColumn(product: Producto) {
    val fullImageUrl = "http://10.0.2.2:8080/productos/uploads/${product.image}"
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
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
                painter = rememberAsyncImagePainter(model = fullImageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.nombre,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$${product.precio}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favorite",
                            tint = Color.Red
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Add to Cart",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }
}
