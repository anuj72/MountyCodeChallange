package com.example.mountyapp.util

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class SocketTimeOut(message: String) : IOException(message)
class unAuthorize(message: String) : IOException(message)