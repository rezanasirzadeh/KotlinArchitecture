package ir.digireza.s1_koin.simple

class UserInfo(private val user: User) {

    fun userName() = "${user.name} ${user.family}"

    fun siteAddress() = user.site

}