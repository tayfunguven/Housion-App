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
import kotlinx.android.synthetic.main.fragment_step3.*

class Step3Fragment : Fragment() {
    private lateinit var step3ViewModel: Step3ViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        step3ViewModel =
            ViewModelProviders.of(this).get(Step3ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_step3, container, false)
        val ageList = resources.getStringArray(R.array.age)
        val spinAge = root.findViewById<Spinner>(R.id.age_spinner)
        spinAge?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, ageList) } as SpinnerAdapter
        spinAge?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select a building age", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedAge = parent.getItemAtPosition(position).toString()
                Values.age = selectedAge

            }
        }

        val floorList = resources.getStringArray(R.array.floor_number)
        val spinFloor = root.findViewById<Spinner>(R.id.build_floor_spinner)
        spinFloor?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, floorList) } as SpinnerAdapter
        spinFloor?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select floor number", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedFloor = parent.getItemAtPosition(position)
                Values.floor_number = selectedFloor.toString()

            }
        }

        val totFloorList = resources.getStringArray(R.array.total_floor)
        val spinTotFloor = root.findViewById<Spinner>(R.id.floor_of_apartment_spinner)
        spinTotFloor?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, totFloorList) } as SpinnerAdapter
        spinTotFloor?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select total floor number of building", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedTotFloor = parent.getItemAtPosition(position)
                Values.total_floor = selectedTotFloor.toString()

            }
        }
        step3ViewModel.text.observe(viewLifecycleOwner, Observer {
            return_button.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_step3Fragment_to_step4Fragment)
            )
            prev_button2.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_step3Fragment_to_step2Fragment)
            )
        })
        return root
    }
}