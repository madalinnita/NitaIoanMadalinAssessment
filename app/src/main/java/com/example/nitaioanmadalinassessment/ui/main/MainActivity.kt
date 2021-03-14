package com.example.nitaioanmadalinassessment.ui.main

import android.R.attr.radius
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import com.example.nitaioanmadalinassessment.R
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
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

        setClickListenersForSecondToolbar()
        observeRecycleViewScrollState()
        observeShowArrowAsToolbar()
    }

    private fun setClickListenersForSecondToolbar() {
        custom_toolbar_scrolled_state.findViewById<ImageView>(R.id.nav_imageview)
            .setOnClickListener {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    custom_toolbar_scrolled_state.findViewById<ImageView>(R.id.nav_imageview)
                        .setImageDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.ic_baseline_menu_24
                            )
                        )
                } else {
                    custom_toolbar_scrolled_state.findViewById<ImageView>(R.id.nav_imageview)
                        .setImageDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.ic_baseline_arrow_back_24
                            )
                        )
                    drawerLayout.openDrawer(GravityCompat.START)
                }
            }

        val shapeAppearanceModel1 = ShapeAppearanceModel()
            .toBuilder()
            .setBottomRightCorner(CornerFamily.CUT, 32f)
            .build()

        val shapeAppearanceModel2 = ShapeAppearanceModel()
            .toBuilder()
            .setBottomRightCorner(CornerFamily.CUT, 32f)
            .build()

        val shapeDrawable1 = MaterialShapeDrawable(shapeAppearanceModel1)
        val shapeDrawable2 = MaterialShapeDrawable(shapeAppearanceModel2)
        ViewCompat.setBackground(custom_toolbar_scrolled_state, shapeDrawable1)
        ViewCompat.setBackground(custom_toolbar_back, shapeDrawable2)
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
                if (!query.isNullOrEmpty()) {
                    sharedViewModel.newCategory.value = query
                }
                if (!searchView.isIconified) {
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

    private fun observeRecycleViewScrollState() {
        sharedViewModel.recyclerViewIsScrolled.observe(this, {
            if (it) {
                custom_toolbar.visibility = View.GONE
                custom_toolbar_scrolled_state.visibility = View.VISIBLE
            } else {
                custom_toolbar.visibility = View.VISIBLE
                custom_toolbar_scrolled_state.visibility = View.GONE
            }
        })
    }

    private fun observeShowArrowAsToolbar() {
        sharedViewModel.showBackArrowAsToolbar.observe(this, {
            if (it) {
                custom_toolbar.visibility = View.GONE
                custom_toolbar_scrolled_state.visibility = View.GONE
                custom_toolbar_back.visibility = View.VISIBLE
                custom_toolbar_back.findViewById<ImageView>(R.id.back_imageview)
                    .setOnClickListener {
                        onBackPressed()
                    }
            } else {
                custom_toolbar.visibility = View.VISIBLE
                custom_toolbar_scrolled_state.visibility = View.GONE
                custom_toolbar_back.visibility = View.GONE
            }
        })
    }
}