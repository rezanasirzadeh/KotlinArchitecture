package ir.digireza.s2_flow.user_example

import ir.digireza.s2_flow.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class UserExampleActivity : AppCompatActivity() {

    private val TAG = "UserExampleLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_example)

        val repository = FakeUserRepository()
        val users = allUsers(repository)

        lifecycleScope.launchWhenCreated {
            val user = users.first {
                Log.e(TAG, "Checking $it")
                delay(1000)
                it.name == "User12"
            }
            Log.e(TAG, "User $user")
        }
    }

    private fun allUsers(api: FakeApiService): Flow<User> = flow {
        var page = 0

        do {
            Log.e(TAG, "fetching page $page")
            val users = api.usersList(page++)
            emitAll(users.asFlow())
        } while (users.isNotEmpty())
    }
}