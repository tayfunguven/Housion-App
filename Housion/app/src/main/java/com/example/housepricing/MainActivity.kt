package com.example.housepricing

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.TaskStackBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_about, R.id.nav_profile), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //to make navView item be clickable
        val logoutItem = navView.menu.findItem(R.id.nav_logout)
        logoutItem.setOnMenuItemClickListener {
            logoutAct()
            true
        }
        //to display email and user name info on the nav_header_main and display on profile
        val headView: View = navView.getHeaderView(0)
        val userMail = FirebaseAuth.getInstance().currentUser
        userMail?.let {
            val email = userMail.email
            val navUserMailTxt: TextView = headView.findViewById(R.id.usermail_textView)
            navUserMailTxt.text = email

            //to get user name, i use orderByChild for ordering children in firebase database
            //then search for the current user name by finding .equalTo(email)
           FirebaseDatabase.getInstance().getReference("Users").orderByChild("email")
                .equalTo(email).addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    for(p in p0.children){
                        val userName = p.child("name").getValue(String::class.java)
                        val navUserNameTxt: TextView = headView.findViewById(R.id.username_textView)
                        navUserNameTxt.text = userName
                    }
                }
                override fun onCancelled(p0: DatabaseError) {
                    Log.d("Database Error","Database error occured: ${p0.message}")
                }
            })

        }




    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun logoutAct(){
        FirebaseAuth.getInstance().signOut()
        val logout = Intent(this, LoginActivity::class.java)
        finishAffinity()
        startActivity(logout)
    }

    fun predictionResult(){


    }
}
