package pe.idat.proyecto.iu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import pe.idat.proyecto.R
import pe.idat.proyecto.SetupViewModel
import pe.idat.proyecto.navigation.Routes
import androidx.compose.runtime.livedata.observeAsState
import pe.idat.proyecto.EstadoLogin


@Composable
fun LoginScreen(navController: NavController, viewModel: SetupViewModel) {
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val loginState by viewModel.estadoLogin.observeAsState()
    val snackbarHostState = remember { SnackbarHostState() }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray), // Fondo gris claro para el círculo
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Cambia R.drawable.logo por tu recurso de logo
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop, // Recorta la imagen para llenar el círculo
                    modifier = Modifier.size(90.dp) // Ajusta el tamaño según sea necesario
                )
            }

            Text(
                text = "Login",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            )

            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = "password visibility")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Button(
                onClick = { viewModel.login(usuario,password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA))
            ) {
                Text(text = "Iniciar Sesion")
            }

            TextButton(
                onClick = { navController.navigate(Routes.ForgotPassword.route)},
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Olvidaste contraseña?")
            }

            TextButton(
                onClick = { navController.navigate(Routes.Register.route)}
            ) {
                Text(text = "Registrate aquí")
            }

            LaunchedEffect(loginState) {
                if(loginState is EstadoLogin.Success){
                    navController.navigate(Routes.Home.route){
                        popUpTo(Routes.Login.route){inclusive=true}
                    }
                }else if (loginState is EstadoLogin.Error){
                    val message = (loginState as EstadoLogin.Error).message
                    snackbarHostState.showSnackbar(
                        message=message,
                        actionLabel = "OK"
                    )
                }
            }
            SnackbarHost(hostState = snackbarHostState)
        }
    }
}
