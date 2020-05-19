package com.example.myapplication123.retrofit

import android.content.ContentValues.TAG
import android.util.Log
import com.example.myapplication123.model.SbModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun getRequiredRepos(
    service: GithubService,
    page: Int,
    itemsPerPage: Int,
    onSuccess: (repos: List<SbModel>) -> Unit,
    onError: (error: String) -> Unit
) {
    Log.d(TAG, " page: $page, itemsPerPage: $itemsPerPage")


    service.getRequiredRepos( page, itemsPerPage).enqueue(
        object : Callback<List<SbModel>> {
            override fun onFailure(call: Call<List<SbModel>>?, t: Throwable) {
                Log.d(TAG, "fail to get data"+t.message)
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<List<SbModel>>?,
                response: Response<List<SbModel>>
            ) {
                Log.d(TAG, "got a response $response")
                if (response.isSuccessful) {
                    val repos = response.body() ?: emptyList()
                    onSuccess(repos)
                    Log.d(TAG, "got a response $repos")

                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}


interface GithubService {
    @GET("/orgs/octokit/repos?")
    fun getRequiredRepos(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): Call<List<SbModel>>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): GithubService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            Log.d("In GS","GS"+client)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService::class.java)
        }
    }
}