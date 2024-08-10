package pe.idat.proyecto.data.repository

import pe.idat.proyecto.data.network.request.RegisterRequest
import pe.idat.proyecto.data.network.response.LoginResponse
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.response.Promocion
import pe.idat.proyecto.data.network.response.RegisterResponse
import pe.idat.proyecto.data.network.service.PostService
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(private val postService: PostService) {

    suspend fun getListaProductos(): List<Producto>{
        return postService.getListaProductos()
    }

    suspend fun getListaPromociones(): List<Promocion>{
        return postService.getListaPromociones()
    }

    suspend fun getLogin(nombre: String, password: String): LoginResponse? {
        return postService.getLogin(nombre, password)
    }

    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse>? {
        return postService.registerUser(registerRequest)
    }

    fun logoutUser(){
        postService.logoutUser()
    }
}