package pe.idat.proyecto.data.repository

import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.service.PostService
import javax.inject.Inject

class PostRepository @Inject constructor(private val api: PostService) {

    suspend fun getList(): List<Producto>{
        return api.getlista()
    }

}