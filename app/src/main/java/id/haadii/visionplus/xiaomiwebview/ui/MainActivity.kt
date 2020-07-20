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
            intent.putExtra("url", url)
            intent.putExtra("headerOne", headerOne)
            intent.putExtra("headerTwo", headerTwo)
            startActivity(intent)
        }
    }
}