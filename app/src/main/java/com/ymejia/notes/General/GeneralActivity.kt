package com.ymejia.notes.General

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ymejia.notes.AddNotesActivity
import com.ymejia.notes.MainActivity
import com.ymejia.notes.R
import com.ymejia.notes.databinding.ActivityGeneralBinding
import com.ymejia.notes.databinding.ActivityMainBinding


lateinit var binding: ActivityGeneralBinding

class GeneralActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_general)

        binding = ActivityGeneralBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnlogin.setOnClickListener{
            val intent = Intent(this@GeneralActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }


}