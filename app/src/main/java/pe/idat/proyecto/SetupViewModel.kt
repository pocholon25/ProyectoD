package pe.idat.proyecto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.domain.GetPostUseCase
import javax.inject.Inject

@HiltViewModel
class SetupViewModel  @Inject constructor(private val postUseCase: GetPostUseCase) : ViewModel(){

    private val _lista= MutableStateFlow<List<Producto>>(emptyList())
    val lista: StateFlow<List<Producto>>get() = _lista

    init {
        viewModelScope.launch {
            _lista.value=postUseCase()
        }
    }


    private val _estadoLogin = MutableLiveData<EstadoLogin>()
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
}