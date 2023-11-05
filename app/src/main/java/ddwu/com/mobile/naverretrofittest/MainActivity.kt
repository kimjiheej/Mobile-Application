package ddwu.com.mobile.naverretrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ddwu.com.mobile.naverretrofittest.data.BookRoot
import ddwu.com.mobile.naverretrofittest.databinding.ActivityMainBinding
import ddwu.com.mobile.naverretrofittest.network.IBookAPIService
import ddwu.com.mobile.naverretrofittest.ui.BookAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    lateinit var mainBinding : ActivityMainBinding
    lateinit var adapter : BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        adapter = BookAdapter()
        mainBinding.rvBooks.adapter = adapter
        mainBinding.rvBooks.layoutManager = LinearLayoutManager(this)


        adapter.setOnItemClickListener(object : BookAdapter.OnItemClickListner {
            override fun onItemClick(view: View, position: Int) {
                // RecyclerView 항목 클릭 시 해당 위치의 Item 이 갖고 있는 image 를 Glide 에 전달

                val url = adapter?.books?.get(position)?.image

                Glide.with(applicationContext)
                    .load(url)
                    .into(mainBinding.imageView)
            }
        })


        val retrofit = Retrofit.Builder()
            .baseUrl(resources.getString(R.string.naver_api_url))
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        val service = retrofit.create(IBookAPIService::class.java)



        mainBinding.btnSearch.setOnClickListener {
            val keyword = mainBinding.etKeyword.text.toString()

            val apiCall = service.getBooksByKeyword(
                resources.getString(R.string.client_id),
                resources.getString(R.string.client_secret),
                keyword,
            )

            apiCall.enqueue(object: Callback<BookRoot> {
                override fun onResponse(call: Call<BookRoot>, response: Response<BookRoot>) {

                    if (response.isSuccessful) {

                        val root: BookRoot? = response.body()
                        adapter.books = root?.items
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.d(TAG, response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<BookRoot>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}