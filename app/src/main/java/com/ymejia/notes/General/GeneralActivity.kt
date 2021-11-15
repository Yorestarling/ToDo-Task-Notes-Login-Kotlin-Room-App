package com.ymejia.notes.General

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.ymejia.notes.AddNotesActivity
import com.ymejia.notes.General.Fragments.HomeFragment
import com.ymejia.notes.General.Fragments.ProviderType
import com.ymejia.notes.General.Users.ForgotActivity
import com.ymejia.notes.General.Users.RegisterActivity
import com.ymejia.notes.MainActivity
import com.ymejia.notes.R
import com.ymejia.notes.databinding.ActivityGeneralBinding
import com.ymejia.notes.databinding.ActivityMainBinding


 lateinit var _binding: ActivityGeneralBinding

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference


class GeneralActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_general)

        _binding = ActivityGeneralBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)


        auth = FirebaseAuth.getInstance()
        setup()


       // _binding.btnlogin.setOnClickListener{
       //     val intent = Intent(this@GeneralActivity, HomeActivity::class.java)
       //     startActivity(intent)
       // }

        _binding.btnsignup.setOnClickListener{
            val intent = Intent(this@GeneralActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        _binding.tvforgot.setOnClickListener{
            val intent = Intent(this@GeneralActivity, ForgotActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setup() {
        title = "Log in"

        _binding.btnlogin.setOnClickListener {
            if (_binding.txtpasswrod.text.isNotEmpty() && _binding.txtusername.text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    _binding.txtusername.text.toString(),
                    _binding.txtpasswrod.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?:"",
                                it.result?.user?.uid ?:"",
                                ProviderType.BASIC)

                            // startActivity(Intent(this,HomeActivity::class.java))
                            finish()

                            _binding.txtusername.text.clear()
                            _binding.txtpasswrod.text.clear()
                        } else {
                            showAlert()
                            _binding.txtusername.text.clear()
                            _binding.txtpasswrod.text.clear()
                        }
                    }
            }
            else if(_binding.txtpasswrod.text.isNullOrBlank()){
                _binding.txtusername.error = "EMAIL REQUIRED"
            }
            else if(_binding.txtusername.text.isNullOrBlank()){
                _binding.txtpasswrod.error = "PASSWORD REQUIRED"
            }
        }
    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("An Authentication Error")
        builder.setPositiveButton("Accept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showHome(email: String,usersId: String, provider: ProviderType) {

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("UserId", usersId)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}