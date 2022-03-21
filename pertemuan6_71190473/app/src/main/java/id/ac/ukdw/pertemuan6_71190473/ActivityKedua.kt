package id.ac.ukdw.pertemuan6_71190473

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActivityKedua : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kedua)
        fun onConfigurationChanged(newConfig: Configuration) {
            super.onConfigurationChanged(newConfig)
            setContentView(R.layout.activity_kedua)

        }
    }
}