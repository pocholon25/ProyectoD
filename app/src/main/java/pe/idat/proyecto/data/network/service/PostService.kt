package pe.idat.proyecto.data.network.service

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import pe.idat.proyecto.auth.UserCrendentialsManager
import pe.idat.proyecto.data.network.request.LoginRequest
import pe.idat.proyecto.data.network.request.RegisterRequest
import pe.idat.proyecto.data.network.response.LoginResponse
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.response.Promocion
import pe.idat.proyecto.data.network.response.RegisterResponse
import pe.idat.proyecto.data.network.retrofitClient.RetrofitClient
import retrofit2.Response
import javax.inject.Inject

class PostService @Inject constructor(private val retrofitClient: RetrofitClient) {

    suspend fun getListaProductos(): List<Producto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofitClient.getListaProductos()
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    suspend fun getListaPromociones(): List<Promocion> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofitClient.getListaPromociones()
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    suspend fun getLogin(nombre: String, password: String): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val request = LoginRequest(nombre, password)
                val response = retrofitClient.getLogin(request)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.let {
                        loginUser(nombre, password)
                    }
                    loginResponse
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    fun loginUser(nombre: String, password: String) {
        UserCrendentialsManager.setCredentials(nombre, password)
    }

    fun logoutUser() {
        UserCrendentialsManager.clearCredentials()
    }

    // PostService.kt
    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofitClient.getRegister(registerRequest)
                if (response.isSuccessful) {
                    Log.d("PostService", "Register Response: ${response.body()}")
                } else {
                    Log.e("PostService", "Error Registering User: ${response.code()} - ${response.errorBody()?.string()}")
                }
                response // Devolver el objeto response completo
            } catch (e: Exception) {
                Log.e("PostService", "Exception during registration: ${e.message}")
                e.printStackTrace()
                null // Devolver null en caso de excepción
            }
        }
    }

}