package ir.digireza.s2_flow.user_example

interface FakeApiService {
    suspend fun usersList(pageNumber: Int): List<User>
}