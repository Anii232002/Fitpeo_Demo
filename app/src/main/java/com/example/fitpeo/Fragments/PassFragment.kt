package com.example.fitpeo.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeo.Activity.PassDetails
import com.example.fitpeo.PhotoAdapter1.PhotoAdapter1
import com.example.fitpeo.R
import com.example.fitpeo.RecyclerAdapters.PhotoAdapter2

import de.hdodenhof.circleimageview.CircleImageView


class Pass_Fragment : Fragment() {
    val photo1 = ArrayList<Int>()
    val photo2 = ArrayList<Int>()
    lateinit var rv1 : RecyclerView
    lateinit var rv2 : RecyclerView
    lateinit var x: CircleImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_pass_, container, false)
       x = view.findViewById(R.id.info)
        x.setOnClickListener { view ->
            val intent   = Intent(this.requireContext(), PassDetails::class.java)
            startActivity(intent)

        }
        val adapter = PhotoAdapter1()
        val adapter2 = PhotoAdapter2()
        photo1.add(R.drawable.avatar1)
        photo1.add(R.drawable.avatar2)
        photo1.add(R.drawable.avatar3)
        photo1.add(R.drawable.avatar4)
        photo1.add(R.drawable.avatar5)
        photo1.add(R.drawable.avatar6)

        photo2.add(R.drawable.avatar1)
        photo2.add(R.drawable.avatar2)
        photo2.add(R.drawable.avatar3)
        photo2.add(R.drawable.avatar4)
        rv1 = view.findViewById<RecyclerView>(R.id.photo_rv1)
        rv1.adapter = adapter
        rv1.layoutManager =  LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,true)
        adapter.updatePhotos(photo1)
        rv2 = view.findViewById<RecyclerView>(R.id.photo_rv2)
        rv2.adapter = adapter2
        rv2.layoutManager =  LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,true)
        adapter2.updatePhotos(photo2)


        return view


    }

}