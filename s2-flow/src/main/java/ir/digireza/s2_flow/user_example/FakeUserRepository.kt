package ir.digireza.s2_flow.user_example

import kotlinx.coroutines.delay

class FakeUserRepository : FakeApiService {

    private val users = List(50) { User("User$it") }
    private val pageSize = 3

    override suspend fun usersList(pageNumber: Int): List<User> {
        delay(1000)
        return users.drop(pageSize * pageNumber)
            .take(pageSize)
    }
}