package com.abcd.dicodingsubmissionbelajarandroidinteremediate.data.model

import com.google.gson.annotations.SerializedName

data class UserModel (

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("password")
    var password: String? = null,

    @SerializedName("isLogin")
    var isLogin: Boolean? = null,

)