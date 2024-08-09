package pe.idat.proyecto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.idat.proyecto.data.network.response.LoginResponse
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.response.Promocion
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
}

   /* private val _estadoLogin = MutableLiveData<EstadoLogin>()
    val estadoLogin: LiveData<EstadoLogin>get() = _estadoLogin

    fun login(usuario: String, password:String){
        viewModelScope.launch {
            if(usuario=="daniel" && password=="12345"){
                _estadoLogin.value = EstadoLogin.Success
            }else {
                _estadoLogin.value = EstadoLogin.Error("Invalidas las Credenciales")
            }
        }
    }
}
sealed class  EstadoLogin{
    object Success: EstadoLogin()
    data class Error (val message: String): EstadoLogin()
}*/