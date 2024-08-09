package pe.idat.proyecto.data.repository

import pe.idat.proyecto.data.network.response.LoginResponse
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.response.Promocion
import pe.idat.proyecto.data.network.service.PostService
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
}