package com.luisenricke.simpleroomapp.common.extension

fun ArrayList<String>.toStringMultiLine(): String {
    var aux = ""
    for (element in this) {
        aux += element + "\n"
    }
    return aux
}