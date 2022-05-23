package id.ac.ukdw.pertemuan11_71190473

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtNim = findViewById<EditText>(R.id.edtNim)
        val edtIpk = findViewById<EditText>(R.id.edtIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val rg1 = findViewById<RadioGroup>(R.id.rg1)
        val rg2 = findViewById<RadioGroup>(R.id.rg2)
        val radioAsc = findViewById<RadioButton>(R.id.radioAsc)
        val radioDesc = findViewById<RadioButton>(R.id.radioDesc)
        val radioNim = findViewById<RadioButton>(R.id.radioNim)
        val radioNama = findViewById<RadioButton>(R.id.radioNama)
        val radioIpk = findViewById<RadioButton>(R.id.radioIpk)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val btnFilter = findViewById<Button>(R.id.btnFilter)
        val txvHasil = findViewById<TextView>(R.id.txvHasil)
        val txvHslCari2 = findViewById<TextView>(R.id.txvHslCari2)


        btnSimpan.setOnClickListener{
            val m = Mahasiswa(edtNama.text.toString(), edtNim.text.toString().toInt(), edtIpk.text.toString().toInt())
            db.collection("mahasiswa").document(edtNim.text.toString()).set(m)

            edtNama.setText("")
            edtNim.setText("")
            edtIpk.setText("")
            refreshData()
        }

        btnHapus.setOnClickListener{
            db.collection("mahasiswa").document(edtNim.text.toString()).delete()

            edtNama.setText("")
            edtNim.setText("")
            edtIpk.setText("")
            refreshData()
        }

        refreshData()

        btnFilter.setOnClickListener {
            if (radioAsc.isChecked()) {
                if (radioNama.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nama", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txvHslCari2.setText(output)
                        }
                } else if (radioNim.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nim", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txvHslCari2.setText(output)
                        }
                } else if (radioIpk.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("ipk", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txvHslCari2.setText(output)
                        }
                }
            } else if (radioDesc.isChecked()) {
                if (radioNama.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nama", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txvHslCari2.setText(output)

                        }
                } else if (radioNim.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nim", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txvHslCari2.setText(output)
                        }
                } else if (radioIpk.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("ipk", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txvHslCari2.setText(output)
                        }
                }
            }
            rg1.clearCheck()
            rg2.clearCheck()
        }

        btnCari.setOnClickListener {
            if (edtNama.text.toString() != "" && edtNama.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txvHasil.setText(output)

                    }
            } else if (edtNim.text.toString() != "" && edtNim.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("nim", edtNim.text.toString().toInt())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txvHasil.setText(output)
                    }
            } else if (edtIpk.text.toString() != "" && edtNama.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("ipk", edtIpk.text.toString().toInt())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txvHasil.setText(output)
                    }
            }

        }

    }

    fun refreshData() {
        db.collection("mahasiswa")
            .get()
            .addOnSuccessListener {snapshot ->
                val txvHasil = findViewById<TextView>(R.id.txvHasil)
                var hasil =""
                for(doc in snapshot){
                    hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"
                }
                txvHasil.text = hasil
            }

    }
}