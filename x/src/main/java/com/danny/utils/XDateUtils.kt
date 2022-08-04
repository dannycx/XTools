package com.danny.utils

import java.util.*

/**
 * 日期工具类
 *
 * @author danny
 * @since 2022/8/2
 */
object XDateUtils {

    /**
     * 获取两个日期间的周数
     */
    fun getBetweenWeekCount(startTime: Long, endTime: Long): Int =
            getBetweenWeekCount(startTime, null, endTime, null)

    /**
     * 获取两个日期间的周数
     */
    fun getBetweenWeekCount(startTime: Long, startZone: TimeZone?, endTime: Long, endTimeZone: TimeZone?) : Int {
        val startCalendar = getCalendar(startTime, startZone)
        val endCalendar = getCalendar(endTime, endTimeZone)
        startCalendar.firstDayOfWeek = Calendar.MONDAY
        endCalendar.firstDayOfWeek = Calendar.MONDAY

        var startYear = startCalendar[Calendar.YEAR]
        val startWeek = startCalendar[Calendar.WEEK_OF_YEAR]
        var endYear = endCalendar[Calendar.YEAR]
        val endWeek = endCalendar[Calendar.WEEK_OF_YEAR]

        if (startWeek == 1 && startCalendar[Calendar.MONTH] == Calendar.DECEMBER) {
            ++startYear
        }

        if (endWeek == 1 && endCalendar[Calendar.MONTH] == Calendar.DECEMBER) {
            ++endYear
        }

        val minYear = startYear.coerceAtMost(endYear)

        val startWeekCount = startWeek + getYearBetweenWeekCount(minYear, startYear)
        val endWeekCount = endWeek + getYearBetweenWeekCount(minYear, startYear)
        return endWeekCount - startWeekCount
    }

    private fun getYearBetweenWeekCount(startYear: Int, endYear: Int): Int {
        var weekCount = 0
        if (startYear >= endYear) {
            return weekCount
        }

        // 一年最后一天，跨年31-6
        val endDateDay = 25
        for (i in 0 until endYear - startYear) {
            val calendar = Calendar.getInstance()
            calendar.set(startYear + i, Calendar.DECEMBER, endDateDay)
            calendar.firstDayOfWeek = Calendar.MONDAY
            weekCount += calendar[Calendar.WEEK_OF_YEAR]
        }
        return weekCount
    }

    private fun getCalendar(timeInMillis: Long, timeZone: TimeZone?): Calendar {
        val calendar = when (timeZone) {
            null -> Calendar.getInstance()
            else -> Calendar.getInstance(timeZone)
        }
        calendar.timeInMillis = timeInMillis
        return calendar
    }
}