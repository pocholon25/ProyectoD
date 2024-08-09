package pe.idat.proyecto.data.network.retrofitClient

import pe.idat.proyecto.data.network.request.LoginRequest
import pe.idat.proyecto.data.network.response.LoginResponse
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.response.Promocion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitClient {

    @GET("productos/lista")
    suspend fun getListaProductos(): Response<List<Producto>>

    @GET("promociones/lista")
    suspend fun getListaPromociones(): Response<List<Promocion>>

    @POST("usuarios/login")
    suspend fun getLogin(@Body request: LoginRequest): Response<LoginResponse>
}