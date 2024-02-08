package com.example.dz6.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoriesApiModel (
    @SerializedName("id") @Expose  // должны быть идентичны полям ид и нэйм на сервере и в апиинтерфейсе
    var id: Int? = null,
    @SerializedName("name") @Expose
    var name: String? = null
)