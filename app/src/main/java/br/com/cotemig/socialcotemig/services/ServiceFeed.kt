package br.com.cotemig.socialcotemig.services

import br.com.cotemig.socialcotemig.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ServiceFeed {

    @GET("feed")
    fun getFeed(): Call<List<Post>>
}