package util

import Interface.IUserAPIService
import Interface.IMedicationAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MediTrackApiService {
    // Example Base URL
    private const val BASE_URL = "https://api-meditrack-dfa7dzd8brh8f7ee.eastus-01.azurewebsites.net/"

    val apiUser: IUserAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IUserAPIService::class.java)
    }

    val apiMedication: IMedicationAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMedicationAPIService::class.java)
    }
}