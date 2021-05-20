package br.com.cotemig.socialcotemig.services

import br.com.cotemig.socialcotemig.model.Stories
import retrofit2.Call
import retrofit2.http.GET

interface ServiceStories {
    @GET("stories")
    fun getStories(): Call<List<Stories>>
}