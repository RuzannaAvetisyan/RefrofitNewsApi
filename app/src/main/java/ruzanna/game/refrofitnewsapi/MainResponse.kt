package ruzanna.game.refrofitnewsapi

import com.google.gson.annotations.SerializedName

class MainResponse(
        @SerializedName("articles") val articles: List<Article>,
        @SerializedName("totalResults") val totalResults: Int,
        @SerializedName("status") val status: String
)
