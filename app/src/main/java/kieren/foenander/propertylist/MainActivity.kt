package kieren.foenander.propertylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mPropertyDetailsViewModel: PropertyDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPropertyDetailsViewModel = ViewModelProvider(this).get(PropertyDetailsViewModel::class.java)

        mPropertyDetailsViewModel.selectedProperty.observe(this){
            supportFragmentManager.beginTransaction().replace(R.id.container, PropertyDetailsFragment.newInstance()).commit()
        }

        if(savedInstanceState == null){
            val fragment = PropertyListFragment.newInstance()

            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }


    }
}