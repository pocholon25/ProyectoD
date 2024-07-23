package pe.idat.proyecto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SetupViewModel : ViewModel(){

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