package ir.digireza.s3_mvp.retrofit.ui

import ir.digireza.s3_mvp.R
import ir.digireza.s3_mvp.databinding.ActivityFoodBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityFoodBinding

    //Other
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Nav controller
        navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        //BottomNav
        binding.bottomNav.setupWithNavController(navHost.navController)
        //Show bottom nav
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.detailFragment) {
                binding.bottomNav.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navHost.navController.navigateUp() || super.onNavigateUp()
    }
}