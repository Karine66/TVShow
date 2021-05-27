package com.karine.tvshow.utils

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DatesConverter {
    companion object {
        fun dateConvertForMovies(dateToFormat: String?): String? {
            try {
                if (dateToFormat != null && dateToFormat.isNotEmpty()) {
                    var sdf: SimpleDateFormat? = null
                    val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(dateToFormat)
                    sdf = SimpleDateFormat("dd/MM/yyyy")
                    return sdf.format(date)
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return ""
        }
    }
}