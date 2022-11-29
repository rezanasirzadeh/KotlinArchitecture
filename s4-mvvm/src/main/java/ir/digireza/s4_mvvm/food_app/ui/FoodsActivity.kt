package ir.digireza.s4_mvvm.food_app.ui

import ir.digireza.s4_mvvm.R
import ir.digireza.s4_mvvm.databinding.ActivityFoodsBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodsActivity : AppCompatActivity() {
    //Binding
    private var _binding: ActivityFoodsBinding? = null
    private val binding get() = _binding!!

    //Other
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFoodsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Nav controller
        navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        //Bottom nav
        binding.bottomNav.setupWithNavController(navHost.navController)
        //Hide bottom nav
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.foodDetailFragment) {
                binding.bottomNav.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navHost.navController.navigateUp() || super.onNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}