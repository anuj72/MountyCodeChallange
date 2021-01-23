package com.example.mountyapp.user.util

import android.widget.Toast
import java.util.*

class getTimeFromAndroid  {


    var c = Calendar.getInstance();
    var timeOfDay = c.get(Calendar.HOUR_OF_DAY);
    fun getques():String{
        if(timeOfDay in 0..11){
            return "Good Morning"
        }else if(timeOfDay in 12..15){
            return "Good Afternoon"
        }else if(timeOfDay in 16..20){
            return "Good Evening"
        }else if(timeOfDay in 21..23){
            return "Good Night"
        } else{
            return "Hi"
        }

    }


}