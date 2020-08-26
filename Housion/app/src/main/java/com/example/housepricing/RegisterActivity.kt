package com.example.housepricing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        reg_button.setOnClickListener {
            registration()
        }
        already_account_text.setOnClickListener{
            Log.d("RegisterActivity", "Try to show login activity")
            //Launch the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
    private fun registration(){
        var email = mail_text_reg.text.toString()
        var password = password_text_reg.text.toString()
        var fullname = name_text_reg.text.toString()

        if(email.isEmpty() || password.isEmpty() || fullname.isEmpty()){
            Toast.makeText(this, "Please fill the whole text boxes", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("RegisterActivity", "User name is:" + fullname)
        Log.d("RegisterActivity", "Email is: " + email)
        Log.d("RegisterActivity", "Password: " + password)


        //to register firebase authentication
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(!it.isSuccessful) return@addOnCompleteListener
            Log.d("Main", "Successfully created user with uid: ${it.result?.user?.uid}")
            Toast.makeText(this, "Successfully created user", Toast.LENGTH_SHORT).show()


            //to save data to firebase database if registration is successful
            val userInfoRef = FirebaseDatabase.getInstance().getReference("Users")
            val userID = userInfoRef.push().key
            val userN =
                (it.result?.user?.uid)?.let { it1 -> UserInformationObject(it1, fullname, email) }
            if (userID != null) {
                userInfoRef.child(userID).setValue(userN)
            }


            val baseAct = Intent(this, MainActivity::class.java)
            finishAffinity()
            startActivity(baseAct)

        }
            .addOnFailureListener{
                //Log.d("Main", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
