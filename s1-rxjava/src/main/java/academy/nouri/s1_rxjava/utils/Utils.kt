package ir.digireza.s1_rxjava.utils

object Utils {
    //Users
    fun usersList(): List<Users> {
        val users = ArrayList<Users>()
        users.add(Users(1, "Reza"))
        users.add(Users(2, "Niloofar"))
        users.add(Users(3, "Ali"))
        return users
    }
}