package intellinectsschool.intellinects.com.v4flavors.Network

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import intellinectsschool.intellinects.com.v4flavors.Content_Provider.EventDataSource
import intellinectsschool.intellinects.com.v4flavors.Other.ApiConstant
import intellinectsschool.intellinects.com.v4flavors.Other.Constants
import intellinectsschool.intellinects.com.v4flavors.Other.DataCallback


import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.collections.HashMap


/**
 * Modified bi vikas 13/3/2018
 */
class NetworkCall {
    companion object {

        val TIMEOUT = 10000
        val TAG = "NetworkCall"
        val PAGE_NOT_FOUND = "Page Not Found"
        var ACCESS_DENIED = "You don't have access"
        val REQUEST_TIME = "Request Time out, Try Again"
        val INTERNAL_SERVER_ERROR="Internal Server Error, Try Again"
        val UNKNOWN_ERROR = "Timeout , Try Again"
        val NETWORK_ERROR = "Network issue, Try Again"
        var PARAMETER_MISSSING = "Parameter missing"




        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }





        fun getNews(context: Context, dataCallback: DataCallback) {

            val url = "https://apps.intellinects.com/masterapi/public/api/wpNews" //"https://test.intelliquiz.in/intelliquiz/public/api/verify" //
            Log.d("KOTLIN_GET", "getURL: $url")
            val requestRegistration = object : StringRequest(Method.POST, url, Response.Listener { s ->
                try {
//                var vv = s
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                dataCallback.onSuccess(s)
            }, Response.ErrorListener { volleyError ->
                volleyError.printStackTrace()
                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {
                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }

                dataCallback.onFailure(errorMessage);
                Log.d("JSONRESPONSE", "onError: $volleyError")
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                   // params["SCHOOL_ID"] =Constants.SCHOOL_ID

                    return params
                }
            }
            requestRegistration.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            Constants.REQUEST_QUEUE.add(requestRegistration)
        }

        fun getEvents(context: Context, dataCallback: DataCallback) {

            val url = "https://apps.intellinects.com/masterapi/public/api/wpEvents" //"https://test.intelliquiz.in/intelliquiz/public/api/verify" //
            Log.d("KOTLIN_GET", "getURL: $url")
            val requestRegistration = object : StringRequest(Method.POST, url, Response.Listener { s ->
                try {
//                var vv = s
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                dataCallback.onSuccess(s)
            }, Response.ErrorListener { volleyError ->
                volleyError.printStackTrace()
                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {
                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }

                dataCallback.onFailure(errorMessage);
                Log.d("JSONRESPONSE", "onError: $volleyError")
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                   // params["SCHOOL_ID"] = Constants.SCHOOL_ID
                    return params
                }
            }
            requestRegistration.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            Constants.REQUEST_QUEUE.add(requestRegistration)
        }


        fun getRefreshedEvents(context: Context, dataCallback: DataCallback) {
            val url =
                (Constants.IP_ADDRESS + Constants.BASE_URL + "?action=events&eventDate=" + Constants.mStringDate + "&SCHOOL_ID="
                        + Constants.SCHOOL_ID)
            val stringRequestEvents = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                try {
                    val eventDataSource = EventDataSource()
                    eventDataSource.open(context)
                    eventDataSource.insertEvents(context, response)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                dataCallback.onSuccess(response)
            }, Response.ErrorListener {volleyError->
                volleyError.printStackTrace()
                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {
                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }
                dataCallback.onFailure(errorMessage)


            })
            stringRequestEvents.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

            Constants.REQUEST_QUEUE.add(stringRequestEvents);
        }

        fun getClassDivSub(paramMap: HashMap<String, String>, dataCallback: DataCallback) {

            val url = ApiConstant.CLASS_LIST_API

            val stringRequestSecureAttendance = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->

                dataCallback.onSuccess(response)


            }, Response.ErrorListener { volleyError ->
                volleyError.printStackTrace()
                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {
                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }
                dataCallback.onFailure(errorMessage)


            }) {
                @Throws(AuthFailureError::class)
//                override fun getHeaders(): Map<String, String> {
//                    val params = HashMap<String, String>()
//                    params["Accept"] = Constants.AUTH_ACCEPT
//                    //                params.put("Authorization",Constants.AUTHERIZATIONKEY);
//
//                    params["Authorization"] = NetworkCall.getAuthenticator(context)
//
//                    return params
//                }

                override fun getParams(): Map<String, String> {
                    return paramMap
                }
            }
            stringRequestSecureAttendance.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

            Constants.REQUEST_QUEUE.add(stringRequestSecureAttendance)
        }

        fun registration(email: String, ph_no: String, dataCallback: DataCallback) {

            val url = ApiConstant.REGSITER_API
            val requestRegistration = object : StringRequest(Request.Method.POST, url, Response.Listener { s ->

                dataCallback.onSuccess(s)
            }, Response.ErrorListener { volleyError ->


                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {

                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED ->{

                            var message = ACCESS_DENIED
                            val networkResponse = volleyError.networkResponse
                            if (networkResponse != null && networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                                message = String(networkResponse.data)
                                try {
                                    message = JSONObject(message).getString("error_message")
                                } catch (e: JSONException) {
                                    message = ACCESS_DENIED
                                }
                            }
                            message

                        }
                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }


                dataCallback.onFailure(errorMessage)
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["Schoolcode"] = Constants.SCHOOLCODE
                    params["MobileNumber"] = ph_no
                    params["emailAddress"] = email

                    return params
                }
            }
            requestRegistration.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            Constants.REQUEST_QUEUE.add(requestRegistration)
        }

        fun updateRegistrationDetails(paramUpdate: Map<String, String>, dataCallback: DataCallback) {

            val url = ApiConstant.UPADTE_REGSITER_API
            val requestRegistration = object : StringRequest(Request.Method.POST, url, Response.Listener { s ->
                dataCallback.onSuccess(s)
            }, Response.ErrorListener { volleyError ->

                volleyError.printStackTrace()
                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {
                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }
                dataCallback.onFailure(errorMessage)
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    return paramUpdate
                }
            }
            requestRegistration.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            Constants.REQUEST_QUEUE.add(requestRegistration)
        }

        fun getAuthTokenAndLogin(context: Context, userId: String, userPassword: String, dataCallback: DataCallback) {
            val url = ApiConstant.LOGIN_API+"?Schoolcode="+ Constants.SCHOOLCODE
            val stringRequest = object : StringRequest(Request.Method.POST, url, Response.Listener { s ->
                //Log.e("Login Response", s);
                try {
                    dataCallback.onSuccess(s)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { volleyError ->

                volleyError.printStackTrace()
                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {


                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED ->{

                            var message = ACCESS_DENIED
                            val networkResponse = volleyError.networkResponse
                            if (networkResponse != null && networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                                message = String(networkResponse.data)
                                try {
                                    message = JSONObject(message).getString("error_message")
                                } catch (e: JSONException) {
                                    message = ACCESS_DENIED
                                }
                            }
                            message

                       }
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }

                dataCallback.onFailure(errorMessage)

            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["client_secret"] = Constants.AUTH_CLIENT_SECRET
                    params["grant_type"] = Constants.AUTH_GRANT_TYPE
                    params["client_id"] = Constants.AUTH_CLIENT_ID
                    params["username"] = userId
                    params["password"] = userPassword
                    params["scope"] = Constants.AUTH_SCOPE
                    val shared = context.getSharedPreferences(Constants.FCM_KEY, MODE_PRIVATE)

                    var tokenvalue = shared.getString(Constants.FCM_TOKEN, "")
                    params["fcmToken"] = tokenvalue



                    return params
                }
            }
            stringRequest.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            Constants.REQUEST_QUEUE.add(stringRequest)

        }




//        fun uploadHomeWork(fileParam: Array<File?>, dataCallback: DataCallback) {
//            val url = ApiConstant.UPLOAD_HOMEWORK
//            val requestRegistration = object : VolleyMultipartRequest(Request.Method.POST, url, Response.Listener { response ->
//
//                //                dataCallback.onSuccess(s)
//                try {
//                    val obj = JSONObject(String(response.data))
//                    Log.d(TAG, "uploadHomeWork $obj")
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//
//            }, Response.ErrorListener { volleyError ->
//
//                volleyError.printStackTrace()
//                var errorMessage = UNKNOWN_ERROR
//                val networkResponse = volleyError.networkResponse
//                if (networkResponse != null) {
//                    errorMessage = when {
//                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
//                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
//                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
//                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
//                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
//                        else -> UNKNOWN_ERROR
//                    }
//                }
//                dataCallback.onFailure(errorMessage)
//            }) {
//                @Throws(AuthFailureError::class)
//                override fun getParams(): Map<String, String> {
//                    val params = HashMap<String, String>()
//
//                    return params
//
//                }
//
//            }
//            requestRegistration.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
//            Constants.REQUEST_QUEUE.add(requestRegistration)
//
//        }


        fun getNewsAPI(paramMap: HashMap<String, String>,context:Context, dataCallback: DataCallback) {

            val url = (Constants.IP_ADDRESS + Constants.BASE_URL + "?action=news&newsDate=" + Constants.mStringDate + "&SCHOOL_ID="
                        + Constants.SCHOOL_ID)
            Log.d("KOTLIN_GET", "getURL: $url")
            val requestRegistration = object : StringRequest(Method.GET, url, Response.Listener { s ->
                try {
//                var vv = s
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                dataCallback.onSuccess(s)
            }, Response.ErrorListener { volleyError ->
                volleyError.printStackTrace()
                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {
                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }

                dataCallback.onFailure(errorMessage);
                Log.d("JSONRESPONSE", "onError: $volleyError")
            }) {

            }
            requestRegistration.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            Constants.REQUEST_QUEUE.add(requestRegistration)
        }

        fun getEventsAPI(paramMap: HashMap<String, String>,context:Context, dataCallback: DataCallback) {

            val url =
                (Constants.IP_ADDRESS + Constants.BASE_URL + "?action=events&eventDate=" + Constants.mStringDate + "&SCHOOL_ID="
                        + Constants.SCHOOL_ID)

               Log.d("KOTLIN_GET", "getURL: $url")
            val requestRegistration = object : StringRequest(Method.GET, url, Response.Listener { s ->
                try {
//                var vv = s
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                Log.d("JSONRESPONSE", s)
                dataCallback.onSuccess(s)
            }, Response.ErrorListener { volleyError ->
                volleyError.printStackTrace()
                var errorMessage = UNKNOWN_ERROR
                val networkResponse = volleyError.networkResponse
                if (networkResponse != null) {
                    errorMessage = when {
                        networkResponse.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED -> ACCESS_DENIED
                        networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND -> PAGE_NOT_FOUND
                        networkResponse.statusCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> REQUEST_TIME
                        networkResponse.statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                        networkResponse.statusCode == HttpURLConnection.HTTP_BAD_GATEWAY -> NETWORK_ERROR
                        else -> UNKNOWN_ERROR
                    }
                }

                dataCallback.onFailure(errorMessage);
                Log.d("JSONRESPONSE", "onError: $volleyError")
            }) {

            }
            requestRegistration.retryPolicy = DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            Constants.REQUEST_QUEUE.add(requestRegistration)
        }





    }

}