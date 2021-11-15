package com.ymejia.notes.General.Users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ymejia.notes.General._binding
import com.ymejia.notes.R
import com.ymejia.notes.databinding.ActivityForgotBinding
import com.ymejia.notes.databinding.ActivityGeneralBinding

class ForgotActivity : AppCompatActivity() {

    lateinit var binding: ActivityForgotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ///setContentView(R.layout.activity_forgot)

        binding = ActivityForgotBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnsutmit.setOnClickListener{
            val email: String = binding.txtemail.text.toString().trim{it <= ' '}
            if(email.isEmpty())
            {
                Toast.makeText(this@ForgotActivity,"Please Enter email Address.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(this@ForgotActivity,"EMAIL SENT", Toast.LENGTH_LONG).show()
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this@ForgotActivity,task.exception!!.message.toString(),
                                Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

    }
}