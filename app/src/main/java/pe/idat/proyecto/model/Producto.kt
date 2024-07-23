package pe.idat.proyecto.model

import androidx.annotation.DrawableRes
import pe.idat.proyecto.R

data class Producto(
    val nombre: String,
    val precio : Double,
    @DrawableRes val imagenRes: Int
)
val productList = listOf(
    Producto("Producto 1", 29.99, R.drawable.img1),
    Producto("Producto 2", 39.99, R.drawable.img2),
    Producto("Producto 3", 49.99, R.drawable.img3),
    Producto("Producto 4", 59.99, R.drawable.img4),
    Producto("Producto 5", 69.99, R.drawable.img5),
    Producto("Producto 6", 79.99, R.drawable.img6),
    Producto("Producto 7", 89.99, R.drawable.img7),
    Producto("Producto 8", 99.99, R.drawable.img8),
    Producto("Producto 9", 109.99, R.drawable.img9),
    Producto("Producto 10", 119.99, R.drawable.img10)
)
