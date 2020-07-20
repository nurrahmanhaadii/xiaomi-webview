package id.haadii.visionplus.xiaomiwebview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import id.haadii.visionplus.xiaomiwebview.R
import id.haadii.visionplus.xiaomiwebview.model.XiaomiResponse
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity(), PlayContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        val url = intent.getStringExtra("url")
        val headerOne = intent.getStringExtra("headerOne")
        val headerTwo = intent.getStringExtra("headerTwo")
        val presenter = PlayPresenter(url!!, headerOne!!, headerTwo!!, this)
        presenter.getData()

    }

    override fun getDataSuccess(data: XiaomiResponse) {
        val adapterList = ListAdapter(data.data[0].episodes) {
            setWebView(it.play_info.play_url)
        }
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@PlayActivity)
            adapter = adapterList
        }
    }

    private fun setWebView(url: String) {
        webview?.apply {
            visibility = View.VISIBLE
            settings?.apply {
                loadsImagesAutomatically = true
                javaScriptEnabled = true
                domStorageEnabled = true
            }
            webViewClient = MyWebClient()
            webChromeClient = MyWebChromeClient()
            loadUrl(url)
        }
    }

    inner class MyWebClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar?.visibility = View.GONE
        }
    }

    inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100)
                progressBar?.visibility = View.GONE
            else
                progressBar?.visibility = View.VISIBLE
        }

        override fun onPermissionRequest(request: PermissionRequest?) {
            Handler().post {
                request?.grant(arrayOf(PermissionRequest.RESOURCE_PROTECTED_MEDIA_ID))
            }
        }
    }

    override fun getDataFailed() {

    }
}