package ddwu.com.mobile.jsonnetworktest.data

import com.google.gson.annotations.SerializedName

data class BoxOfficeRoot(val boxOfficeResult: BoxOfficeResult,)

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

data class Root(
    val boxOfficeResult: BoxOfficeResult,
)

//data class BoxOfficeResult(
//    val boxofficeType: String,
//    val showRange: String,
//    val dailyBoxOfficeList: List<DailyBoxOfficeList>,
//)
//data class DailyBoxOfficeList(
//    val rnum: String,
//    val rank: String,
//    val rankInten: String,
//    val rankOldAndNew: String,
//    val movieCd: String,
//    val movieNm: String,
//    val openDt: String,
//    val salesAmt: String,
//    val salesShare: String,
//    val salesInten: String,
//    val salesChange: String,
//    val salesAcc: String,
//    val audiCnt: String,
//    val audiInten: String,
//    val audiChange: String,
//    val audiAcc: String,
//    val scrnCnt: String,
//    val showCnt: String,
//)