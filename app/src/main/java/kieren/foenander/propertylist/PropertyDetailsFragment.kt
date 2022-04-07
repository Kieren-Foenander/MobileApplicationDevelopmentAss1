package kieren.foenander.propertylist

import android.app.backup.BackupAgent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
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

        loadImage(mProperty.propertyImage, view.findViewById(R.id.property_image))
        val addressText = view?.findViewById<TextView>(R.id.address)
        addressText?.text = mProperty.address
        val priceText = view?.findViewById<TextView>(R.id.price)
        priceText?.text = "$" + mProperty.price.toString()
        val agentText = view?.findViewById<TextView>(R.id.agent)
        agentText?.text = mProperty.agent

        val doneButton = view?.findViewById<Button>(R.id.done)
        doneButton?.setOnClickListener{_->

            if (propertyChanged(mProperty, addressText?.text.toString(), priceText?.text.toString().drop(1).toInt(), agentText?.text.toString())){
                mProperty.address = addressText?.text.toString()
                //drop is used to take the "$" out to avoid number format exception
                mProperty.price = priceText?.text.toString().drop(1).toInt()
                mProperty.agent = agentText?.text.toString()
                mPropertyDetailsViewModel.editedProperty.value = mProperty
            }

        }
        return view
    }

    //function sets the image name it is given to the current image view
    fun loadImage(imageName: String, imageView: ImageView){

        val resourceId = this.resources.getIdentifier(imageName, "drawable", activity?.packageName)

        if (resourceId != 0){
            imageView.setImageResource(resourceId)
        }
    }

    fun validateFields(address: String, price: String, agent: String){

    }

    fun propertyChanged(originalProperty: Property, newAddress: String, newPrice: Int, newAgent: String): Boolean{
        return !(originalProperty.address == newAddress && originalProperty.price == newPrice && originalProperty.agent == newAgent)
    }

}