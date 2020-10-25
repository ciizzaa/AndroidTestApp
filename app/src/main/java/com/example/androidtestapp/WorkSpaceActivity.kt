package com.example.androidtestapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtestapp.ui.AboutFragment
import com.example.androidtestapp.ui.ContactsFragment
import com.example.androidtestapp.ui.ProfileFragment

class WorkSpaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_space)
        val navigation = findViewById<BottomNavigationView>(R.id.bottomNavMenu)

        val profileFragment = ProfileFragment()
        val contactsFragment = ContactsFragment()
        val aboutFragment = AboutFragment()

        val fragmentChanger = supportFragmentManager.beginTransaction()
        fragmentChanger.replace(R.id.frameLayout, profileFragment)
        fragmentChanger.commit()

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profileMenuItem -> {
                    val fragmentChanger = supportFragmentManager.beginTransaction()
                    fragmentChanger.replace(R.id.frameLayout, profileFragment)
                    fragmentChanger.commit()
                    true

                }
                R.id.contactsMenuItem -> {
                    val fragmentChanger = supportFragmentManager.beginTransaction()
                    fragmentChanger.replace(R.id.frameLayout, contactsFragment)
                    fragmentChanger.commit()
                    true

                }
                R.id.aboutMenuItem -> {
                    val fragmentChanger = supportFragmentManager.beginTransaction()
                    fragmentChanger.replace(R.id.frameLayout, aboutFragment)
                    fragmentChanger.commit()
                    true

                }
                else -> false
            }
        }
    }
}