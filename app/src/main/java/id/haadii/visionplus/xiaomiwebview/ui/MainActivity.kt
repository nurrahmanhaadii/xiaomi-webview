package id.haadii.visionplus.xiaomiwebview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.haadii.visionplus.xiaomiwebview.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnProcess?.setOnClickListener {
            val url = etUrl?.text.toString()
            val headerOne = etHeaderOne?.text.toString()
            val headerTwo = etHeaderTwo?.text.toString()
            val intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("url", "https://partners-api.visionplus.id/api/v1/partnership/xiaomi/all?page_no=1&page_size=20")
            intent.putExtra("headerOne", "PZ8ObMKnq_GeFWSO_ec9el9-ybBkzCQy7snJpazDNKE")
            intent.putExtra("headerTwo", "eOAIIXS5dMFNdB_7hpsoFwMVakBcA6tP9EPLw0O-DB4")
            startActivity(intent)
        }
    }
}