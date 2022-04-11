package kieren.foenander.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PropertyListFragment: Fragment() {

    companion object{
        fun newInstance() = PropertyListFragment()
    }

    private val mPropertyArray: ArrayList<Property> = ArrayList()
    private lateinit var mPropertyDetailsViewModel: PropertyDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        mPropertyArray.add(Property("1", "360 Pier Point Road, Cairns, 4870", 200000, "Kieren Foenander", "house1"))
        mPropertyArray.add(Property("2", "250 Sheridan St, Cairns, 4870", 350000, "Bob Bobber", "house2"))
        mPropertyArray.add(Property("3", "86 Taylor St, Trinity Beach, 4879", 800000, "Terry Crews", "house3"))
        mPropertyArray.add(Property("4", "17 Martin St, Cairns, 4870", 550000, "Karen Smith", "house4"))
        mPropertyArray.add(Property("5", "715 Mulgrave Road, Earlville, 4898", 400000, "Earl Earlington", "house5"))
        mPropertyArray.add(Property("6", "451 Yeet Street, Pimpama, 4209", 650000, "Rick Astley", "house6"))

        val context = activity as ViewModelStoreOwner
        mPropertyDetailsViewModel = ViewModelProvider(context).get(PropertyDetailsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View{
        val recyclerView = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        var editedProperty = mPropertyDetailsViewModel.editedProperty.value

        if (editedProperty != null){
            for (i in 0 until (mPropertyArray.size -1)){
                if (mPropertyArray[i].id == editedProperty.id){
                    mPropertyArray[i] = editedProperty
                    break
                }
            }
        }

        recyclerView.adapter = PropertyAdapter(mPropertyArray)
        return recyclerView
    }
}