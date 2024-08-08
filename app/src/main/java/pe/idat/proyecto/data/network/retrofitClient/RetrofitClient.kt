package pe.idat.proyecto.data.network.retrofitClient

import pe.idat.proyecto.data.network.response.Producto
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitClient {

    @GET("lista")
    suspend fun getLista(): Response<List<Producto>>

}