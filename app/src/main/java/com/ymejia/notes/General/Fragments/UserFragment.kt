package com.ymejia.notes.General.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ymejia.notes.General.GeneralActivity
import com.ymejia.notes.MainActivity
import com.ymejia.notes.R
import com.ymejia.notes.databinding.FragmentTaskBinding
import com.ymejia.notes.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    //lateinit var binding: FragmentUserBinding
    private lateinit var database: DatabaseReference
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var databases: FirebaseDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_user, container, false)

        val bind = FragmentUserBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        databases = FirebaseDatabase.getInstance()
        databaseReference = databases?.reference!!.child("Users")

        loadProfile()

        return bind.root
    }

    private fun loadProfile() {

        val binding = FragmentUserBinding.inflate(layoutInflater)
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        binding.txtEmailAdd.text = user?.email

        userreference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                binding.txtName.text = snapshot.child("Firstname").value.toString()
                binding.txtLastName.text = snapshot.child("Lastname").value.toString()
                binding.txtPhone.text = snapshot.child("Phone").value.toString()
                binding.txtCountry.text = snapshot.child("Country").value.toString()
                binding.txtAddress.text = snapshot.child("Address").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        binding.btnlogout.setOnClickListener {
            //auth.signOut()

            val intent = Intent(this@UserFragment.requireContext(),GeneralActivity::class.java)
           startActivity(intent)
        }
    }
}