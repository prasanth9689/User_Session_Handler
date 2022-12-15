package com.skyblue.mya

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.skyblue.mya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    var PREFS_KEY = "prefs"
    var EMAIL_KEY = "email"
    var PWD_KEY = "pwd"

    // on below line we are creating a
    // variable for email and password.
    var email = ""
    var pwd = ""

    lateinit var sharedPreferences: SharedPreferences
    lateinit var session: SessionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // initialize for main activity
        session = SessionHandler

        // The initialization can be achieved in your case by calling the method init() -  helper class.
        session.init(this)

        // on below line we are initializing our shared preferences.
        sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

        email = sharedPreferences.getString(EMAIL_KEY, "").toString()
        pwd = sharedPreferences.getString(PWD_KEY, "").toString()

        mainBinding.idBtnLogin.setOnClickListener{
            if (TextUtils.isEmpty(mainBinding.idEdtEmail.text.toString()) && TextUtils.isEmpty(mainBinding.idEdtPassword.text.toString())){
                // if email and pwd edit text is empty we are displaying a toast message
                Toast.makeText(this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
            }else{
//                val editor: SharedPreferences.Editor = sharedPreferences.edit()
//                editor.putString(EMAIL_KEY, mainBinding.idEdtEmail.text.toString())
//                editor.putString(PWD_KEY, mainBinding.idEdtPassword.text.toString())
//                editor.apply()

                session.loginUser(mainBinding.idEdtEmail.text.toString(), mainBinding.idEdtPassword.text.toString())

                val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    // on below line we are calling on start method.
    override fun onStart() {
        super.onStart()
        // in this method we are checking if email and pwd are not empty.
        if (!email.equals("") && !pwd.equals("")) {
            // if email and pwd is not empty we
            // are opening our main 2 activity on below line.
            val i = Intent(this@MainActivity, DashboardActivity::class.java)

            // on below line we are calling start
            // activity method to start our activity.
            startActivity(i)

            // on below line we are calling
            // finish to finish our main activity.
            finish()
        }
    }
}