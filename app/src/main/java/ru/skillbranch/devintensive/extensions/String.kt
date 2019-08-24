package ru.skillbranch.devintensive2019.extensions

fun String.truncate(countSymbols: Int):String{
    return this.substring(0,countSymbols)+"..."
}
//*String.stripHtml
//Необходимо реализовать метод stripHtml для очистки строки от лишних пробелов, html тегов, escape последовательностей
