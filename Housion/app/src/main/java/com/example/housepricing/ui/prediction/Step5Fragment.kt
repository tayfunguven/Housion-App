package com.example.housepricing.ui.prediction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.housepricing.R
import com.example.housepricing.Values
import kotlinx.android.synthetic.main.fragment_step4.*
import kotlinx.android.synthetic.main.fragment_step5.*

class Step5Fragment : Fragment() {
    private lateinit var step5ViewModel: Step4ViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        step5ViewModel =
            ViewModelProviders.of(this).get(Step4ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_step5, container, false)
        val creditList = resources.getStringArray(R.array.credit)
        val spinCredit = root.findViewById<Spinner>(R.id.credit_availability_spinner)
        spinCredit?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, creditList) } as SpinnerAdapter
        spinCredit?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select credit availability", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedCredit = parent.getItemAtPosition(position)
                Values.credit = selectedCredit.toString()

            }
        }

        val usageList = resources.getStringArray(R.array.usage)
        val spinUsage = root.findViewById<Spinner>(R.id.usage_status_spinner)
        spinUsage?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, usageList) } as SpinnerAdapter
        spinUsage?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select usage status", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedUsage = parent.getItemAtPosition(position)
                Values.usage = selectedUsage.toString()

            }
        }

        val heatList = resources.getStringArray(R.array.heating)
        val spinHeat = root.findViewById<Spinner>(R.id.heating_type_spinner)
        spinHeat?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, heatList) } as SpinnerAdapter
        spinHeat?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select heating type", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedHeat = parent.getItemAtPosition(position)
                Values.heating = selectedHeat.toString()

            }
        }
        step5ViewModel.text.observe(viewLifecycleOwner, Observer {
            result_button.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_step5Fragment_to_resultFragment)
                
            )
            prev_button4.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_step5Fragment_to_step4Fragment)
            )
        })
        return root
    }
}