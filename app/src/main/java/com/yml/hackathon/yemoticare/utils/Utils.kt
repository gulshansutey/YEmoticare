package com.yml.hackathon.yemoticare.utils

import android.content.Context
import java.nio.charset.StandardCharsets


object Utils {

    fun Context.getJsonData(fileName: String): String {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, StandardCharsets.UTF_8)

        return json
    }

}