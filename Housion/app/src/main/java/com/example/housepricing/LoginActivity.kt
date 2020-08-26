package com.example.housepricing

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import org.w3c.dom.Text

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            login()
        }
        dont_have_text.setOnClickListener{
            Log.d("LoginActivity", "Try to show register activity")
            //Launch the register activity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        forgot_password_textView.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enter your e-mail address:")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val enterMail = view.findViewById<EditText>(R.id.mail_editTExt)
            builder.setView(view)
            builder.setPositiveButton("Send",DialogInterface.OnClickListener { _, _ ->
                forgotPassword(enterMail)
            })
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }

    }
    private fun login(){
        val email = mail_text.text.toString()
        val password = password_text.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill the whole text boxes", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("LoginActivity", "Attempt log in with email/password: $email/***")


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(!it.isSuccessful) return@addOnCompleteListener
            Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()
            val baseAct = Intent(this, MainActivity::class.java)
            finishAffinity()
            startActivity(baseAct)

        }

            .addOnFailureListener{
                Toast.makeText(this, "Failed to logged in ${it.message}", Toast.LENGTH_SHORT).show()
            }

    }
    private fun forgotPassword(enterMail : EditText){
        if(enterMail.text.toString().isEmpty()){
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().sendPasswordResetEmail(enterMail.text.toString()).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this, "Email sent Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

}