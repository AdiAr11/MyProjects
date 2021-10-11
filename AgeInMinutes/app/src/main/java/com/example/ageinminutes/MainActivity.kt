package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDatePicker.setOnClickListener{

            clickDatePicker()
        }


    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)    //gets current or present year
        val currentMonth = myCalendar.get(Calendar.MONTH)  //gets current or present month
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)      //gets present day

        //opens the date picker dialog box
        val dpd = DatePickerDialog(this, { _, yearChosen, monthChosen, chosenDayOfMonth ->
            val selectedDate = "$chosenDayOfMonth/${monthChosen + 1}/$yearChosen"
            binding.dateTextView.text = selectedDate

            //val ageInMinutes: Int = (currentYear - yearChosen) * 525600 + (currentMonth - monthChosen) * 43800 + (currentDay - chosenDayOfMonth) * 1440
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)   //theDate will be parsed into Date Object type

            val selectedDateInMinutes = theDate!!.time / 60000   //the non-null assertion operation (!!) converts any value to a non-null type and throws
                                                        // an exception if the value is null. We can write b!!, and this will return non-null value of b

            //The date is represented as a Date object or as the milliseconds since January 1, 1970, 00:00:00 GMT.
            //so the above .time function returns milliseconds from Jan 1 1970 till selectedDate(as parsed)
            //or the above .time calls the getTime() function which Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            val ageInMinutes = currentDateInMinutes - selectedDateInMinutes

            binding.ageInMinutesTextViews.text = ageInMinutes.toString()

            val ageInDays = ageInMinutes / 1440
            binding.ageInDaysTextView.text = ageInDays.toString()

        }, currentYear, currentMonth, currentDay)
        //year, month, day passed in the last are different from the one in the function with lambda expression.
        // We passed these to set the current date when date picker dialog box is opened.

        dpd.datePicker.maxDate = myCalendar.timeInMillis - 86400000
        dpd.show()




    }

}