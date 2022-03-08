package com.example.fitpeo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fitpeo.Fragments.OfficeFragment
import com.example.fitpeo.Fragments.Pass_Fragment
import com.example.fitpeo.Fragments.PeopleFragment
import com.example.fitpeo.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pass = Pass_Fragment()
        val office = OfficeFragment()
        val people = PeopleFragment()
        setCurrentFragment(pass)
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.pass -> {
                    setCurrentFragment(pass)
                    true
                }
                R.id.office -> {

                    setCurrentFragment(office)
                    true
                }
                R.id.people -> {
                    setCurrentFragment(people)
                    true
                }
                else -> false
            }

        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.fragment, fragment)
        frag.commit()

    }
}