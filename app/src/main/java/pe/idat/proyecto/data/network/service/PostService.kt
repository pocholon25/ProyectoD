package pe.idat.proyecto.data.network.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.idat.proyecto.data.network.request.LoginRequest
import pe.idat.proyecto.data.network.response.LoginResponse
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.response.Promocion
import pe.idat.proyecto.data.network.retrofitClient.RetrofitClient
import javax.inject.Inject

class PostService @Inject constructor(private val retrofitClient: RetrofitClient) {

    suspend fun getListaProductos(): List<Producto>{
        return withContext(Dispatchers.IO){
            try {
                val response = retrofitClient.getListaProductos()
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!
                } else {
                    // Maneja el caso de respuesta no exitosa o cuerpo nulo
                    emptyList()
                }
            } catch (e: Exception) {
                // Maneja excepciones de red o cualquier otro error
                e.printStackTrace() // O usa un logger
                emptyList()
            }
        }
    }

    suspend fun getListaPromociones(): List<Promocion> {
        return withContext(Dispatchers.IO){
            try {
                val response = retrofitClient.getListaPromociones()
                if (response.isSuccessful && response.body()!=null){
                    response.body()!!
                }else{
                    emptyList()
                }
            }catch (e: Exception){
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
                    response.body()
                } else {
                    null // Maneja caso de error
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}