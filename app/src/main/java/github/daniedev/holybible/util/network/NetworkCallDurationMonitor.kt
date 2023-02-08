package github.daniedev.holybible.util.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class NetworkCallDurationMonitor : Interceptor{

    private val tag = "N/W Call Monitor"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val networkCallStartTimeStamp = System.nanoTime()
        Log.d(tag, String.format("Sending request %s on %s%n%s",
            request.url, chain.connection(), request.headers))
        val response = chain.proceed(request)
        val networkCallEndTimeStamp = System.nanoTime()
        Log.d(tag, String.format("Received response for %s in %.1fms%n%s",
            response.request.url, (networkCallEndTimeStamp - networkCallStartTimeStamp) / 1e6 , response.headers))
        Log.d(tag, "Time taken for completion ${(networkCallEndTimeStamp - networkCallStartTimeStamp) / 1e6} ms")
        return response
    }
}