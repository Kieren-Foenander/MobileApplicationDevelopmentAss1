package kieren.foenander.propertylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PropertyDetailsViewModel: ViewModel() {
    val selectedProperty = MutableLiveData<Property>()
    val editedProperty = MutableLiveData<Property>()


}