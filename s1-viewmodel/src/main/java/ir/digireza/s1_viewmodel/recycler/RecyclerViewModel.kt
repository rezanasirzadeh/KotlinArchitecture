package ir.digireza.s1_viewmodel.recycler

import ir.digireza.s1_viewmodel.Utils
import androidx.lifecycle.ViewModel

class RecyclerViewModel : ViewModel() {

    val itemsList = Utils.getItems()

}