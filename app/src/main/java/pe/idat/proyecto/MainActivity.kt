package pe.idat.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.idat.proyecto.iu.ComprasScreen
import pe.idat.proyecto.iu.CuentaScreen
import pe.idat.proyecto.iu.FavoritosScreen
import pe.idat.proyecto.iu.HomeScreen
import pe.idat.proyecto.iu.LoginScreen
import pe.idat.proyecto.iu.PerfilScreen
import pe.idat.proyecto.iu.RecuperarScreen
import pe.idat.proyecto.navigation.Routes
import pe.idat.proyecto.ui.theme.ProyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoTheme {
                ConfiguracionNavegacion()
            }
        }
    }

    @Composable
    fun ConfiguracionNavegacion() {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Routes.Login.route) {
            composable(Routes.Login.route){ LoginScreen(navController, viewModel = SetupViewModel())}
            composable(Routes.Register.route){ CuentaScreen(navController)}
            composable(Routes.ForgotPassword.route){ RecuperarScreen(navController)}
            composable(Routes.Home.route){ HomeScreen(navController)}
            composable(
                route = Routes.Favorite.route,
                arguments = listOf(navArgument("selectedIndex") { type = NavType.IntType })
            ) { backStackEntry ->
                val selectedIndex = backStackEntry.arguments?.getInt("selectedIndex") ?: 0
                FavoritosScreen(navController, selectedIndex)
            }
            composable(Routes.Perfil.route) { PerfilScreen(navController) }
            composable(Routes.Compras.route) { ComprasScreen(navController) }

        }
    }
}

