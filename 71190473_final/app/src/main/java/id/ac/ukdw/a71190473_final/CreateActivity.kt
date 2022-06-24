package id.ac.ukdw.a71190473_final

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        //Toolbar
        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val edtJudul = findViewById<EditText>(R.id.edtJudul)
        val edtGenre = findViewById<EditText>(R.id.edtGenre)
        val edtProduser = findViewById<EditText>(R.id.edtProduser)
        val edtPemeran = findViewById<EditText>(R.id.edtPemeran)
        val edtTahun = findViewById<EditText>(R.id.edtTahun)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val film = Film(
                edtJudul.text.toString(), edtGenre.text.toString(),
                edtProduser.text.toString(), edtPemeran.text.toString(),
                edtTahun.text.toString()
            )

            edtJudul.setText("")
            edtGenre.setText("")
            edtProduser.setText("")
            edtPemeran.setText("")
            edtTahun.setText("")
            firestore?.collection("film")?.add(film)?.addOnSuccessListener {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                Toast.makeText(this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.button_navigation, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when(item.itemId){
            R.id.ic_home -> {
                val home = Intent(this, MainActivity::class.java)
                startActivity(home)
                this.finish()
            }
            R.id.btnLogout -> {
                auth.signOut()
                val intentKeluar = Intent(this, LoginActivity::class.java)
                startActivity(intentKeluar)
                this.finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}