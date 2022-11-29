package ir.digireza.s1_viewmodel.recycler

import ir.digireza.s1_viewmodel.R
import ir.digireza.s1_viewmodel.databinding.ActivityMainBinding
import ir.digireza.s1_viewmodel.databinding.ActivityRecyclerBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

class RecyclerActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRecyclerBinding

    //Other
    private val viewModel: RecyclerViewModel by viewModels()
    private val itemsAdapter by lazy { ItemsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Get data
            itemsAdapter.differ.submitList(viewModel.itemsList)
            //RecyclerView
            itemsRecycler.apply {
                layoutManager = LinearLayoutManager(this@RecyclerActivity)
                adapter = itemsAdapter
            }
        }
    }
}