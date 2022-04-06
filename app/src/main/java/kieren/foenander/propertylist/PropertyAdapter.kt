package kieren.foenander.propertylist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

class PropertyAdapter(var properties: List<Property>): RecyclerView.Adapter<PropertyAdapter.ViewHolder>(){


        private lateinit var mPropertyDetailsModel: PropertyDetailsViewModel

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

            val viewModelStoreOwner = parent.context as ViewModelStoreOwner

            mPropertyDetailsModel = ViewModelProvider(viewModelStoreOwner).get(PropertyDetailsViewModel::class.java)

            return ViewHolder(view)

        }

        override fun getItemCount() = properties.size

        override fun onBindViewHolder(propertyHolder: ViewHolder, position: Int) {

            val property = properties[position]
            propertyHolder.bind(property)
        }

        inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {

            lateinit var property: Property

            init {
                itemView.setOnClickListener(this)

            }

            override fun onClick(v: View) {
                Log.d("PropertyAdapter", property.address + "selected")

                mPropertyDetailsModel.selectedProperty.value = property

            }

            fun bind(property: Property) {
                this.property = property

                val addressView: TextView = view.findViewById(R.id.address)

                addressView.text = property.address

            }
        }
    }
