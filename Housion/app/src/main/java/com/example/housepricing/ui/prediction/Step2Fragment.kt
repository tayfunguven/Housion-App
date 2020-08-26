package com.example.housepricing.ui.prediction

import android.icu.text.NumberFormat
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.housepricing.R
import com.example.housepricing.Values
import kotlinx.android.synthetic.main.fragment_step2.*

class Step2Fragment : Fragment() {
    private lateinit var step2ViewModel: Step2ViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        step2ViewModel =
            ViewModelProviders.of(this).get(Step2ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_step2, container, false)
        step2ViewModel.text.observe(viewLifecycleOwner, Observer {
            next_button2.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_step2Fragment_to_step3Fragment)
            )
            prev_button1.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_step2Fragment_to_step1Fragment)
            )
        })
        return root
    }
}


