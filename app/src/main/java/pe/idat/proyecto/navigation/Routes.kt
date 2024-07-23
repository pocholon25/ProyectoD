package pe.idat.proyecto.navigation

sealed class Routes(val route: String) {

    object Login : Routes("loginScreen")
    object Register : Routes("cuentaScreen")
    object ForgotPassword : Routes("recuperarScreen")
    object Home : Routes("homeScreen")
    object Favorite : Routes("favoritosScreen/{selectedIndex}") {
        fun createRoute(selectedIndex: Int) = "favoritosScreen/$selectedIndex"
    }
    object Perfil : Routes("perfilScreen")
    object Compras : Routes("comprasScreen")

}