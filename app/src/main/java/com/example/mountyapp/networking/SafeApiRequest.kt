package com.example.mountyapp.networking


import com.example.mountyapp.util.ApiException
import com.example.mountyapp.util.unAuthorize
import org.json.JSONException
import retrofit2.Response

abstract class SafeApiRequest() {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let{
                try{
                   // message.append(JSONObject(it).getString("message"))
                }catch(e: JSONException){ }
               // message.append("\n")
            }
            if(response.code().equals(401)){
                message.append("Error Code: ${response.code()}"+"\n"+"Unauthenticated or Session has expired Login Again")
                throw unAuthorize(message.toString())

            }else{
                message.append("Error Code: ${response.code()}"+"\n"+"Sorry for inconvenience, system has encountered technical glitch")
                throw ApiException(message.toString())
            }

        }
    }

}