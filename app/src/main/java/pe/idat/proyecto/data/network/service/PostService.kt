package pe.idat.proyecto.data.network.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.retrofitClient.RetrofitClient
import javax.inject.Inject

class PostService @Inject constructor(private val retrofitClient: RetrofitClient) {

    suspend fun getlista(): List<Producto>{
        return withContext(Dispatchers.IO){
            try {
                val response = retrofitClient.getLista()
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

}