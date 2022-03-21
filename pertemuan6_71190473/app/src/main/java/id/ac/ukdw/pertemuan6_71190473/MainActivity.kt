package id.ac.ukdw.pertemuan6_71190473

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun onConfigurationChanged(newConfig: Configuration) {
            super.onConfigurationChanged(newConfig)
            setContentView(R.layout.activity_main)
        }
    }
}