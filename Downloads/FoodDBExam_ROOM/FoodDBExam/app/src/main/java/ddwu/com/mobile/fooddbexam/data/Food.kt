package ddwu.com.mobile.fooddbexam.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

// 여기에 정의하면 된다
@Entity(tableName="food_table")
data class Food (

    @PrimaryKey(autoGenerate = true)
    val _id: Int,

    var food: String?,


    var country: String?,
)
{
    override fun toString() = "$_id - $food ( $country )\n"
}

