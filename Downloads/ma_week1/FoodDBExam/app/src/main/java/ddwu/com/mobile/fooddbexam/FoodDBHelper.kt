package ddwu.com.mobile.fooddbexam

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

class FoodDBHelper(context : Context) : SQLiteOpenHelper(context, DB_NAME, null,1) {

    val TAG = "FoodDBHelper"
    companion object{ // 상수로 정해주기
        const val DB_NAME = "food_db"
        const val TABLE_NAME = "food_table"
        const val COL_FOOD = "food"
        const val COL_COUNTRY = "country"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE ${TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${COL_FOOD} TEXT, ${COL_COUNTRY} TEXT)"

        Log.d(TAG,CREATE_TABLE)
        db?.execSQL(CREATE_TABLE) // create table 문장을 실행해주는 것이다.

        db?.execSQL("INSERT INTO ${TABLE_NAME} VALUES (null,'불고기','한국')")
        db?.execSQL("INSERT INTO ${TABLE_NAME} VALUES (null,'비빔밥','한국')")
        db?.execSQL("INSERT INTO ${TABLE_NAME} VALUES (null,'마라탕','중국')")
        db?.execSQL("INSERT INTO ${TABLE_NAME} VALUES (null,'딤섬','중국')")
        db?.execSQL("INSERT INTO ${TABLE_NAME} VALUES (null,'스시','일본')")
        db?.execSQL("INSERT INTO ${TABLE_NAME} VALUES (null,'오코노미야키','일본')")
        Log.d(TAG,CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

}