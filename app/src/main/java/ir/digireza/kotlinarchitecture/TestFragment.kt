package ir.digireza.kotlinarchitecture

import ir.digireza.kotlinarchitecture.databinding.FragmentTestBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TestFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentTestBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtFragment.text = "Nasirzadeh"
    }
}