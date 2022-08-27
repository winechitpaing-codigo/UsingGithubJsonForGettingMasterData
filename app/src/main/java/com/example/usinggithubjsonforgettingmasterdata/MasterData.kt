package com.example.usinggithubjsonforgettingmasterdata

import com.google.gson.annotations.SerializedName

data class MasterData(
    @SerializedName("updateTitle") val updateTitle: String,
    @SerializedName("updateMessage") val updateMessage: String,
    @SerializedName("versionCode") val versionCode: Int,
    @SerializedName("versionName") val versionName: String,
    @SerializedName("forceUpdate") val forceUpdate: Boolean,
    @SerializedName("forceUpdateMessage") val forceUpdateMessage: String,
    @SerializedName("storeLink") val storeLink: String
)
