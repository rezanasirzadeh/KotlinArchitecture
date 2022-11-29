package ir.digireza.s1_koin.interfaces

class CarImpl : Car, Owner {
    override fun model(): String {
        return "BMW 730 li"
    }

    override fun ownerName(): String {
        return "Reza Nasirzadeh"
    }
}

class BMW(private val carImpl: CarImpl) {
    fun showCarInfo(): String {
        return "${carImpl.ownerName()} : ${carImpl.model()}"
    }
}