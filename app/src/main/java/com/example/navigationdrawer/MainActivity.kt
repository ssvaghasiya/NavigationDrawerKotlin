//https://code.tutsplus.com/tutorials/how-to-code-a-navigation-drawer-in-an-android-app--cms-30263
//https://www.11zon.com/zon/android/how-to-use-same-navigation-drawer-in-all-activities.php

package com.example.navigationdrawer

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        loadFragment(Item2Fragement())
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.nav_item_one ->
            {
//                var intent = Intent(this,Item1::class.java)
//                startActivity(intent)
                loadFragment(Item2Fragement())
                Toast.makeText(this,"Item 1 Selected",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_item_two -> {
                loadFragment(Item3Fragment())
                Toast.makeText(this,"Item 2 Selected",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_item_three -> Toast.makeText(this,"Item 3 Selected",Toast.LENGTH_SHORT).show()
            R.id.nav_item_four -> Toast.makeText(this,"Item 4 Selected",Toast.LENGTH_SHORT).show()
            R.id.nav_item_five -> Toast.makeText(this,"Item 5 Selected",Toast.LENGTH_SHORT).show()
            R.id.nav_item_six -> Toast.makeText(this,"Item 6 Selected",Toast.LENGTH_SHORT).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun loadFragment(fragment: Fragment){
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
    }
}