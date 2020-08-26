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

class Step4Fragment : Fragment() {
    private lateinit var step4ViewModel: Step4ViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        step4ViewModel =
            ViewModelProviders.of(this).get(Step4ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_step4, container, false)
        val roomList = resources.getStringArray(R.array.room_number)
        val spinRoom = root.findViewById<Spinner>(R.id.rooms_spinner)
        spinRoom?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, roomList) } as SpinnerAdapter
        spinRoom?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select room number", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedRoom = parent.getItemAtPosition(position)
                Values.room = selectedRoom.toString()

            }
        }

        val bathList = resources.getStringArray(R.array.bath_number)
        val spinBath = root.findViewById<Spinner>(R.id.bathrooms_spinner)
        spinBath?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, bathList) } as SpinnerAdapter
        spinBath?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select bathroom number", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedBath = parent.getItemAtPosition(position)
                Values.bath = selectedBath.toString()

            }
        }
        step4ViewModel.text.observe(viewLifecycleOwner, Observer {
        next_button4.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_step4Fragment_to_step5Fragment)
        )
        prev_button3.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_step4Fragment_to_step3Fragment)
        )
        })
        return root
    }
}