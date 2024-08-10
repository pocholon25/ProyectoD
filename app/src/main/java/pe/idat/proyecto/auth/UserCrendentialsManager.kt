package pe.idat.proyecto.auth

data class  UserCredentials(val nombre: String, val password: String)

object UserCrendentialsManager {
    private var currentCredentials: UserCredentials? = null

    fun setCredentials(nombre: String, password: String) {
        currentCredentials = UserCredentials(nombre, password)
    }

    fun clearCredentials() {
        currentCredentials = null
    }

    fun getCredentials(): UserCredentials? {
        return currentCredentials
    }
}