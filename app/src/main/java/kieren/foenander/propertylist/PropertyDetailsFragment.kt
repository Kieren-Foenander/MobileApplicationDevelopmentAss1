package kieren.foenander.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class PropertyDetailsFragment: Fragment(){
    companion object{
        fun newInstance() = PropertyDetailsFragment()
    }

    private lateinit var mPropertyDetailsViewModel: PropertyDetailsViewModel
    private lateinit var mProperty: Property

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        var view = inflater.inflate(R.layout.fragment_details, container, false)

        val context = activity as ViewModelStoreOwner
        mPropertyDetailsViewModel = ViewModelProvider(context).get(PropertyDetailsViewModel::class.java)

        mProperty = mPropertyDetailsViewModel.selectedProperty.value!!

        val addressText = view?.findViewById<TextView>(R.id.address)
        addressText?.text = mProperty.address


        return view
    }

}