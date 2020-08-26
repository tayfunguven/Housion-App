package com.example.housepricing.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.housepricing.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val userMail: TextView = root.findViewById(R.id.text_profileUserMail)
        val userName: TextView = root.findViewById(R.id.text_profileUserName)
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val email = user.email
            userMail.text = email
            FirebaseDatabase.getInstance().getReference("Users").orderByChild("email")
                .equalTo(email).addListenerForSingleValueEvent(object: ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {
                        for(p in p0.children){
                            val name = p.child("name").getValue(String::class.java)
                            userName.text = name
                        }
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Log.d("Database Error","Database error occured: ${p0.message}")
                    }
                })

        }
        /*edit_button.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_nav_profile_to_editFragment)
        }*/

        return root
    }



}