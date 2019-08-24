package ru.skillbranch.devintensive2019.extensions

import ru.skillbranch.devintensive2019.models.User
import ru.skillbranch.devintensive2019.models.UserView
import ru.skillbranch.devintensive2019.utils.Utils

fun User.toUserView(): UserView {
    val nickName = Utils.transliteration("$firstName $lastName")
    val initials =Utils.toInitials("${firstName}","${lastName}")
    val status =if (lastVisit == null)"Еше ни разу не был" else if(isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"
    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        nickName = nickName,
        status = status ,
        initials =initials
        )
}