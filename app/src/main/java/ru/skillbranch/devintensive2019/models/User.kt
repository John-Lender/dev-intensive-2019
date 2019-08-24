package ru.skillbranch.devintensive2019.models

import ru.skillbranch.devintensive2019.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false) {
    constructor(id: String, firstName: String?, lastName: String?): this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )
    companion object Factory{
        var id = -1
        fun create(fullName: String): User{
            id ++
            val pairFullName: Pair<String?, String?> = Utils.parseFullName(fullName)
            return User("${id}", pairFullName.first, pairFullName.second )
        }
    }
}
