package ddwu.com.mobile.openapitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.openapitest.data.Movie
import ddwu.com.mobile.openapitest.databinding.ActivityMainBinding
import ddwu.com.mobile.openapitest.network.NetworkManager
import ddwu.com.mobile.openapitest.ui.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    lateinit var mainBinding : ActivityMainBinding
    lateinit var adapter : MovieAdapter
    lateinit var networkDao : NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        networkDao = NetworkManager(this)

        adapter = MovieAdapter()
        mainBinding.rvMovies.adapter = adapter
        mainBinding.rvMovies.layoutManager = LinearLayoutManager(this)

        mainBinding.btnSearch.setOnClickListener {
            val date = mainBinding.etDate.text.toString()
//  코루틴은 스레드 보다 더 작은 단위로 동시에 작업을 할 수 있도록 한다.
            CoroutineScope(Dispatchers.Main).launch{
                val def = async(Dispatchers.IO) { // 네트워크는 io 스레드 사용
                    var movies : List<Movie>? = null
                    try {
                        movies = networkDao.downloadXml(date)
                    } catch (e: IOException) {
                        Log.d(TAG, e.message?: "null")
                        null
                    } catch (e: XmlPullParserException) {
                        Log.d(TAG, e.message?: "null")
                        null
                    }
                    movies
                }

                // main 이다. 화면과 관련된 스레드는 main 에서 해야한다.
                adapter.movies = def.await()
                adapter.notifyDataSetChanged()
            }
        }

    }
}