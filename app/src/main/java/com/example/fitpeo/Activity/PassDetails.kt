package com.example.fitpeo.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.location.Geocoder
import android.location.Location
import java.lang.NullPointerException
import java.util.*
import android.location.Criteria

import android.location.LocationManager
import android.net.Uri
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.fitpeo.R
import java.io.IOException


class PassDetails : AppCompatActivity() {

    lateinit var schedule1 : TextView
    lateinit var cancel : ImageView
    lateinit var listItems: Array<String>
    lateinit var dropdown : ImageButton
    lateinit var checkedItems: BooleanArray
    lateinit var viewonmap : TextView
    var lat : Double = 0.0
    var lng : Double = 0.0
    private var addressField: TextView? = null
    private var locationManager: LocationManager? = null
    private var provider: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_details)
       initialise()
        showcity()
        cancel.setOnClickListener { view ->
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    dropdown = findViewById(R.id.dropdown)

        dropdown.setOnClickListener{view ->
            setdropdown()

        }



          viewonmap.setOnClickListener{view->
              openmap()
          }
        }


    private fun showcity() {
        val criteria = Criteria()
        provider = locationManager!!.getBestProvider(criteria, false)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askpermission()

        }
        val location: Location? = provider?.let { locationManager!!.getLastKnownLocation(it) }
        setlocation(location)
    }

    private fun initialise() {
        addressField = findViewById(R.id.location)
        schedule1 = findViewById(R.id.schedule)
        viewonmap = findViewById(R.id.viewonmap)
        cancel = findViewById(R.id.cancel)
        listItems = getResources().getStringArray(R.array.days_item);
        checkedItems = BooleanArray(listItems.size)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
    }


    private fun openmap() {

        val gmmIntentUri = Uri.parse("geo:"+lat+","+lng)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
        }
    }


    private fun askpermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                } else -> {
                // No location access granted.
                Toast.makeText(this,"Kindly provide Location permissions",Toast.LENGTH_LONG).show()
            }

            }

        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }



    private fun setdropdown() {

        val mBuilder : AlertDialog.Builder = AlertDialog.Builder(this)
        mBuilder.setTitle("Select our Work Day's")
        mBuilder.setMultiChoiceItems(listItems, checkedItems) { dialog, which, isChecked ->

            checkedItems[which] = isChecked

            val currentItem = listItems[which]

        }

        mBuilder.setPositiveButton("OK") { dialog, which ->
            schedule1.text = ""
            for (i in checkedItems.indices) {
                val checked = checkedItems[i]
                if (checked) {
                    schedule1.text = schedule1.text.toString() + listItems[i] + ","
                }
            }
        }
        mBuilder.setNeutralButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = mBuilder.create()
        dialog.show()
    }


    private fun setlocation(location: Location?) {
        if (location != null) {

            onLocationChanged(location)
        } else {

        }
    }



    fun onLocationChanged(location: Location) {

         lat = location.latitude
         lng = location.longitude
        val geoCoder = Geocoder(this, Locale.getDefault())
        val address : List<Address>

        try {
            address = geoCoder.getFromLocation(lat, lng, 1)
            val maxLines = address.get(0).locality

            addressField?.setText(maxLines)
            println("Hello"+maxLines+lat+lng)
        } catch (e: IOException) {

        } catch (e: NullPointerException) {
        }
    }




    }
