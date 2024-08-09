package pe.idat.proyecto.domain

import pe.idat.proyecto.data.network.response.LoginResponse
import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.network.response.Promocion
import pe.idat.proyecto.data.repository.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend fun getProductos(): List<Producto> {
        return postRepository.getListaProductos()
    }

    suspend fun getPromociones(): List<Promocion>{
        return postRepository.getListaPromociones()
    }

    suspend fun getLogin(nombre: String, password: String): LoginResponse? {
        return postRepository.getLogin(nombre, password)
    }


}