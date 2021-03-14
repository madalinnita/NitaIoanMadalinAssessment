package com.example.nitaioanmadalinassessment.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.ui.listfragment.ListFragmentArgs
import com.example.nitaioanmadalinassessment.ui.listfragment.ListFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var navToggle: ActionBarDrawerToggle
    private lateinit var searchView: SearchView

    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(custom_toolbar as Toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        setNavToggle()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_account -> it.title.toString().showToast()
                R.id.nav_logout -> it.title.toString().showToast()
                R.id.nav_about -> it.title.toString().showToast()
            }
            true
        }
    }

    private fun String.showToast() {
        Toast.makeText(applicationContext, this, Toast.LENGTH_SHORT).show()
    }

    private fun setNavToggle() {
        navToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            (custom_toolbar as Toolbar),
            R.string.nav_open,
            R.string.nav_close
        )
        drawerLayout.addDrawerListener(navToggle)
        navToggle.apply {
            syncState()
            drawerArrowDrawable.color = resources.getColor(R.color.black, null)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (navToggle.onOptionsItemSelected(item)) {
            return true
        }
        if (item.itemId == R.id.right_menu_search) {
            item.title.toString().showToast()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.right_menu, menu)

        val myActionMenuItem = menu!!.findItem(R.id.right_menu_search)
        searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty()) {
                    sharedViewModel.newCategory.value = query
                }
                if(!searchView.isIconified) {
                    searchView.isIconified = true;
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }
}