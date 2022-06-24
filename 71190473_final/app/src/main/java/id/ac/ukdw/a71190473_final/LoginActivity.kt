package id.ac.ukdw.a71190473_final

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import id.ac.ukdw.a71190473_final.databinding.ActivityLoginBinding
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    //constants
    private companion object{
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        checkUser()

        binding.tvToRegister.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (email.isEmpty()) {
                binding.edtEmail.error = "Email Harus Diisi"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.error = "Email Tidak Valid"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = "Password Harus Diisi"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email, password)

        }
        //Google SignIn Button
        binding.btnLoginGoogle.setOnClickListener{
            Log.d(TAG, "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent

            startActivityForResult(intent, RC_SIGN_IN)
        }

        //configure google sign in
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("271632331640-74jiilredfjqe20cocnve8mhg738rph7.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

    }
    private fun checkUser() {
        //check apakah user login atau tidak
        val firebaseUser = auth.currentUser
        if(firebaseUser!=null){
            //user sudah login
            //start profile activity
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()
        }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                //sukses sign in, authenticate dengan firebase
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }catch (e: Exception){
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken,null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                //sukses login
                Log.d(TAG, "firebaseAuthWithGoogleAccount:  LoggedIn")

                //ambil user login
                val firebaseUser = auth.currentUser

                //ambil info user
                val uid =firebaseUser!!.uid
                val email = firebaseUser.email


                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")



                //cek apakah baru atau lama
                if (authResult.additionalUserInfo!!.isNewUser){
                    //jika baru, create akun baru
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account created... \n$email")
                    Toast.makeText(this@LoginActivity, "Account created... \n$email", Toast.LENGTH_SHORT).show()

                }else{
                    //akun sudah ada
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing user... \n$email")
                    Toast.makeText(this@LoginActivity, "LoggedIn... \n$email", Toast.LENGTH_SHORT).show()

                }

                //start profile activity
                startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                //login gagal
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin failed due to ${e.message}")
                Toast.makeText(this@LoginActivity, "Loggin failed due to ${e.message}", Toast.LENGTH_SHORT).show()


            }


    }
    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@LoginActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }



}
