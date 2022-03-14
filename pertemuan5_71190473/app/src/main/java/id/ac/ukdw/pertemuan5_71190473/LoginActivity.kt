package id.ac.ukdw.pertemuan5_71190473

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = intent.getStringExtra("username")

        val textSapaan = findViewById<TextView>(R.id.txthasil)
        textSapaan.setText("Selamat Datang, $username")
    }
}