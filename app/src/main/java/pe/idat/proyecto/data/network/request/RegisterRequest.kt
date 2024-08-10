package pe.idat.proyecto.data.network.request

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val celular: String,
    val password: String
)
