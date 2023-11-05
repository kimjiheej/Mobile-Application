package ddwu.com.mobile.jsonnetworktest.data

import com.google.gson.annotations.SerializedName




data class BoxOfficeRoot(val boxOfficeResult: BoxOfficeResult,) // root 클래스의 객체만 알아내면 된다

data class BoxOfficeResult(
    val boxofficeType: String,
    val showRange: String,

//    val dailyBoxOfficeList: List<DailyBoxOfficeList>,
    @SerializedName("dailyBoxOfficeList")
    val movies: List<Movie>
)


data class Movie (
    @SerializedName("rank")
    var rank: Int?,
    @SerializedName("movieNm")
    var title: String?,
    @SerializedName("openDt")
    var openDate: String?,
)
