package id.haadii.visionplus.xiaomiwebview.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            addJavascriptInterface(JavaScriptInterface(), "myjava")
            loadUrl(url)
//            loadUrl("javascript:myjava.returnValue(getTest)")
//            loadUrl("javascript:alert(getTest)")
        }
    }

    inner class MyWebClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar?.visibility = View.GONE
            webview.evaluateJavascript("javascript:getCek();", object: ValueCallback<String> {
                override fun onReceiveValue(value: String?) {
                    Log.e("JSBridge", "test:" + value.toString())
                }
            })
        }
    }

    inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100) {
                progressBar?.visibility = View.GONE
                webview.evaluateJavascript("javascript:getCek();", object: ValueCallback<String> {
                    override fun onReceiveValue(value: String?) {
                        Log.e("JSBridge", "check:" + value.toString())
                    }
                })
            }
            else
                progressBar?.visibility = View.VISIBLE
        }

        override fun onPermissionRequest(request: PermissionRequest?) {
            Handler().post {
                request?.grant(arrayOf(PermissionRequest.RESOURCE_PROTECTED_MEDIA_ID))
            }
        }

        override fun onJsAlert(
            view: WebView?,
            url: String?,
            message: String?,
            result: JsResult?
        ): Boolean {
            Log.e("jsAlert", message)
            result?.confirm()
            return true
        }
    }

    inner class JavaScriptInterface {
        @JavascriptInterface
        fun returnValue() {
            Log.e("JSBridge", "check:")
        }
    }

    override fun getDataFailed() {

    }
}