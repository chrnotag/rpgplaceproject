package com.example.meow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()

        verificarUsuarioLogado()
        login()
        cadastrese()

    }

    fun verificarUsuarioLogado(){
        var usuario = FirebaseAuth.getInstance().currentUser
        if(usuario != null){
            abrirTelaPrincipal()
        }
    }

    fun login() {
        entrar.setOnClickListener {

            var email = email.text.toString()
            var senha = senha.text.toString()

            if (!email.isEmpty() && !senha.isEmpty()) {

                msgerrologin.visibility = View.GONE
                msgerrologin.text = ""

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                            abrirTelaPrincipal()
                        }

                    }.addOnFailureListener {

                    var erro = it

                    when (erro) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            msgerrologin.visibility = View.VISIBLE
                            msgerrologin.text = "Email ou senha Incorretos!"
                        }
                        is FirebaseNetworkException -> {
                            msgerrologin.visibility = View.VISIBLE
                            msgerrologin.text = "Sem conexão com a internet!"
                        }

                        else -> {
                            msgerrologin.visibility = View.VISIBLE
                            msgerrologin.text = "Erro desconhecido"
                        }
                    }

                }
            } else {
                msgerrologin.visibility = View.VISIBLE
                msgerrologin.text = "Reveja os campos!"
            }

        }
    }

    fun cadastrese() {
        cadastrese.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }

    fun abrirTelaPrincipal(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}