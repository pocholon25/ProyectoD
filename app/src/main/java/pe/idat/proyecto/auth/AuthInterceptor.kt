package pe.idat.proyecto.auth

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val credentials = UserCrendentialsManager.getCredentials()
        val requestBuilder = originalRequest.newBuilder()

        credentials?.let {
            val authHeader = getBasicAuthHeader(it.nombre, it.password)
            requestBuilder.addHeader("Authorization", authHeader)
        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun getBasicAuthHeader(nombre: String, password: String): String {
        val credentials ="$nombre:$password"
        return "Basic ${Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)}"
    }
}