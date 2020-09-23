package qboinstitute.com.appformularioskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    private val listapreferencias = ArrayList<String>()
    private val listacontactos = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        chkdeporte.setOnClickListener {
            agregarListaDePreferenciasSeleccionadas(it)
        }
        chkdibujo.setOnClickListener {
            agregarListaDePreferenciasSeleccionadas(it)
        }
        chkotros.setOnClickListener {
            agregarListaDePreferenciasSeleccionadas(it)
        }
        btnregistrar.setOnClickListener {
            if(validarFormulario(it)){
                var infocontacto = etnombre.text.toString() + " " +
                        obtenerGeneroSeleccionado()+" " +
                        obtenerPreferenciasSeleccionadas()
                listacontactos.add(infocontacto)
                enviarMensajeError(it, "Contacto Registrado")
                setearControles()
            }
        }
        btnlistar.setOnClickListener {
            val intentlista = Intent(this,
            ListaActivity::class.java)
                .apply {
                    putExtra("listacontactos", listacontactos)
                }
            startActivity(intentlista)
        }

    }

    fun setearControles(){
        listapreferencias.clear()
        etnombre.setText("")
        chkdeporte.isChecked = false
        chkdibujo.isChecked = false
        chkotros.isChecked = false
        rggenero.clearCheck()
        etnombre.isFocusableInTouchMode = true
        etnombre.requestFocus()
    }

    fun obtenerPreferenciasSeleccionadas(): String{
        var preferencias = ""
        for(pref in listapreferencias){
            preferencias += "$pref -"
        }
        return preferencias
    }

    fun agregarListaDePreferenciasSeleccionadas(vista: View){
        if(vista is CheckBox){
            val check: Boolean = vista.isChecked
            when(vista.id){
                R.id.chkdeporte -> {
                    if(check){
                        listapreferencias.add(vista.text.toString())
                    }else{
                        listapreferencias.remove(vista.text.toString())
                    }
                }
                R.id.chkdibujo -> {
                    if(check){
                        listapreferencias.add(vista.text.toString())
                    }else{
                        listapreferencias.remove(vista.text.toString())
                    }
                }
                R.id.chkotros -> {
                    if(check){
                        listapreferencias.add(vista.text.toString())
                    }else{
                        listapreferencias.remove(vista.text.toString())
                    }
                }
            }
        }
    }

    fun obtenerGeneroSeleccionado():String{
        var genero = ""
        when(rggenero.checkedRadioButtonId){
            R.id.rbtnmasculino -> {
                genero = rbtnmasculino.text.toString()
            }
            R.id.rbtnfemenino -> {
                genero = rbtnfemenino.text.toString()
            }
        }
        return genero
    }

    fun validarFormulario(vista: View): Boolean{
        var respuesta = false
        if(!validarNombre()){
            enviarMensajeError(vista, "Es obligatorio su nombre.")
        }else if(!validarGenero()){
            enviarMensajeError(vista, "Seleccione un género")
        }else if(!validarPreferencias()){
            enviarMensajeError(vista, "Seleccione una preferencia")
        }else{
            respuesta = true
        }
        return respuesta
    }

    fun enviarMensajeError(vista: View, mensajeError: String){
        Snackbar.make(vista, mensajeError, Snackbar.LENGTH_LONG).show()
    }

    fun validarPreferencias(): Boolean{
        var respuesta = false
        if(chkdeporte.isChecked || chkdibujo.isChecked || chkotros.isChecked){
            respuesta = true
        }
        return respuesta
    }
    fun validarGenero(): Boolean{
        var respuesta = true
        if(rggenero.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }
    fun validarNombre(): Boolean{
        var respuesta = true
        if(etnombre.text.toString().trim().isEmpty()){
            etnombre.isFocusableInTouchMode = true
            etnombre.requestFocus()
            respuesta = false
        }
        return respuesta
    }
}