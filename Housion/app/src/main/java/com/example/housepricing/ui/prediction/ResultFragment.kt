package com.example.housepricing.ui.prediction

import android.app.ActionBar
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_result.*
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.reflect.jvm.internal.ReflectProperties

class ResultFragment : Fragment() {

    private lateinit var resultViewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        resultViewModel =
            ViewModelProviders.of(this).get(ResultViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_result, container, false)
        val textView = root.findViewById<TextView>(R.id.text_result_text)
        ActionBar.OnMenuVisibilityListener { false }
        //val val3 = Values.gross

        //-----GETTING USED VALUES IN PREDICTION MODEL GIVEN BELOW FROM VALUES CLASS
        val val5x = Values.age
        var val5 = val5x
        val5 = when(val5){
            "20+" -> "20"
            else ->{
                Values.age
            }
        }

        val val6x = Values.floor_number
        var val6 = val6x
        val6 = when(val6){
            "Dublex" -> "30"
            "Self-Container" -> "34"
            "Villa" -> "35"
            "Triplex" -> "52"
            "Pavilion" -> "55"
            else ->{
                Values.floor_number
            }
        }

        val val7x = Values.room
        var val7 = val7x
        val7 = when(val7){
            "1+0" -> "1"
            "1+1" -> "2"
            "2+1" -> "3"
            "3+1" -> "4"
            "4+1" -> "5"
            "5+1" -> "6"
            "5+2" -> "7"
            "6+1" -> "8"
            else ->{
                "9"
            }
        }


        var val8 = Values.bath


        val val9x = Values.total_floor
        var val9 = val9x
        val9 = when(val9){
            "Kot3" -> Values.const27.toString()
            "Kot2" -> Values.const26.toString()
            "Kot1" -> Values.const25.toString()
            "Half ground floor" -> Values.const24.toString()
            "Ground floor" -> "0"
            else ->{
                Values.total_floor
            }
        }


        //----PREDICTION MODEL FOR HOUSE PRICING
        //----The model is taken from Senda Yildirim's bachelor project
        val result = -1889203.4 + 17512.2* 150 - 8111.1* (val6.toInt()) + 1420279.1* (Values.const9) + 789.3* (val5.toInt()) - 23984.7* (val9.toInt()) - 164533.3 * (val7.toInt()) + 268245.1* (val8.toInt())- 18021.5* 0 + 359720.8* 1 - 585324.7* 1 - 158873.8* 0

        val textResult = root.findViewById<TextView>(R.id.text_result)

        //----CONVERTING MONEY FORMAT
        var formatter:NumberFormat = DecimalFormat("#,###")

        //----RESULT
        textResult.text = "${formatter.format(result)}.00 ₺"
        textView.text= "Predicted price is: "


        resultViewModel.text.observe(viewLifecycleOwner, Observer {
            return_button.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_resultFragment_to_nav_home)


            )
        })


        return root
    }



}