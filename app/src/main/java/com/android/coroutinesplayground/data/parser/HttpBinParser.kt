package com.android.coroutinesplayground.data.parser

import com.android.coroutinesplayground.data.model.HttpbinGet
import com.android.coroutinesplayground.util.getString
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class HttpBinParser : JsonDeserializer<HttpbinGet> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): HttpbinGet {
        return HttpbinGet(
            json.asJsonObject.getString("origin"),
            json.asJsonObject.getString("url")
        )
    }

}