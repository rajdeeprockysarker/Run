package com.raj.run

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GetDateDetailsStartEndTime {


    public fun ListOfDays(days:Int):ArrayList<DateStartEnd>{

        return getCalculatedDate("","yyyy-MM-dd", days)


    }


    fun getCalculatedDate(date: String, dateFormat: String, days: Int): ArrayList<DateStartEnd> {
        var mDays=ArrayList<DateStartEnd>()
        for(i in days downTo 1)
        {

        val cal = Calendar.getInstance()
        val s = SimpleDateFormat(dateFormat)
        if (date.isNotEmpty()) {
            cal.time = s.parse(date)
        }
        cal.add(Calendar.DAY_OF_YEAR, -i)

            val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            var date=s.format(Date(cal.timeInMillis))
            var mStartDate=date+" 00:00:01"
            var mEndDate=date+" 23:59:59"
            var mStartTimeInMili=sdf.parse(mStartDate)
            var mEndTimeInMili=sdf.parse(mEndDate)
            mDays.add(DateStartEnd(mStartDate,mEndDate,mStartTimeInMili.time,mEndTimeInMili.time))

        }
        return mDays
    }

    class DateStartEnd(var mStartDate:String,var mEndDate:String,var mStartTimeInMili:Long,var mEndTimeInMili:Long){

    }
}