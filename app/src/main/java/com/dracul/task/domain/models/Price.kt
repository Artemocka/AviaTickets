package com.dracul.task.domain.models

data class Price(
    val value: Int,
){
    fun getSplitted():String{
        return this.value.toSplitted()
    }
}

fun Int.toSplitted(): String {
    val reversedNumber = this.toString().reversed()
    val stringBuilder = StringBuilder()

    for (i in reversedNumber.indices) {
        if (i > 0 && i % 3 == 0) {
            stringBuilder.append(" ")
        }
        stringBuilder.append(reversedNumber[i])
    }

    return stringBuilder.reverse().toString()
}