package id.ac.ukdw.a71190473_final

import android.app.ProgressDialog
import android.bluetooth.BluetoothDevice.EXTRA_NAME
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.a71190473_final.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView

    var firestore: FirebaseFirestore? = null
    var listFilm = arrayListOf<Film>()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()

        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)




        //variable untuk pencarian
        val btnCari = findViewById<Button>(R.id.btnCari)
        val edtCari = findViewById<EditText>(R.id.edtSearch)
        val rvFilm = findViewById<RecyclerView>(R.id.rvFilm)
        val btnAdd = findViewById<FloatingActionButton>(R.id.fab_add)


        btnAdd.setOnClickListener {
            val i = Intent(this, CreateActivity::class.java)
            startActivity(i)
        }



        val loading = ProgressDialog(this)
        loading.setMessage("Loading Data...")
        loading.show()
        firestore?.collection("film")?.get()?.addOnSuccessListener { docs ->
            var hasil = ""
            for (doc in docs) {
                hasil += "${doc["judul"]}"
                val filmAdd = Film(
                    "${doc["judul"]}",
                    "${doc["genre"]}",
                    "${doc["produser"]}",
                    "${doc["pemeran"]}",
                    "${doc["tahun"]}"
                )
                listFilm.add(filmAdd)
            }
            loading.dismiss()
        }
        Handler().postDelayed({
            rvFilm.layoutManager = LinearLayoutManager(this)
            val adapter = FilmAdapter(listFilm, this)
            rvFilm.adapter = adapter
        }, 1000)

        btnCari.setOnClickListener {
            var pencarian = edtCari.text.toString()
            if (pencarian.isEmpty()) {
                Toast.makeText(this, "Pencarian Kosong", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    rvFilm.layoutManager = LinearLayoutManager(this)
                    val adapter = FilmAdapter(listFilm, this)
                    rvFilm.adapter = adapter
                }, 1000)
            } else if (!pencarian.isEmpty()) {
                loading.setMessage("Mencari...")
                loading.show()
                listFilm.clear()
                firestore?.collection("film")?.get()?.addOnSuccessListener { docs ->
                    for (cari in docs) {
                        var bool = true
                        val filmCari = Film(
                            "${cari["judul"]}",
                            "${cari["genre"]}",
                            "${cari["produser"]}",
                            "${cari["pemeran"]}",
                            "${cari["tahun"]}"
                        )
                        if (pencarian.equals("${cari["judul"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["genre"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["produser"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["pemeran"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["tahun"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                    }
                }
                loading.dismiss()
                Handler().postDelayed({
                    rvFilm.layoutManager = LinearLayoutManager(this)
                    val adapter = FilmAdapter(listFilm, this)
                    rvFilm.adapter = adapter
                },1000)
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