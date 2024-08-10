package pe.idat.proyecto.model

import androidx.annotation.DrawableRes
import pe.idat.proyecto.R

data class Favorito(
    val nombre: String,
    val precio: Double,
    @DrawableRes val imagenRes: Int
)

