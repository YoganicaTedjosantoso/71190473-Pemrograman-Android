package id.ac.ukdw.pertemuan6_71190473

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActivitykeTiga : AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ketiga)
        fun onConfigurationChanged(newConfig: Configuration) {
            super.onConfigurationChanged(newConfig)
            setContentView(R.layout.activity_ketiga)

        }
    }
}