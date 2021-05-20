package br.com.cotemig.socialcotemig.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    // instanciando o retrofit para ser usado na URL e
    // Conversor JSON > Classe e Classe > JSON
    private val retrofit = Retrofit.Builder().
    baseUrl("https://mockup.fluo.app/v1/").
    addConverterFactory(GsonConverterFactory.create()).
    build()

    fun serviceFeed() : ServiceFeed{
        return retrofit.create(ServiceFeed::class.java)
    }

    fun serviceStories() : ServiceStories{
        return  retrofit.create(ServiceStories::class.java)
    }



}