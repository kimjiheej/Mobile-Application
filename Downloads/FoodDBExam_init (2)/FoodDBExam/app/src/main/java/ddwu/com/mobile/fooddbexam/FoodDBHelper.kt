package ddwu.com.mobile.fooddbexam

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log


class FoodDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME,null,1) { // 4가지 매개변수를 만들어주는것이 필요하다
    // context 정보를 어딘가에서 받아와서 넣어주어라
    // this 넣어주면 된다
    // DB_NAME 은 파일 이름이 된다. 상수로 지정하면 된다

    val TAG = "FoodDBHelper"

    companion object {
        const val DB_NAME = "food_db"
        const val TABLE_NAME = "food_table"
        const val COL_FOOD = "food"
        const val COL_COUNTRY = "country"
    }

    override fun onCreate(db: SQLiteDatabase?){ // 테이블을 만드는 역할이다
        val CREATE_TABLE =
            "CREATE TABLE ${TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${COL_FOOD} TEXT, ${COL_COUNTRY} TEXT)"
        Log.d(TAG,CREATE_TABLE)
        db?.execSQL(CREATE_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }
}