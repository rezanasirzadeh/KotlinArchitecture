package ir.digireza.s1_project.repository

import ir.digireza.s1_project.api.ApiServices
import ir.digireza.s1_project.models.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: ApiServices) {
    suspend fun registerUser(body: BodyRegister) = api.registerUser(body)
}