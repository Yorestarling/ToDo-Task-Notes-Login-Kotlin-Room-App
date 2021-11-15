package com.ymejia.notes.General.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ymejia.notes.General.GeneralActivity
import com.ymejia.notes.General.HomeActivity
import com.ymejia.notes.MainActivity
import com.ymejia.notes.R
import com.ymejia.notes.databinding.ActivityMainBinding
import com.ymejia.notes.databinding.FragmentTaskBinding


class TaskFragment : Fragment() {

    //lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val bind = FragmentTaskBinding.inflate(layoutInflater)

        bind.TaskButtom.setOnClickListener{
            val intent = Intent(this@TaskFragment.requireContext(),MainActivity::class.java)
            startActivity(intent)
        }

        return bind.root

       // return inflater.inflate(R.layout.fragment_task, container, false)


    }





}