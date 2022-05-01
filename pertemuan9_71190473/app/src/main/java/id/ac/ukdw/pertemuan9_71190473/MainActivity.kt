package id.ac.ukdw.pertemuan9_71190473

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var sp: SharedPreferences? = null
    var spEdit: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences("mySP", MODE_PRIVATE)
        spEdit = sp?.edit()


        if(sp?.getBoolean("isLogin", false)== true){
            setContentView(R.layout.activity_home)
            val btnLogout = findViewById<Button>(R.id.btnLogout)
            btnLogout.setOnClickListener{
                logout()
            }
            val spinnerBahasa = findViewById<Spinner>(R.id.spinner_bahasa)
            val adapterArray = ArrayAdapter.createFromResource(this, R.array.daftar_bahasa, com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerBahasa.adapter = adapterArray
            spinnerBahasa.setSelection(sp!!.getInt("bahasa",0))

            spinnerBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                    spEdit?.putInt("bahasa", position)
                    spEdit?.commit()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            val ukuranFont = findViewById<TextView>(R.id.Edt_ukuranFont2)
            ukuranFont.text = sp?.getString("ukuran", "10")
            ukuranFont.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
                override fun afterTextChanged(s: Editable?) {
                    spEdit?.putString("ukuran", s.toString())
                    spEdit?.commit()

                }

            })
        }else{
            setContentView(R.layout.activity_main)
            val edtUsername = findViewById<EditText>(R.id.edtUsername)
            val edtPassword = findViewById<EditText>(R.id.edtPassword)
            val btnLogin = findViewById<Button>(R.id.btnLogin)
            btnLogin.setOnClickListener{
                login(edtUsername.text.toString(), edtPassword.text.toString())
            }
        }
    }
    fun login(username: String, password: String){
        if(username.equals("admin")&& password.equals("1234")){
            spEdit?.putBoolean("isLogin",true)?.commit()

            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(this,"Username atau Password Salah",Toast.LENGTH_LONG).show()
        }
    }
    fun logout(){
        spEdit?.putBoolean("isLogin",false)?.commit()

        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }
}