package pe.idat.proyecto

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.idat.proyecto.data.network.request.RegisterRequest
import pe.idat.proyecto.data.network.response.LoginResponse
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.response.Promocion
import pe.idat.proyecto.data.network.response.RegisterResponse
import pe.idat.proyecto.domain.GetPostUseCase
import javax.inject.Inject

@HiltViewModel
class SetupViewModel  @Inject constructor(private val getPostUseCase: GetPostUseCase) : ViewModel() {

    private val _listaProductos = MutableStateFlow<List<Producto>>(emptyList())
    val listaProductos: StateFlow<List<Producto>> get() = _listaProductos

    private val _listaPromociones = MutableStateFlow<List<Promocion>>(emptyList())
    val listaPromociones: StateFlow<List<Promocion>> get() = _listaPromociones

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> get() = _loginResponse

    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse: StateFlow<RegisterResponse?> get() = _registerResponse

    init {
        viewModelScope.launch {
            _listaProductos.value = getPostUseCase.getProductos()
            _listaPromociones.value = getPostUseCase.getPromociones()
        }
    }

    fun login(nombre: String, password: String) {
        viewModelScope.launch {
            _loginResponse.value = getPostUseCase.getLogin(nombre, password)
        }
    }

    // SetupViewModel.kt
    fun registerUser(nombre: String, email: String, celular: String, password: String) {
        viewModelScope.launch {
            val request = RegisterRequest(nombre, email, celular, password)
            try {
                val response = getPostUseCase.registerUser(request)
                if (response != null && response.isSuccessful) {  // Verificamos si la respuesta no es null y si es exitosa
                    val registerResponse = response.body()
                    if (registerResponse != null) {
                        _registerResponse.value = registerResponse
                        Log.d("SetupViewModel", "Register Response: $registerResponse")
                    } else {
                        _registerResponse.value = null
                        Log.e("SetupViewModel", "Register Error: Response body is null")
                    }
                } else {
                    _registerResponse.value = null
                    Log.e("SetupViewModel", "Register Error: ${response?.errorBody()?.string() ?: "Unknown error, response was null"}")
                }
            } catch (e: Exception) {
                _registerResponse.value = null
                Log.e("SetupViewModel", "Register Exception: ${e.message}")
            }
        }
    }


    fun logoutUser() {
        getPostUseCase.logoutUser()
    }

}