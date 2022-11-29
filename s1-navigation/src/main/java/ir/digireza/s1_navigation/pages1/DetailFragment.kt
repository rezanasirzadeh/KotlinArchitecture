package ir.digireza.s1_navigation.pages1

import ir.digireza.s1_navigation.databinding.FragmentDetailBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    //Other
    private val args: DetailFragmentArgs by navArgs()
    private var info = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        info = args.bundleInfo

        binding.infoTxt.text = info
    }
}