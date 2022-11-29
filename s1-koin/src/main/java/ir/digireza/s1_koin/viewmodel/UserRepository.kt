package ir.digireza.s1_koin.viewmodel

class UserRepository(private val family: String) : UserInfo {
    override fun userName(): String {
        return "Reza $family"
    }
}