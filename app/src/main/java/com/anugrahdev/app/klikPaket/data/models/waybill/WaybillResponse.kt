package com.anugrahdev.app.klikPaket.data.models.waybill


import com.google.gson.annotations.SerializedName

data class WaybillResponse(
    @SerializedName("data")
    val `data`: WaybillData,
    @SerializedName("status")
    val status: String,
    @SerializedName("statusCode")
    val statusCode: Int
)