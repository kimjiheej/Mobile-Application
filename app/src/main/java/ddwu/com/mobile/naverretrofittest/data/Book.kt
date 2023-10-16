package ddwu.com.mobile.naverretrofittest.data

//data class BookRoot (
//
//)
//
//
//data class Item (
//
//)

data class BookRoot(
    val lastBuildDate: String,
    val total: Long,
    val start: Long,
    val display: Long,
    val items: List<Item>,
)

data class Item(
    val title: String,
    val image: String,
    val author: String,
    val publisher: String,


)