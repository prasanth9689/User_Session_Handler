package com.skyblue.mya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.skyblue.mya.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardBinding: ActivityDashboardBinding
    lateinit var session: SessionHandler
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)

        session = SessionHandler
        user = session.getUserDetails()!!

        Toast.makeText(this, user.email, Toast.LENGTH_SHORT).show();

        dashboardBinding.txtEmail.text = user.email

        dashboardBinding.logoutBtn.setOnClickListener{
            session.logoutUser()
            finish()
        }
    }
}