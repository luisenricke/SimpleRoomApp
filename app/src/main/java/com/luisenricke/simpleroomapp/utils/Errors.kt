package com.luisenricke.simpleroomapp.utils

import java.lang.Exception

interface Errors {
    class Example(message: String = "Unknown") : Exception(message)
}