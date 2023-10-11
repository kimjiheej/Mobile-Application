package ddwu.com.mobile.networktest


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import ddwu.com.mobile.networktest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding
    lateinit var networkManager : NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkManager = NetworkManager(this)


        // 임시로 코루틴 쓰지 않고 수행할 수 있도록 한다
       val pol = StrictMode.ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(pol)


        binding.btnConnInfo.setOnClickListener{
            getNetworkInfo()
        }

        binding.btnActiveInfo.setOnClickListener {
            Log.d(TAG, "Network is connected: ${isOnline()}")
        }

        binding.btnDown.setOnClickListener {
         //   val url = "https://httpbin.org/get?user=somsom&dept=computer"
         //   val url = "https://cs.dongduk.ac.kr"


//            // URL을 빌드하기 위한 Uri.Builder를 생성합니다.
//            val uriBuilder = Uri.parse(resources.getString(R.string.kobis_url)).buildUpon()
//
//             매개변수를 HashMap으로 추가합니다.
//            val queryParams = HashMap<String, String>()
//            queryParams["itemPerPage"] = "10" // 원하는 값으로 변경
//            queryParams["multiMovieYn"] = "N"   // 원하는 값으로 변경
//            queryParams["targetDt"] = date
//
//            // HashMap에 있는 매개변수를 Uri.Builder에 추가합니다.
//            for ((key, value) in queryParams) {
//                uriBuilder.appendQueryParameter(key, value)
//            }
//
//            // 최종 URL을 생성합니다.
   //         <string name="kobis_url">http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=f5eef3421c602c6cb7ea224104795888&amp;targetDt=&amp;itemPerPage=10&amp;multiMovieYn=N</string>
//            val finalUrl = uriBuilder.build().toString()


            val date = binding.etDate.text.toString()
            val url = resources.getString(R.string.kobis_url) + date
            binding.tvDisplay.text = networkManager.downloadText(url)


        }

        binding.btnImg.setOnClickListener {
            val imageUrl = resources.getString(R.string.image_url)
            val result = networkManager.downloadImage(imageUrl)
            binding.ivDisplay.setImageBitmap(result)
        }

        binding.btnSend.setOnClickListener {
            binding.tvDisplay.text = networkManager.sendPostData("https://httpbin.org/post")
        }
    }
    private fun getNetworkInfo() {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isWifiConn: Boolean = false
        var isMobileConn: Boolean = false
        connMgr.allNetworks.forEach { network ->
            connMgr.getNetworkInfo(network)?.apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or isConnected
                }
                if (type == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = isMobileConn or isConnected
                }
            }
        }

        Log.d(TAG, "Wifi connected: $isWifiConn")
        Log.d(TAG, "Mobile connected: $isMobileConn")
    }


    private fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}