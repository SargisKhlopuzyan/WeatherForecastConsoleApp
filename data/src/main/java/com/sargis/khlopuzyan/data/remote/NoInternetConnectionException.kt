package com.sargis.khlopuzyan.data.remote

import java.io.IOException

class NoInternetConnectionException : IOException() {
    override val message: String
        get() = "No Internet connection"
}
