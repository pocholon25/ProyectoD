package pe.idat.proyecto.model

import androidx.annotation.DrawableRes
import pe.idat.proyecto.R

data class Favorito(
    val nombre: String,
    val precio: Double,
    @DrawableRes val imagenRes: Int
)

/*
val favoriteList = listOf(
    Producto("Producto 1", 29.99, R.drawable.img11),
    Producto("Producto 2", 39.99, R.drawable.img12),
    Producto("Producto 3", 49.99, R.drawable.img13),
    Producto("Producto 4", 59.99, R.drawable.img14),
    Producto("Producto 5", 69.99, R.drawable.img15),
    Producto("Producto 6", 79.99, R.drawable.img16),
    Producto("Producto 7", 89.99, R.drawable.img17),
    Producto("Producto 8", 99.99, R.drawable.img18),
    Producto("Producto 9", 109.99, R.drawable.img19),
    Producto("Producto 10", 119.99, R.drawable.img20)
)*/
