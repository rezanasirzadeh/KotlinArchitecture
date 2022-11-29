package ir.digireza.s1_project.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.digireza.s1_project.R
import ir.digireza.s1_project.databinding.FragmentSplashBinding
import ir.digireza.s1_project.utils.StoreUserData
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var storeUserData: StoreUserData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set delay
        lifecycle.coroutineScope.launchWhenCreated {
            delay(2000)
            //Check user token
            storeUserData.getUserToken().collect {
                if (it.isEmpty()) {
                    findNavController().navigate(R.id.actionSplashToRegister)
                } else {
                    findNavController().navigate(R.id.actionToHome)
                }
            }
        }
    }
}