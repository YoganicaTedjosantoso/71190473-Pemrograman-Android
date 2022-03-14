package id.ac.ukdw.pertemuan5_71190473

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.username)
            val password = findViewById<TextInputEditText>(R.id.password)

            if (username.text.toString().equals("yoganica") && password.text.toString()
                    .equals("12345")
            ) {
                Toast.makeText(
                    getApplicationContext(), "LOGIN SUKSES",
                    Toast.LENGTH_SHORT
                ).show();
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("username","yoganica")
                startActivity(intent)
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Username atau Password Anda salah!")
                    .setNegativeButton("Retry", null).create().show()
            }
        }
    }
}

