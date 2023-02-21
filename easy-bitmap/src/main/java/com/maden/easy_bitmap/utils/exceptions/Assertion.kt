package com.maden.easy_bitmap.utils.exceptions

object Assertion {

    fun quality(data: Int) {
        assert(data in 1..100) { "Quality: '$data' not valid! Must be between 1 and 100" }
    }

    fun string(data: String) = assert(data.isNotEmpty()) { "String is not empty!" }

    fun byteArray(data: ByteArray) = assert(data.isNotEmpty()) { "ByteArray is not empty!" }

}