package ir.digireza.s2_rxjava.utils

object Utils {
    //Users
    fun usersList(): List<User> {
        val users = ArrayList<User>()
        users.add(User(1, "Reza"))
        users.add(User(2, "Niloofar"))
        users.add(User(3, "Ali"))
        users.add(User(4, "Mohsen"))
        users.add(User(5, "Morvarid"))
        return users
    }

    fun usersList2(): List<User> {
        val users = ArrayList<User>()
        users.add(User(1, "Reza"))
        users.add(User(2, "Niloofar"))
        users.add(User(3, "Ali"))
        users.add(User(4, "Mohsen2"))
        users.add(User(5, "Morvarid"))
        return users
    }

    fun usersListWithDuplicate(): List<User> {
        val users = ArrayList<User>()
        users.add(User(1, "Reza"))
        users.add(User(2, "Niloofar"))
        users.add(User(3, "Ali"))
        users.add(User(4, "Mohsen"))
        users.add(User(1, "Naser"))
        users.add(User(5, "Morvarid"))
        return users
    }

    fun maleList(): List<User> {
        val users = ArrayList<User>()
        users.add(User(1, "Reza", "Male"))
        users.add(User(2, "Peyman", "Male"))
        users.add(User(3, "Ali", "Male"))
        users.add(User(4, "Mohsen", "Male"))
        return users
    }

    fun femaleList(): List<User> {
        val users = ArrayList<User>()
        users.add(User(1, "Nooshin", "Female"))
        users.add(User(2, "Morvarid", "Female"))
        users.add(User(3, "Narges", "Female"))
        users.add(User(4, "Baran", "Female"))
        users.add(User(5, "Maryam", "Female"))
        return users
    }
}