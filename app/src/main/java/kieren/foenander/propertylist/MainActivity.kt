package kieren.foenander.propertylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mPropertyDetailsViewModel: PropertyDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPropertyDetailsViewModel = ViewModelProvider(this).get(PropertyDetailsViewModel::class.java)

        mPropertyDetailsViewModel.selectedProperty.observe(this){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PropertyDetailsFragment.newInstance())
                .addToBackStack("list_fragment")
                .commit()
        }
        mPropertyDetailsViewModel.editedProperty.observe(this){
            supportFragmentManager.popBackStack()
        }
        if(savedInstanceState == null){
            loadFragment(PropertyListFragment.newInstance())
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    override fun onBackPressed() {
        val currentFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)

        val frag = currentFragment as PropertyDetailsFragment
        frag.checkDone(true)
    }
}