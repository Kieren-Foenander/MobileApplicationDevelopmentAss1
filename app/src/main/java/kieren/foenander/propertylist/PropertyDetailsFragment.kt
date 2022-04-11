package kieren.foenander.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
        val view = inflater.inflate(R.layout.fragment_details, container, false)

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
            checkDone(false)
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

    //compares the original property with the new property value to determine if it has changed
    fun propertyChanged(originalProperty: Property, newAddress: String, newPrice: Int, newAgent: String): Boolean{
        return !(originalProperty.address == newAddress && originalProperty.price == newPrice && originalProperty.agent == newAgent)
    }

    //completes all validation checks whether list needs to update or confirm changes
    fun checkDone(backButtonPressed: Boolean){
        val addressText = view?.findViewById<TextView>(R.id.address)
        val priceText = view?.findViewById<TextView>(R.id.price)
        val agentText = view?.findViewById<TextView>(R.id.agent)

        if (!backButtonPressed){
            //validates all fields before applying any changes
            if (validateFields(addressText?.text.toString(), priceText?.text.toString(), agentText?.text.toString())){
                //checks if property changed and if not sets edited property to null which defaults back to list view
                if (propertyChanged(mProperty, addressText?.text.toString(), priceText?.text.toString().drop(1).toInt(), agentText?.text.toString())){
                    mProperty.address = addressText?.text.toString()
                    //drop is used to take the "$" out to avoid number format exception
                    mProperty.price = priceText?.text.toString().drop(1).toInt()
                    mProperty.agent = agentText?.text.toString()
                    mPropertyDetailsViewModel.editedProperty.value = mProperty
                }
                else{
                    mPropertyDetailsViewModel.editedProperty.value = null
                }
            }
        } else{
            if (propertyChanged(mProperty, addressText?.text.toString(), priceText?.text.toString().drop(1).toInt(), agentText?.text.toString())) {
                val dialogBuilder =
                    AlertDialog.Builder(requireActivity())
                        .setMessage("Do you wish to Discard changes")
                        .setPositiveButton("YES") { _, _ ->
                            mPropertyDetailsViewModel.editedProperty.value = null
                        }
                        .setNegativeButton("Cancel", null)

                val alert = dialogBuilder.create()
                alert.setTitle("Warning")
                alert.show()

            }
            else{
                mPropertyDetailsViewModel.editedProperty.value = null
            }
        }
    }

    //validation checks on data
    fun validateFields(address: String, price: String, agent: String): Boolean{
        return if (address == "" || price == "" || agent == ""){

            val dialogBuilder = AlertDialog.Builder(requireActivity())
                .setMessage("fields cannot be empty")
                .setPositiveButton("OK", null)

            val alert = dialogBuilder.create()
            alert.setTitle("Error")
            alert.show()

            false
        } else if (!price.matches(Regex("\\$?[0-9]+"))){
            val dialogBuilder = AlertDialog.Builder(requireActivity())
                .setMessage("price field must be a number")
                .setPositiveButton("OK", null)

            val alert = dialogBuilder.create()
            alert.setTitle("Error")
            alert.show()
            false
        } else if (price[0] != '$'){
            val dialogBuilder = AlertDialog.Builder(requireActivity())
                .setMessage("price field must start with a '$'")
                .setPositiveButton("OK", null)

            val alert = dialogBuilder.create()
            alert.setTitle("Error")
            alert.show()
            false
        }
        else{
            true
        }
    }
}