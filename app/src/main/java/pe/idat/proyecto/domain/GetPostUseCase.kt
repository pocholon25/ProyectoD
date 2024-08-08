package pe.idat.proyecto.domain

import pe.idat.proyecto.data.network.response.Producto
import pe.idat.proyecto.data.repository.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend operator fun invoke(): List<Producto> {
        return postRepository.getList()
    }

}