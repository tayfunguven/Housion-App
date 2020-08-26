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
import kotlinx.android.synthetic.main.fragment_step1.*

class Step1Fragment : Fragment() {


    private lateinit var step1ViewModel: Step1ViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        step1ViewModel =
            ViewModelProviders.of(this).get(Step1ViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_step1, container, false)
        val provinceList = resources.getStringArray(R.array.province)
        val spinProvince = root.findViewById<Spinner>(R.id.province_spinner)
        spinProvince?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, provinceList) } as SpinnerAdapter
        spinProvince?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
               Toast.makeText(activity,"Please select a province", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {

                val selectedProvince = parent.getItemAtPosition(position)
                Values.province = selectedProvince.toString()
            }

        }



        val districtList = resources.getStringArray(R.array.district)
        val spinDistrict = root.findViewById<Spinner>(R.id.district_spinner)
        spinDistrict?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, districtList) } as SpinnerAdapter
        spinDistrict?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select a district", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {
                val selectedDistrict = parent.getItemAtPosition(position)
                Values.district = selectedDistrict.toString()
            }
        }

        val neighborhoodList = resources.getStringArray(R.array.neighborhood)
        val spinNeighborhood = root.findViewById<Spinner>(R.id.neighborhood_spinner)
        spinNeighborhood?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, neighborhoodList) } as SpinnerAdapter
        spinNeighborhood?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(activity,"Please select a neighborhood", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View ,
                position: Int,
                id: Long
            ) {

                val selectedNeighborhood = (parent.getItemAtPosition(position))
                Values.neighborhood = selectedNeighborhood.toString()

            }
        }
        step1ViewModel.text.observe(viewLifecycleOwner, Observer {
            next_button1.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_step1Fragment_to_step2Fragment)

            )
        })
        return root
    }
}


