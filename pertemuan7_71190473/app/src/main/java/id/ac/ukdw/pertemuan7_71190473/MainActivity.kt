package id.ac.ukdw.pertemuan7_71190473

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = arrayListOf<Contact>()
        listContact.add(Contact("Albert", R.mipmap.male1, "08122113343", "Albert@gmail.com"))
        listContact.add(Contact("Reynaldo", R.mipmap.icon2, "08123411221", "Reynaldo@gmail.com"))
        listContact.add(Contact("Leonel", R.mipmap.woman, "081236211221", "Leonel@gmail.com"))
        listContact.add(Contact("Messi", R.mipmap.orang, "08113231341", "Messi@gmail.com"))
        listContact.add(Contact("Torres", R.mipmap.orang3, "08131312456", "Torres@gmail.com"))
        listContact.add(Contact("Ferann", R.mipmap.orang, "083126211221", "Ferann@gmail.com"))
        listContact.add(Contact("Aubamenyang", R.mipmap.orang4, "081236211221", "Aubamenyang@gmail.com"))
        listContact.add(Contact("Stegen", R.mipmap.orang5, "089824172471", "Stegen@gmail.com"))
        listContact.add(Contact("Pique", R.mipmap.icon2, "082412112123", "Pique@gmail.com"))
        listContact.add(Contact("Fati", R.mipmap.male1, "089122121311", "Fati@gmail.com"))


        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.layoutManager = LinearLayoutManager(this)
        val contactAdapter = ContactAdapter(listContact)
        rvContact.adapter = contactAdapter
    }
}