package ru.skillbranch.devintensive2019.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{//Возвращает имя и фамилию
        val str: List<String>? = fullName?.split(' ')
        val pairOfFullName: Pair<String?, String?> = Pair(str?.getOrNull(0), str?.getOrNull(1))
        return pairOfFullName
    }
    fun transliteration(name: String, devider: String = " "): String{ //Переводит слово на киррилице в слово на латинице
        var nameLat: String = ""
        val devideName: List<String>? = name?.split(" ")
        val firstName: String? = devideName?.getOrNull(0)?.toLowerCase()
        val lastName: String? = devideName?.getOrNull(1)?.toLowerCase()
        for(i:Int in 0..firstName!!.length-1){
                nameLat+= ABC("${firstName.getOrNull(i)}")
            }
        nameLat+=devider
        for (i:Int in 0..lastName!!.length-1)
            nameLat+= ABC("${lastName.getOrNull(i)}")
        return nameLat
    }
    private fun ABC(symbol: String?):String{
        when(symbol){
            "а"->return "a"
            "б"->return "b"
            "в"->return "v"
            "г"->return "g"
            "д"->return "d"
            "е"->return "e"
            "ё"->return "e"
            "ж"->return "zh"
            "з"->return "z"
            "и"->return "i"
            "й"->return "i"
            "к"->return "k"
            "л"->return "l"
            "м"->return "m"
            "н"->return "n"
            "о"->return "o"
            "п"->return "p"
            "р"->return "r"
            "с"->return "s"
            "т"->return "t"
            "у"->return "u"
            "ф"->return "f"
            "х"->return "h"
            "ц"->return "c"
            "ч"->return "ch"
            "ш"->return "sh"
            "щ"->return "sh"
            "ъ"->return ""
            "ы"->return "i"
            "ь"->return ""
            "э"->return "e"
            "ю"->return "yu"
            "я"->return "ya"
            else -> throw Exception("This symbol ${symbol} isn't contained")
        }
    }
    fun toInitials(firstName: String,  lastName: String):String{
        var initials: String = "${firstName.getOrNull(0)}${lastName.getOrNull(0)}"
        return initials
    }
}