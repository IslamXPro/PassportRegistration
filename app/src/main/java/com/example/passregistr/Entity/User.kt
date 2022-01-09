package com.example.passregistr.Entity

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    var userName: String? = null
    var userPassportId: String? = null
    var userSName: String? = null
    var userOName: String? = null

    var userViloyat: String? = null
    var userCity: String? = null

    var userAddress: String? = null
    var userDataStart: String? = null
    var userDataEnd: String? = null
    var userJinsi: String? = null
    var userImage: String? = null

    @SuppressLint("SimpleDateFormat")
    var data: String? = SimpleDateFormat("dd.MM.yyyy").format(Date())

    constructor()
    constructor(userName: String?, userPassportId: String?) {
        this.userName = userName
        this.userPassportId = userPassportId
    }

    constructor(
        userName: String?,
        userPassportId: String?,
        userSName: String?,
        userOName: String?,
        userViloyat: String?,
        userCity: String?,
        userAddress: String?,
        userDataStart: String?,
        userDataEnd: String?,
        userJinsi: String?,
        userImage: String?
    ) {
        this.userName = userName
        this.userPassportId = userPassportId
        this.userSName = userSName
        this.userOName = userOName
        this.userViloyat = userViloyat
        this.userCity = userCity
        this.userAddress = userAddress
        this.userDataStart = userDataStart
        this.userDataEnd = userDataEnd
        this.userJinsi = userJinsi
        this.userImage = userImage
    }


}