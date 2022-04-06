package kieren.foenander.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PropertyListFragment: Fragment() {

    companion object{
        fun newInstance() = PropertyListFragment()

    }
    val mPropertyArray: ArrayList<Property> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        mPropertyArray.add(Property("1", "360 Pier Point Road, Cairns, 4870", 200000, "Kieren Foenander"))
        mPropertyArray.add(Property("2", "250 Sheridan St, Cairns, 4870", 350000, "Bob Bobber"))
        mPropertyArray.add(Property("3", "86 Taylor St, Trinity Beach, 4879", 800000, "Terry Crews"))
        mPropertyArray.add(Property("4", "17 Martin St, Cairns, 4870", 550000, "Karen Smith"))
        mPropertyArray.add(Property("5", "715 Mulgrave Road, Earlville, 4898", 400000, "Earl Earlington"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View{
        val recyclerView = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = PropertyAdapter(mPropertyArray)
        return recyclerView

    }

}