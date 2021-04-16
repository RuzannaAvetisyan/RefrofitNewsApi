package ruzanna.game.refrofitnewsapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {
    @GET("v2/everything?sortBy=publishedAt&apiKey=96c60ed7c45e45fe9cc81646e6e26abd&pageSize=15")
    fun getArticles(@Query("q") q: String): Call<MainResponse>
}
