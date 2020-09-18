package qboinstitute.com.appformularioskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnlogin.setOnClickListener {
            val usuario = etusuario.text.toString()
            val password = etpassword.text.toString()
            if(usuario == ""){
                etusuario.error = "Ingrese su usuario"
            }else if(password == ""){
                etpassword.error = "Ingrese su password"
            }else{
                if(validarUsuario(usuario, password)){
                    val intentlogin = Intent(this,
                    RegistroActivity::class.java).apply {
                        putExtra("usuario", usuario)
                        putExtra("password", password)
                    }
                    startActivity(intentlogin)
                }else{
                    Snackbar.make(it, "Usuario o password incorrecto",
                    Snackbar.LENGTH_LONG).show()
                }
            }
        }

    }

    fun validarUsuario(usu: String, pwd : String): Boolean{
        var respuesta = false
        if(usu == "lsalvatierra" && pwd == "123456"){
            respuesta = true
        }
        return respuesta
    }
}