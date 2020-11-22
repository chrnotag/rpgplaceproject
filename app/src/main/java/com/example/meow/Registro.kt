package com.example.meow

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.toolbar.*

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        supportActionBar!!.hide()

        personalizarToolbar()

        cadastrar.setOnClickListener {
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario() {

        var email = registrarEmail.text.toString()
        var senha = registrarSenha.text.toString()
        var confsenha = confirmarSenha.text.toString()

        if(!email.isEmpty() && !senha.isEmpty() && !confsenha.isEmpty() && senha == confsenha){

            msgerro.text = ""
            msgerro.visibility = View.GONE

            FirebaseAuth.getInstance().
            createUserWithEmailAndPassword(email,senha).
            addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "sucesso", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                var erro = it

                when(erro){
                    is FirebaseAuthInvalidCredentialsException -> {msgerro.visibility = View.VISIBLE
                        msgerro.text = "Insira um Email Valido!"}

                    is FirebaseAuthWeakPasswordException -> {msgerro.visibility = View.VISIBLE
                        msgerro.text = "A senha deve ter no minimo 6 caracteres!"}

                    is FirebaseAuthUserCollisionException -> {msgerro.visibility = View.VISIBLE
                        msgerro.text = "Usuario já existente!"}

                    is FirebaseNetworkException -> {msgerro.visibility = View.VISIBLE
                        msgerro.text = "Erro de conexão, verifique sua internet!"}

                    else -> {msgerro.text = "Erro desconhecido"}
                }
            }

        }else{
            msgerro.visibility = View.VISIBLE
            msgerro.text = "Reveja os campos!"
        }

    }

    fun personalizarToolbar(){

        toolbar.title = "Cadastre-se"
        toolbar.subtitle = "E viva novas aventuras!"
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setSubtitleTextColor(Color.WHITE)

        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        toolbar.menu.add("Ajuda")
        toolbar.menu.add("Contato")
    }
}