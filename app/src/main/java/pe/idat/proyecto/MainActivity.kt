package pe.idat.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import pe.idat.proyecto.iu.ComprasScreen
import pe.idat.proyecto.iu.CuentaScreen
import pe.idat.proyecto.iu.FavoritosScreen
import pe.idat.proyecto.iu.HomeScreen
import pe.idat.proyecto.iu.LoginScreen
import pe.idat.proyecto.iu.PerfilScreen
import pe.idat.proyecto.iu.RecuperarScreen
import pe.idat.proyecto.navigation.Routes
import pe.idat.proyecto.ui.theme.ProyectoTheme

@AndroidEntryPoint
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
            composable(Routes.Login.route){
                val viewModel: SetupViewModel = hiltViewModel()
                LoginScreen(navController, viewModel = viewModel)}
            composable(Routes.Register.route){ CuentaScreen(navController)}
            composable(Routes.ForgotPassword.route){ RecuperarScreen(navController)}
            composable(Routes.Home.route){ val viewModel: SetupViewModel = hiltViewModel()
                HomeScreen(navController, viewModel) }
            composable(
                route = Routes.Favorite.route,
                arguments = listOf(navArgument("selectedIndex") { type = NavType.IntType })
            ) {
                val viewModel: SetupViewModel = hiltViewModel()
                FavoritosScreen(navController,viewModel)
            }
            composable(Routes.Perfil.route) { PerfilScreen(navController) }
            composable(Routes.Compras.route) { ComprasScreen(navController) }

        }
    }
}

