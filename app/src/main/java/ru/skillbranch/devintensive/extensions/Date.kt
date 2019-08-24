package ru.skillbranch.devintensive2019.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR



fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{ // This function for set pattern date
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}
fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{// This function add SS, MM, HH, DD in time right now
    var time = this.time
    time +=  when(units){
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE-> value * MINUTE
        TimeUnits.HOUR-> value * HOUR
        TimeUnits.DAY-> value * DAY
    }
    this.time = time
    return this
}
fun Date.humanizeDiff(date:Date = Date()):String{ //Дописать функцию
    if ((date.time - this.time).toInt() == 0 || (date.time - this.time).toInt() == 1) return "только что"
    var result: Pair<Pair<Int,Int>, TimeUnits> = toSecMinHourDay(date.time - this.time )
    var str:String = result.first.first.toString()

    // Pair(<<целые единицы TimeUtits, остаток в TimeUnits>, TimeUnits(sec,min,hour,day)>)
    if ((result.first.first in 1..44 && result.second == TimeUnits.SECOND) || (result.first.first == 45 && result.first.second == 0)) return "несколько секунд назад"
    if ((result.first.first in 45..59 && result.second == TimeUnits.SECOND) || (result.first.first == 1 && result.first.second < 26 && result.second == TimeUnits.MINUTE)) return "минуту назад"
    if ((result.first.first in 1..44  && result.second == TimeUnits.MINUTE) || (result.first.first == 45 && result.first.second == 0)){
        str = "минут"
        when(result.first.first){
            in 1..1 ->  str+="у"
            in 2..4 -> str+="ы"
            in 5..20 -> str+=""
            in 21..21 -> str+="у"
            in 22..24 -> str+="ы"
            in 25..30 -> str+= ""
            in 31..31 ->  str+="у"
            in 32..34 ->  str+="ы"
            in 35..40 ->  str+=""
            in 41..41 ->  str+="у"
            in 42..44 ->  str+="ы"
            in 45..45 ->  str+=""
        }
        return "${result.first.first} $str назад"
    }
    if ((result.first.first > 45 && result.second == TimeUnits.MINUTE) || (result.first.first == 1 && result.first.second < 26 && result.second == TimeUnits.HOUR)){
        return "час назад"
    }
    if ((result.first.first >= 1 && result.first.first < 21 && result.second == TimeUnits.HOUR) || (result.first.first == 22 && result.first.second == 0)){
        str = "час"
        when(result.first.first){
            in 1..1 -> str+= ""
            in 2..4 -> str+="а"
            in 5..20 -> str+="ов"
            in 21..21 -> str+=""
            in 22..24 -> str+="а"
        }
        return "${result.first.first} $str назад"
    }
    if ((result.first.first > 22 && result.first.first < 24 && result.second == TimeUnits.DAY) || (result.first.first==1 && result.first.second  < 5 && result.second ==TimeUnits.DAY)){
        return "день назад"
    }
    if (result.first.first in 1..360 && result.second == TimeUnits.DAY){
        if (result.first.first < 10){
            when(result.first.first){
                in 1..1-> str = "день"
                in 2..4-> str = "дня"
                in 5..9-> str = "дней"
            }
        }else{
            var lastChar: Char = str.get(str.length-1)
            if (str.get(str.length-2) in '1'..'1') str = "дней" // проверяем десятки для обеспеченя правильного склонения слова "день"
            else when(lastChar) {
                in '0'..'0'-> str="дней"
                in '1'..'1' -> str="день"
                in '2'..'4' -> str= "дня"
                in '5'..'9' -> str= "дней"
            }
        }
        return "${result.first.first} $str назад"
    }
    if (result.first.first > 360 && result.second == TimeUnits.DAY){
        return "более года назад"
    }
    return "star"
}
fun Date.toSecMinHourDay(result: Long):Pair<Pair<Int,Int>, TimeUnits>{
    if (secondToHumansForm(result,TimeUnits.HOUR).first >= 24){
        return Pair(secondToHumansForm(result,TimeUnits.DAY), TimeUnits.DAY)
    }else if (secondToHumansForm(result,TimeUnits.MINUTE).first >= 60){
        return Pair(secondToHumansForm(result,TimeUnits.HOUR), TimeUnits.HOUR)
    }else if (secondToHumansForm(result,TimeUnits.SECOND).first >= 60){
        return Pair(secondToHumansForm(result,TimeUnits.MINUTE), TimeUnits.MINUTE)
    }else return Pair(secondToHumansForm(result,TimeUnits.SECOND), TimeUnits.SECOND)
}

private fun Date.secondToHumansForm(value: Long, unit: TimeUnits):Pair<Int,Int>{
    when(unit){
        TimeUnits.SECOND -> return Pair((value/ SECOND /60).toInt(),(value%SECOND).toInt())
        TimeUnits.MINUTE -> return Pair((value/ MINUTE).toInt(),((value%MINUTE)/ SECOND).toInt())
        TimeUnits.HOUR -> return Pair((value/ HOUR).toInt(),((value% HOUR)/ MINUTE).toInt())
        TimeUnits.DAY-> return Pair((value/ DAY).toInt(),((value% DAY)/ HOUR).toInt())
    }
}
enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}